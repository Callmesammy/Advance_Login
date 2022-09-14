
package Component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

//first Jpanel was added and coverted to JLayeredPane, beacuse some features including animations will be added

public class PanelBoader extends javax.swing.JLayeredPane {

    // in this segment we add the necessary components, Timingtarget and Animation ...
    private Animator animate;
    //for the animator 
    private float minate =1f;
    private boolean isLoading;
    
    public PanelBoader() {
        initComponents();
        Color cl = new Color(14,212,100);
        setBackground(cl);
        setOpaque(false);
        TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void timingEvent(float fraction) {
                    minate = fraction;
                    repaint();
            }

           
        };
        animate = new Animator(3000, target);
        // for smooth animations 
        animate.setResolution(0);
        animate.setAcceleration(0.5f);
        animate.setDeceleration(0.5f);
    }
    
    public void  start (){
        animate.start();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
    // here wee add the necessary Maths calculations 
        int width = getWidth();
        int height = getHeight();
        float x = easeInOutCirc(minate)*width;
        float y =0;
        int centerX = width/2;
    // here we use Path2D to add the calculations 
        Path2D.Float p = new Path2D.Float();
        p.moveTo(x, y);
        p.lineTo(x, height);
        p.curveTo(x, height, easeInOutQuint(minate)*width, centerX, x, y);
        g2.fill(p);
        
        g2.dispose();
    }

    private float easeInOutQuint(float x ){
        //copied from easings.net for the animations 
        double v;
        v =x < 0.5 ? 16 * x * x * x * x * x : 1 - Math.pow(-2 * x + 2, 5) / 2;
        return (float)v;
    }
    
     //copied from easings.net for the animations 
    private float easeInOutCirc(float x){
        double v;
        v = x < 0.5
  ? (1 - Math.sqrt(1 - Math.pow(2 * x, 2))) / 2
  : (Math.sqrt(1 - Math.pow(-2 * x + 2, 2)) + 1) / 2;
        return (float)v;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
