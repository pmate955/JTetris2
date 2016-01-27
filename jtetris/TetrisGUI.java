/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtetris;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Mate1
 */
public class TetrisGUI extends javax.swing.JFrame implements Runnable{

    boolean decrease = false;
    boolean threadRun = true;
    boolean isEnd = false;     
    boolean isPaused = false;
    ImageIcon empty = new ImageIcon(this.getClass().getResource("/images/back.jpg"));  
    ImageIcon tile = new ImageIcon(this.getClass().getResource("/images/tile.png"));
    ImageIcon nextLine = new ImageIcon(this.getClass().getResource("/images/nextL.jpg"));
    ImageIcon nextSQ = new ImageIcon(this.getClass().getResource("/images/nextSQ.jpg"));
    ImageIcon nextH = new ImageIcon(this.getClass().getResource("/images/nextH.jpg"));
    ImageIcon nextZ = new ImageIcon(this.getClass().getResource("/images/nextZ.jpg"));
    ImageIcon nextinvZ = new ImageIcon(this.getClass().getResource("/images/nextinvZ.jpg"));
    ImageIcon nextLShape = new ImageIcon(this.getClass().getResource("/images/nextLShape.jpg"));
    JLabel[][] l = new JLabel[8][18];
    JLabel pointLabel;
    Map2 m;                                              //Map class
    Thread falling;                                     //Thread for update
    
    public TetrisGUI() {
        JLabel backgr = null;
         try {
                backgr = new JLabel(new ImageIcon(ImageIO.read(this.getClass().getResource("/images/backgr.jpg"))));    		
                
                this.setContentPane(backgr);
                backgr.setLayout(new FlowLayout());
                
    	} catch (IOException e) {
    		      System.out.println("Hiba");
    	}
        initComponents();
        this.getContentPane().setBackground(Color.BLACK);
        m = new Map2(8,18);
        int startX = 0;
        int startY = 0;
        for(int y = 0; y<18;y++){                   //Create jlabels            
            startX=0;
            for(int x = 0; x<8;x++){                
                JLabel jl = new JLabel("");
                jl.setSize(30, 30);
                jl.setLocation(startX, startY);
                startX+=30;
                l[x][y] = jl;
                this.add(jl);
            }
            startY+=30;
        }
        
        this.addKeyListener(new KeyAdapter(){       //Keyboard
            public void keyPressed(KeyEvent e){
                int keyCode = e.getKeyCode();
                if((keyCode==38 || keyCode==87)&& !isEnd){
                    m.rotate();
                }
                else if((keyCode==40 || keyCode==83) && !isEnd){
                    m.step();
                }
                else if((keyCode==39 || keyCode==68)&& !isEnd){
                    m.move(false);
                }
                else if((keyCode==37 || keyCode==65)&& !isEnd){
                    m.move(true);
                }
                else if(keyCode==32 && !isEnd){
                    m.rotate();
                }
                else if(keyCode==80 && !isEnd){
                    isPaused = true;
                }
            }
        });
        
        falling = new Thread(this, "Fall");        //Start update thread
        falling.start(); 
    }
    
    public void run(){
        while(falling != null && threadRun){
            this.writeOut();            
        }
    }

    
    private void writeOut(){                    //Update the labels
        pointL.setText("Score: " + m.score );    
        if(m.nextElement==1) nextLabel.setIcon(nextLine);
        else if(m.nextElement==2) nextLabel.setIcon(nextSQ);
        else if(m.nextElement==3) nextLabel.setIcon(nextH);
        else if(m.nextElement==4) nextLabel.setIcon(nextZ);
        else if(m.nextElement==5) nextLabel.setIcon(nextinvZ);
        else if(m.nextElement==6) nextLabel.setIcon(nextLShape);
        for(int y = 0; y<18;y++){
            for(int x = 0; x<8;x++){
                l[x][y].setIcon(this.intToImg(m.mapArray[x][y]));
            }
        }
    }
    
    private ImageIcon intToImg(int i){
        if(i==5 || i==1) return tile;
        else return empty;
    }
    
    public void gameStart(){
        String[] buttons2 = { "Chill", "Normal", "Hard"};    
        int speedValue= JOptionPane.showOptionDialog(null, "Choose difficulty!", "Starting",
        JOptionPane.WARNING_MESSAGE, 0, null, buttons2, buttons2[1]);
        int timer = 1000;
        if(speedValue==1){
            timer = 500;
            decrease = true;
        }
        else if(speedValue==2) timer = 200;
        int levelup = 10;
        while(!isEnd){
            if(isPaused){
                JOptionPane.showMessageDialog(this, "Press ok to continue!", "Paused", JOptionPane.ERROR_MESSAGE);
                isPaused = false;
            } else {
                m.step();
                this.isEnd = m.isOver;
                try{
                    Thread.sleep(timer);
                } catch (Exception e){
                    System.out.println("Error while sleep!");
                }
                if(decrease && m.score>=levelup){
                    if(timer>200) timer-=50;
                    levelup+=10;
                }
            }
        }
        String[] buttons = { "Again", "Exit"};      //Exit window
        int returnValue = JOptionPane.showOptionDialog(null, "Score: " + m.score, "Game Over",
        JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[0]);
        if(returnValue == 0){
            m.resetMap();
            isEnd = false;
            this.gameStart();
        } else {
            threadRun = false;
            this.dispose();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pointL = new javax.swing.JLabel();
        nextLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JTetris");
        setBackground(new java.awt.Color(102, 102, 102));
        setResizable(false);

        pointL.setBackground(new java.awt.Color(0, 0, 0));
        pointL.setFont(new java.awt.Font("LCD", 0, 24)); // NOI18N
        pointL.setForeground(new java.awt.Color(51, 255, 0));
        pointL.setText("jLabel1");
        pointL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pointL.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(245, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(nextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(pointL)
                        .addGap(39, 39, 39))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(pointL)
                .addGap(138, 138, 138)
                .addComponent(nextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(141, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TetrisGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TetrisGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TetrisGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TetrisGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TetrisGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel nextLabel;
    private javax.swing.JLabel pointL;
    // End of variables declaration//GEN-END:variables
}
