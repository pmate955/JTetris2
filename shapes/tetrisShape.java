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
public abstract class tetrisShape {
    
    int x;
    int y;
    int maxX;
    int maxY;
    int shapeNumber;
    int position;
    public boolean spawned;
    public boolean isOver;
    public boolean goNext;
    
    abstract int[][] step(int[][] mapArray);
    abstract int[][] rotate(int[][] mapArray);
    
    
    public tetrisShape(){
        position = 2;
        spawned = false;
        isOver = false;
        goNext = false;
    }
    
    public int[][] move(boolean toLeft, int[][] mapArray){
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
                this.x--;
                
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
                this.x++;
            }
        }
        return mapArray;
    }
    
    private int[][] fallOne(int[][] mapArray){                                 //Fall the block one 
        for(int y = maxY-1; y > 0; y--){
            for(int x = 0; x < maxX; x++){
                if(mapArray[x][y-1]!=1 && mapArray[x][y] != 1){
                    mapArray[x][y] = mapArray[x][y-1];                   
                }
            }
        }
        for(int x = 0; x < maxX; x++) if(mapArray[x][0]==5) mapArray[x][0] = 0;
        y++;
        return mapArray;
    }
}
