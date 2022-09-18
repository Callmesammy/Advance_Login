
package Component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;


public class Panel_Slider extends javax.swing.JLayeredPane {

    private final Animator animator;
    private float minate = 1f;
    private boolean isLoading;
    
    public Panel_Slider() {
        initComponents();
        Color cl = new Color(142,100,141);
        setBackground(cl);
        TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void timingEvent(float fraction) {
               minate = fraction;
               repaint();
            }
            
        };
        animator = new Animator(3000, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
    }

    public void start(boolean  show){
        isLoading = show;
        animator.start();
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 347, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 342, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
private float easeInOutBounce(float x){
     double v;
    v =  x < 0.5
  ? (1 - easeOutBounce(1 - 2 * x)) / 2
  : (1 + easeOutBounce(2 * x - 1)) / 2;
    return (float)v;
}
    private float easeOutBounce(float x){
            float n1 = 7.5625f;
    float d1 = 2.75f;
    double v;
    if (x < 1 / d1) {
        v = n1 * x * x;
    } else if (x < 2 / d1) {
        v = n1 * (x -= 1.5 / d1) * x + 0.75;
    } else if (x < 2.5 / d1) {
        v = n1 * (x -= 2.25 / d1) * x + 0.9375;
    } else {
        v = n1 * (x -= 2.625 / d1) * x + 0.984375;
    }
    return (float)v;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        int width = getWidth();
        int height = getHeight();
        float x =easeOutBounce(minate)*width;
        float y = 0;
        int centerT = height/2;
        Path2D.Float p = new Path2D.Float();
        p.moveTo(x, y);
        p.lineTo(x, height);
        p.curveTo(x, height, easeInOutBounce(minate)*width, centerT, x, y);
        g2.fill(p);
        
        g2.dispose();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
