/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtetris;

/**
 *
 * @author Mate1
 */
public class JTetris {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        TetrisGUI t = new TetrisGUI();
        t.setVisible(true);
        t.gameStart();
        
    }
    
}
