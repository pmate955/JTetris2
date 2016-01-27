/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtetris;

import java.awt.Point;
import java.util.ArrayList;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Mate1
 */
public class Map {
    int[][] mapArray;               //5 - moving element, 0 - empty, 1 falled element
    int thisElement;                //1 - I, 2 - square, 3 - half H
    int nextElement;                //4 - Z, 5 - invertZ
    int position;                   //1-up, 2 - right, 3 - down, 4 -left
    int maxX;                       //Width
    int maxY;                       //Height
    int score;
    boolean isDown;                 //Spawned the element
    boolean isOver;                 //End of game?
    Point eP;                       //Element position
    int[] xInc = {-1, 0, 1, 1, 1, 0, -1, -1};
    int[] yInc = { 1, 1, 1, 0, -1, -1, -1, 0};
    
    public Map(int width, int height){
        this.maxX = width;
        this.maxY = height;
        mapArray = new int[maxX][maxY];
        this.resetMap();
        this.thisElement =5;
        this.nextElement =4;
        this.isDown = false;
        this.isOver = false;
        eP = new Point(0,0);
        score = 0;
        position=2;
        
    }
    
    public void resetMap(){
        for(int y = 0; y < maxY; y++){
            for(int x = 0; x < maxX; x++){
                mapArray[x][y] = 0;
            }
        }
    }
    
    public void printMap(){
        for(int y = 0; y < maxY; y++){
            for(int x = 0; x < maxX; x++){
                System.out.print(mapArray[x][y] + " ");
            }
            System.out.println("");
        }
        System.out.println("---------");
    }
    
    public void step(){
         this.checkLines();
        if(thisElement==1 && !isDown){                                             //I
            for(int i = 0; i<4;i++){                
                if(mapArray[(maxX/2)+i-2][0] == 0){
                    mapArray[(maxX/2)+i-2][0] = 5;                    
                    isDown = true;
                    eP.x = (int)(maxX/2)-1;                          //Center : second point
                   
                    eP.y = 0;                                        // 0 1 0 0  
                } else {
                    isOver = true;
                    return;
                }
            }            
        } else if(thisElement==2 && !isDown){                                      //Square
            if(mapArray[maxX/2][1]==0 && mapArray[(maxX/2)-1][1]==0){
                mapArray[maxX/2][1] = 5; 
                mapArray[(maxX/2)-1][1] = 5;
                mapArray[maxX/2][0] = 5; 
                mapArray[(maxX/2)-1][0] = 5;    
                eP.x = (int)(maxX/2);                      // Center: left lower
                eP.y = 1;                                  //0 0
                isDown = true;                             //1 0
                return;
            } else {
                isOver = true;
                return;
            }
        } else if(thisElement==3 && !isDown){                                      //Half H
            if(mapArray[(maxX/2)-1][1]==0 && mapArray[(maxX/2)+1][1]==0 && mapArray[maxX/2][1]==0){
                for(int i = -1; i < 2 ; i++) mapArray[(maxX/2)+i][1] = 5;
                mapArray[maxX/2][0] = 5;
                isDown = true;                                  //Center: middle of object
                eP.x = (int)(maxX/2);                           //  0
                eP.y = 1;                                       //0 1 0      
                return;
            } else {
                isOver = true;
                return;
            }
        } else if(thisElement==4 && !isDown){                                      //Z
            if(mapArray[maxX/2][1]==0 && mapArray[(maxX/2)+1][1]==0 && mapArray[(maxX/2)-1][0]==0){
                mapArray[maxX/2][1] = 5;
                mapArray[(maxX/2)+1][1] = 5;
                mapArray[maxX/2][0] = 5;
                mapArray[(maxX/2)-1][0] = 5;                     //Center   
                eP.x = (int)(maxX/2);                            // 0 0   
                eP.y = 1;                                        //   1 0 
                isDown = true;
                System.out.println(eP.x + " -- " + eP.y);
                return;
            } else {
                isOver = true;
                return;
            }
        } else if(thisElement==5 && !isDown){                                      //Invert Z
            if(mapArray[maxX/2][1]==0 && mapArray[(maxX/2)-1][1]==0 && mapArray[(maxX/2)+1][0]==0){
                mapArray[maxX/2][1] = 5;
                mapArray[(maxX/2)-1][1] = 5;
                mapArray[(maxX/2)+1][0] = 5;
                mapArray[(maxX/2)][0] = 5;
                eP.x = (int)(maxX/2);                           //  0 0
                eP.y = 1;                                       //0 1   
                isDown = true;
                return;
            } else {
                isOver = true;
                return;
            }
        }
        boolean canFall = true;
        for(int x = 0; x<maxX;x++){
            for(int y = maxY-1; y>0; y--){
                if(mapArray[x][y]== 5 && y == maxY-1){
                    change();
                    return;
                } else if(mapArray[x][y]==1 && mapArray[x][y-1]==5){
                    canFall = false;
                }
            }
        }
        if(canFall) this.fallOne();
        else this.change();
       
        
    }
    
