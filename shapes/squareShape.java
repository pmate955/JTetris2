/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes;

import shapes.tetrisShape;

/**
 *
 * @author Mate1
 */
public class squareShape extends tetrisShape{
    
    public squareShape(int maxX, int maxY){
        super();
        this.maxX = maxX;
        this.maxY = maxY;
        position = 2;
    }
    
    public int[][] step(int[][] mapArray){
        if(!spawned){                                      //Square
            if(mapArray[maxX/2][1]==0 && mapArray[(maxX/2)-1][1]==0){
                mapArray[maxX/2][1] = 5; 
                mapArray[(maxX/2)-1][1] = 5;
                mapArray[maxX/2][0] = 5; 
                mapArray[(maxX/2)-1][0] = 5;    
                this.x = (int)(maxX/2);                      // Center: left lower
                this.y = 1;                                  //0 0
                spawned = true;                              //1 0                
            } else {
                isOver = true;
                return mapArray;
            }
        } else {
            boolean canFall = true;
            for(int x = 0; x<maxX;x++){
                for(int y = maxY-1; y>0; y--){
                    if(mapArray[x][y]== 5 && y == maxY-1){
                        goNext = true;
                        return mapArray;
                    } else if(mapArray[x][y]==1 && mapArray[x][y-1]==5){
                        canFall = false;
                    }
                }
            }  
            if(canFall){
                for(int y = maxY-1; y > 0; y--){
                    for(int x = 0; x < maxX; x++){
                        if(mapArray[x][y-1]!=1 && mapArray[x][y] != 1){
                            mapArray[x][y] = mapArray[x][y-1];                   
                        } else if(mapArray[x][y]==5 &&mapArray[x][y-1]==1){
                            mapArray[x][y]=0;
                        }
                    }
                }
                for(int x = 0; x < maxX; x++) if(mapArray[x][0]==5) mapArray[x][0] = 0;
                y++;
                } else this.goNext = true;
            } 
            return mapArray;
            
          
        }
    
        public int[][] rotate(int[][] mapArray){
            return mapArray;
        }
    
    
    
}
