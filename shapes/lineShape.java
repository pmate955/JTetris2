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
public class lineShape extends tetrisShape{
    
    public lineShape(int maxX, int maxY){
        super();
        this.maxX = maxX;
        this.maxY = maxY;
        position = 2;
    }
    
    public int[][] step(int[][] mapArray){
        if(!spawned){
            for(int i = 0; i<4;i++){                
                    if(mapArray[(maxX/2)+i-2][0] == 0){
                        mapArray[(maxX/2)+i-2][0] = 5;                    
                        spawned = true;
                        x = (int)(maxX/2)-1;                          //Center : second point
                        y = 0;                                        // 0 1 0 0  
                    } else {
                        isOver = true;
                    }
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
        if(position==2){
            if( y<maxY-2 &&  y>0 && mapArray[ x][ y+2]==0 && mapArray[ x][ y+1]==0 && mapArray[ x][ y-1]==0){
                mapArray[ x][ y-1]=5;
                mapArray[ x][ y+1]=5;
                mapArray[ x][ y+2]=5;
                mapArray[ x-1][ y]=0;
                mapArray[ x+1][ y]=0;
                mapArray[ x+2][ y]=0;
                position = 3;
            }
        } else if(position == 3){
            if( x<maxX-2 &&  x>0 && mapArray[ x-1][ y]==0 && mapArray[ x+1][ y]==0 && mapArray[ x+2][ y]==0){
                mapArray[ x][ y-1]=0;
                mapArray[ x][ y+1]=0;
                mapArray[ x][ y+2]=0;
                mapArray[ x-1][ y]=5;
                mapArray[ x+1][ y]=5;
                mapArray[ x+2][ y]=5;
                position = 2;
            }
        }
        return mapArray;
    }
    
}
