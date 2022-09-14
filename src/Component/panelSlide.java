
package Component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;


public class panelSlide extends javax.swing.JLayeredPane {

   private Animator animate;
   private float minate;
   private boolean isAction;
   
    public panelSlide() {
        initComponents();
        setOpaque(false);
        
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
       int width = getWidth();
       int hight = getHeight();
       float x = 0;
       float y = 0;
       int centerX = width/2;
        
        
    }

private double easeInBack(float x){
  float c1 = 1.70158f;
float c3 = c1 + 1;
double v;
v = c3 * x * x * x - c1 * x * x;
return (float)v;
}

private double  easeOutQuart(float x){
    double v;
    v= 1 - Math.pow(1 - x, 4);
    return (float)v;
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