    private void change(){                      // NExt element
        
        for(int y = 0; y < maxY; y++){
            for(int x = 0; x < maxX; x++){
                if(mapArray[x][y] == 5) mapArray[x][y] = 1;
            }
        }
        thisElement = nextElement;
        nextElement = ThreadLocalRandom.current().nextInt(1,6);        
        isDown = false;
        position = 2;
        System.out.println("this element " + thisElement);
        this.checkLines();
       
    }
    
    private void fallOne(){                                 //Fall the block one 
        for(int y = maxY-1; y > 0; y--){
            for(int x = 0; x < maxX; x++){
                if(mapArray[x][y-1]!=1 && mapArray[x][y] != 1){
                    mapArray[x][y] = mapArray[x][y-1];                   
                }
            }
        }
        for(int x = 0; x < maxX; x++) if(mapArray[x][0]==5) mapArray[x][0] = 0;
        eP.y++;
    }
    
    private void checkLines(){                              //Check full lines
        int pointsPerLine = 0;
        boolean noMoreLine = false;
        while(!noMoreLine){
            for(int y = maxY-1; y>=0;y--){
                for(int x = 0; x<maxX; x++) if(mapArray[x][y]==1) pointsPerLine++;
                if(pointsPerLine==maxX){
                    score++;
                    for(int yP = y; yP>0;yP--){
                        for(int x=0; x<maxX;x++){
                            if(yP==0){
                                mapArray[x][yP]=0;                        
                            } else {
                                mapArray[x][yP]=mapArray[x][yP-1];
                            }
                        }
                    }

                } else noMoreLine = true;
                pointsPerLine=0;
            }
        }
    }
    
    public void move(boolean toLeft){           //Move the object left
        boolean moveable = true;
        
        if(toLeft){                             //Move to left
            for(int y = 0; y<maxY;y++){
                for(int x = 0; x<maxX;x++){
                    if(x>0) if(mapArray[x][y]==5 && (mapArray[x-1][y]!=0 && mapArray[x-1][y]!=5)){
                        moveable = false;                        
                    }
                    if(mapArray[x][y]==5 && x == 0){
                        moveable = false;                        
                    }
                }
            }
            if(moveable){
                for(int y = 0; y<maxY;y++){
                    for(int x = 1; x<maxX;x++){
                        if(mapArray[x][y]==5){
                            mapArray[x-1][y]=5;
                            mapArray[x][y]=0;                            
                        }
                    }
                }
                eP.x--;
            }
        } else {                                        //Move to right
            for(int y = 0; y<maxY;y++){
                for(int x = maxX-1; x>=0;x--){
                    if(x<maxX-1) if(mapArray[x][y]==5 && (mapArray[x+1][y]!=0 && mapArray[x+1][y]!=5)){
                        moveable = false;                        
                    }
                    if(mapArray[x][y]==5 && x == maxX-1){
                        moveable = false;                        
                    }
                }
            }
            
            if(moveable){
                for(int y = 0; y<maxY;y++){
                    for(int x = maxX-2; x>=0;x--){
                        if(mapArray[x][y]==5){
                            mapArray[x+1][y]=5;
                            mapArray[x][y]=0;                            
                        }
                    }
                }
                eP.x++;
            }
        }
    }
    
