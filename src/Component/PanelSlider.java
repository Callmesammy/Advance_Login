
package Component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 *in this segment wee the necessary feature to enable the slider animation to function properly 
 * 1. we remove the JPanel and add the JlayeredPane segment 
 */
public class PanelSlider extends javax.swing.JLayeredPane {

    // Now we add the neecessary segments to the LayeredPane 
    
    private final Animator animate;
    private boolean isLoading;
    private float minate = 1f;
    private final PanelLogin login;
    private final MigLayout layout;
    private final PanelLoading loading;
    
    public PanelSlider() {
        initComponents();
        setPreferredSize(new Dimension(350,450));
        layout = new MigLayout("inset 0", "[fill]", "[fill]");
        login = new PanelLogin();
        loading = new PanelLoading();
        setLayout(layout);
        Color cl = new Color(14,150,222);
        setBackground(cl);
        TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void begin() {
                if (isLoading) {
                    loading.setVisible(true);
                }else{
                    login.setVisible(true);
                }
            }
            
            @Override
            public void timingEvent(float fraction) {
               minate =fraction;
               int width = getWidth();
               float a = easeOutBounce(fraction);
               int x = (int) (a *width);
               layout.setComponentConstraints(loading, "pos " +x+ " 0 100% 100%");
               repaint();
               revalidate();
            }

            @Override
            public void end() {
                if (isLoading) {
                      login.setVisible(false);
                }else{
                    loading.setVisible(false); 
                }
            }
            
            
        };
        animate = new Animator(1000, target);
        animate.setResolution(0);
        animate.setAcceleration(0.5f);
        animate.setDeceleration(0.5f);
        add(login);
        add(loading, " pos  0 0 0 0, w 0! ");
        
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
            v= n1 * (x -= 2.625 / d1) * x + 0.984375;
        }
        if (isLoading) {
           return (float) (v);
       }else
            return (float)(1f -v);
    }
   
   private float easeInBounce(float x){
       double v;
       v = 1 - easeOutBounce(1 - x);
       if (isLoading) {
           return (float)(v);
       }else{
           return (float)(1f -v);
       }
   }
   
   public void isLoading(boolean show){
       isLoading = show;
       animate.start();
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 364, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 382, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (isLoading) {
                    Graphics2D g2 =(Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        int width = getWidth();
        int height = getHeight();
        float x = easeOutBounce(minate)*width;
        float y = 0;
        int centerX = width/2;
        Path2D.Float p = new Path2D.Float();
        p.moveTo(x, y);
        p.lineTo(x, height);
        p.curveTo(x, height, easeInBounce(minate)*width, centerX, x, y);
        g2.fill(p);
        g2.dispose();  
        }
        
        
    }
    
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
