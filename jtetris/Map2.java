package jtetris;

import shapes.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Mate - HG8-654
 */
public class Map2 {
    
    int[][] mapArray;               //5 - moving element, 0 - empty, 1 falled element
    int thisElement;                //1 - I, 2 - square, 3 - half H
    int nextElement;                //4 - Z, 5 - invertZ, 6 - L shape
    int position;                   //Block position, 1-up, 2 - right, 3 - down, 4 -left
    int maxX;                       //Width
    int maxY;                       //Height
    int score;
    boolean isDown;                 //Spawned the element
    boolean isOver;                 //End of game?
    tetrisShape obj;
    
    public Map2(int width, int height){
         this.maxX = width;
        this.maxY = height;
        mapArray = new int[maxX][maxY];
        this.resetMap();
        this.thisElement = ThreadLocalRandom.current().nextInt(1,7);
        this.nextElement = ThreadLocalRandom.current().nextInt(1,7);
        this.isDown = false;
        this.isOver = false;
        score = 0;
        position=2;
        if(thisElement==1) obj = new lineShape(maxX,maxY);
        else if(thisElement==2) obj = new squareShape(maxX,maxY);
        else if(thisElement==3) obj = new HShape(maxX,maxY);
        else if(thisElement==4) obj = new ZShape(maxX,maxY);
        else if(thisElement==5) obj = new invZShape(maxX,maxY);
        else if(thisElement==6) obj = new LShape(maxX,maxY);     
    }
    
     public void resetMap(){                                            //Start new game
         this.thisElement = ThreadLocalRandom.current().nextInt(1,7);
        this.nextElement = ThreadLocalRandom.current().nextInt(1,7);
        this.isDown = false;
        this.isOver = false;
        score = 0;
        position=2;
        if(thisElement==1) obj = new lineShape(maxX,maxY);
        else if(thisElement==2) obj = new squareShape(maxX,maxY);
        else if(thisElement==3) obj = new HShape(maxX,maxY);
        else if(thisElement==4) obj = new ZShape(maxX,maxY);
        else if(thisElement==5) obj = new invZShape(maxX,maxY);
        else if(thisElement==6) obj = new LShape(maxX,maxY);   
         
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
     
     public void step(){                                                    //Block falling
            if(obj instanceof lineShape){
                this.resetMap(((lineShape)obj).step(mapArray));                
                if(((lineShape)obj).goNext){
                    this.change();
                    return;
                }
                if(((lineShape)obj).isOver) this.isOver = true;
            } else if(obj instanceof squareShape){
                this.resetMap(((squareShape)obj).step(mapArray));                
                if(((squareShape)obj).goNext){
                    this.change();
                    return;
                }
                if(((squareShape)obj).isOver) this.isOver = true;
            } else if(obj instanceof HShape){
                this.resetMap(((HShape)obj).step(mapArray));                
                if(((HShape)obj).goNext){
                    this.change();
                    return;
                }
                if(((HShape)obj).isOver) this.isOver = true;
            } else if(obj instanceof ZShape){
                this.resetMap(((ZShape)obj).step(mapArray));                
                if(((ZShape)obj).goNext){
                    this.change();
                    return;
                }
                if(((ZShape)obj).isOver) this.isOver = true;
            } else if(obj instanceof invZShape){
                this.resetMap(((invZShape)obj).step(mapArray));                
                if(((invZShape)obj).goNext){
                    this.change();
                    return;
                }
                if(((invZShape)obj).isOver) this.isOver = true;
            } else if(obj instanceof LShape){
                this.resetMap(((LShape)obj).step(mapArray));                
                if(((LShape)obj).goNext){
                    this.change();
                    return;
                }
                if(((LShape)obj).isOver) this.isOver = true;
            }         
     }
     
     public void move(boolean toLeft){                                              //Move block left or right
         if(obj instanceof lineShape) this.resetMap(((lineShape)obj).move(toLeft, this.mapArray));
         else if(obj instanceof squareShape) this.resetMap(((squareShape)obj).move(toLeft, this.mapArray));
         else if(obj instanceof HShape) this.resetMap(((HShape)obj).move(toLeft, mapArray));
         else if(obj instanceof ZShape) this.resetMap(((ZShape)obj).move(toLeft, mapArray));
         else if(obj instanceof invZShape) this.resetMap(((invZShape)obj).move(toLeft, mapArray));
         else if(obj instanceof LShape) this.resetMap(((LShape)obj).move(toLeft, mapArray));
     }
     
     public void rotate(){                                                          //Rotate block
         if(obj instanceof lineShape) this.resetMap(((lineShape)obj).rotate(mapArray));
         else if(obj instanceof squareShape) this.resetMap(((squareShape)obj).rotate(mapArray));
         else if(obj instanceof HShape) this.resetMap(((HShape)obj).rotate(mapArray));
         else if(obj instanceof ZShape) this.resetMap(((ZShape)obj).rotate(mapArray));
         else if(obj instanceof invZShape) this.resetMap(((invZShape)obj).rotate(mapArray));
         else if(obj instanceof LShape) this.resetMap(((LShape)obj).rotate(mapArray));
     }
     
     private void resetMap(int[][] map){                                            //Set the original map to new map
         for(int y = 0; y < maxY; y++){
            for(int x = 0; x < maxX; x++){
                this.mapArray[x][y] = map[x][y];
            }
            
        }
     }
     
     private void change(){                                                          // NExt element , if this is on ground        
        
        thisElement = nextElement;
        nextElement = ThreadLocalRandom.current().nextInt(1,7);        
        this.isDown = false;
        position = 2;       
        if(thisElement==1) obj = new lineShape(maxX,maxY);
        else if(thisElement==2) obj = new squareShape(maxX,maxY);
        else if(thisElement==3) obj = new HShape(maxX,maxY);
        else if(thisElement==4) obj = new ZShape(maxX,maxY);
        else if(thisElement==5) obj = new invZShape(maxX,maxY);  
        else if(thisElement==6) obj = new LShape(maxX,maxY);  
        for(int y = 0; y < maxY; y++){
            for(int x = 0; x < maxX; x++){
                if(mapArray[x][y] == 5) mapArray[x][y] = 1;
            }
        }
        this.checkLines();
       
    }
     
      private void checkLines(){                                              //Check full lines
        int pointsPerLine = 0;  
        boolean found = true;
        while(found){
            found = false;
            for(int y = maxY-1; y>=0;y--){
                for(int x = 0; x<maxX; x++) if(mapArray[x][y]==1) pointsPerLine++;
                if(pointsPerLine==maxX){
                    found = true;
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

                } 
                pointsPerLine=0;
            }
        }
    }
}