    public void rotate(){
        boolean rotateable = true;
        System.out.println(eP.x + " . " + eP.y);
        if(thisElement==1 && position==2){
            if(eP.y<maxY-3 && eP.y>0 && mapArray[eP.x][eP.y+2]==0 && mapArray[eP.x][eP.y+1]==0 && mapArray[eP.x][eP.y-1]==0){
                mapArray[eP.x][eP.y-1]=5;
                mapArray[eP.x][eP.y+1]=5;
                mapArray[eP.x][eP.y+2]=5;
                mapArray[eP.x-1][eP.y]=0;
                mapArray[eP.x+1][eP.y]=0;
                mapArray[eP.x+2][eP.y]=0;
                position = 3;
            }
        } else if(thisElement==1 && position == 3){
            if(eP.x<maxX-3 && eP.x>0 && mapArray[eP.x-1][eP.y]==0 && mapArray[eP.x+1][eP.y]==0 && mapArray[eP.x+2][eP.y]==0){
                mapArray[eP.x][eP.y-1]=0;
                mapArray[eP.x][eP.y+1]=0;
                mapArray[eP.x][eP.y+2]=0;
                mapArray[eP.x-1][eP.y]=5;
                mapArray[eP.x+1][eP.y]=5;
                mapArray[eP.x+2][eP.y]=5;
                position = 2;
            }
        } else if(thisElement==2){
            return;
        } else {
            List<Integer> leftPosition = new ArrayList();
            this.printMap();
            if(eP.x>0 && eP.x<maxX-1 && eP.y<maxY-1 && eP.y>0) {
                System.out.println(eP.x + " " + eP.y);
                for(int inc = 0; inc<8;inc++){
                    for(int i = 0; i<leftPosition.size();i++){
                        int num = leftPosition.get(i);
                        num--;
                        leftPosition.set(i, num);
                    }
                    if(mapArray[eP.x + xInc[inc]][eP.y + yInc[inc]]==5) leftPosition.add(2);
                    for(int ind = 0; ind < leftPosition.size();ind++){
                        int i = leftPosition.get(ind);
                        if(i==0) {
                            if(mapArray[eP.x + xInc[inc]][eP.y + yInc[inc]]==1){
                                rotateable = false;
                            } else {
                                leftPosition.remove(i);
                            }
                        }
                    }
                }
            
            leftPosition.clear();
            if(rotateable){
                for(int inc = 0; inc<8;inc++){
                    for(int i = 0; i<leftPosition.size();i++){
                        int num = leftPosition.get(i);
                        num--;
                        leftPosition.set(i, num);
                    }
                    if(mapArray[eP.x + xInc[inc]][eP.y + yInc[inc]]==5){
                        leftPosition.add(2);
                        mapArray[eP.x + xInc[inc]][eP.y + yInc[inc]]=0;
                        
                    }
                    for(int ind = 0; ind < leftPosition.size();ind++){
                        int i = leftPosition.get(ind);
                        System.out.println(i + " " + inc);
                        if(i==0) {
                            mapArray[eP.x + xInc[inc]][eP.y + yInc[inc]]=5;
                            System.out.println(eP.x + xInc[inc] + " - " + eP.y + yInc[inc]);
                            leftPosition.remove(i);
                        }
                    }
                }
                int xIndex = eP.x-1;
                while(leftPosition.size()>0){
                    for(int i = 0; i<leftPosition.size();i++){
                        int num = leftPosition.get(i);
                        System.out.println(num + " -.- ");
                        if(num==1){
                            mapArray[xIndex][eP.y+1]=5;  
                           leftPosition.remove(i);
                        } else {
                            num--;
                            leftPosition.set(i, num);
                        }
                    }
                    xIndex++;
                }
            }
             leftPosition.clear();
            this.printMap();
            }
           
        }
    }
    
}
