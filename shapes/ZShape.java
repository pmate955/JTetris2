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
public class ZShape extends tetrisShape{
    
    public ZShape(int maxX, int maxY){
        super();
        this.maxX = maxX;
        this.maxY = maxY;
        position=2;
    }
    
    public int[][] step(int[][]  mapArray){
        if(!spawned){                                      //Z
            if(mapArray[maxX/2][1]==0 && mapArray[(maxX/2)+1][1]==0 && mapArray[(maxX/2)-1][0]==0){
                mapArray[maxX/2][1] = 5;
                mapArray[(maxX/2)+1][1] = 5;
                mapArray[maxX/2][0] = 5;
                mapArray[(maxX/2)-1][0] = 5;                     //Center   
                this.x = (int)(maxX/2);                            // 0 0   
                this.y = 1;                                        //   1 0 
                spawned = true;
                return mapArray;
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
            if(position==2 && y<maxY-1 && x>0){
                if(mapArray[x-1][y]==0 && mapArray[x-1][y+1]==0){
                    mapArray[x-1][y]=5;
                    mapArray[x-1][y+1]=5;
                    mapArray[x+1][y]=0;
                    mapArray[x-1][y-1]=0;
                    position=3;
                }
            } else if(position==3 && x<maxX-1){
                if(mapArray[x-1][y-1]==0 && mapArray[x+1][y]==0){
                    mapArray[x-1][y]=0;
                    mapArray[x-1][y+1]=0;
                    mapArray[x+1][y]=5;
                    mapArray[x-1][y-1]=5;
                    position=2;
                }
            }
            return mapArray;
        }
    
}
