/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes;

/**
 *
 * @author Mate1
 */
public class HShape extends tetrisShape{
    
    public HShape(int maxX, int maxY){
        super();
        this.maxX = maxX;
        this.maxY = maxY;
        position = 1;
    }
    
    public int[][] step(int[][] mapArray){
        if(!spawned){                                      
            if(mapArray[(maxX/2)-1][1]==0 && mapArray[(maxX/2)+1][1]==0 && mapArray[maxX/2][1]==0){
                for(int i = -1; i < 2 ; i++) mapArray[(maxX/2)+i][1] = 5;
                mapArray[maxX/2][0] = 5;
                spawned = true;                                  //Center: middle of object
                x = (int)(maxX/2);                           //  0
                y = 1;                                       //0 1 0      
                
            } else {
                isOver = true;                
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
            if(position==1 && y<maxY-1 && x > 0){
                if(mapArray[x][y+1]==0){
                    mapArray[x][y+1]=5;
                    mapArray[x-1][y]=0;
                    position++;
                }
            } else if(position==2 && x>0){
                if(mapArray[x-1][y]==0){
                    mapArray[x-1][y]=5;
                    mapArray[x][y-1]=0;
                    position++;
                }
            } else if(position==3 && y>0){
                if(mapArray[x][y-1]==0){
                    mapArray[x][y-1]=5;
                    mapArray[x+1][y]=0;
                    position++;
                }                        
            } else if(position==4 && x<maxX-1){
                if(mapArray[x+1][y]==0){
                    mapArray[x+1][y]=5;
                    mapArray[x][y+1]=0;
                    position=1;
                }
            }
            return mapArray;
        }
}
