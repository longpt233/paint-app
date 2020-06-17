
package properties;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import static javax.swing.SwingConstants.CENTER;


public class StrokeState extends javax.swing.JPanel {

    private float strokeThickness = 3f;//kích thước nét vẽ mặc định là 3
    private BasicStroke stroke = new BasicStroke(strokeThickness);//kiểu + kích thước nét vẽ

    private float[] dash;// kieu net 

    private static final float[] DASH_1 = null;

    private static final float[] DASH_2 = {10f};
    
   
    public StrokeState() {
        initComponents();
        this.setStrokeComboBox();
    }

    
    @SuppressWarnings("unchecked")
         // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
         private void initComponents() {

                  jComboBox2 = new javax.swing.JComboBox();
                  jComboBox3 = new javax.swing.JComboBox();

                  setBackground(new java.awt.Color(153, 153, 153));
                  setForeground(new java.awt.Color(153, 153, 153));

                  jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
                  jComboBox2.addItemListener(new java.awt.event.ItemListener() {
                           public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                    jComboBox2ItemStateChanged(evt);
                           }
                  });

                  jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Stroke 1", "Stroke 2" }));
                  jComboBox3.addItemListener(new java.awt.event.ItemListener() {
                           public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                    jComboBox3ItemStateChanged(evt);
                           }
                  });

                  javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                  this.setLayout(layout);
                  layout.setHorizontalGroup(
                           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                           .addComponent(jComboBox3, 0, 83, Short.MAX_VALUE)
                  );
                  layout.setVerticalGroup(
                           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addGroup(layout.createSequentialGroup()
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                  );
         }// </editor-fold>//GEN-END:initComponents

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        // TODO add your handling code here:
        switch (jComboBox3.getSelectedIndex() + 1) {
            case 1:
                this.setDash(DASH_1);
                this.setStroke(strokeThickness, dash);
                break;
            case 2:
                this.setDash(DASH_2);
                this.setStroke(strokeThickness, dash);
                break;
 
            default:
                break;
        }

    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling codte here:
        this.setStrokeThickness((float) (jComboBox2.getSelectedIndex() + 4));
        this.setStroke(strokeThickness, dash);
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    public BasicStroke getStroke() {
        return this.stroke;
    }

    public void setStroke(float strokeThickness, float[] dash) {
        BasicStroke basicStroke = new BasicStroke(strokeThickness, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_MITER, 10.0f, dash, 2f);
        this.stroke = basicStroke;
        
    }

    public void setDash(float[] dash) {
        this.dash = dash;
    }

    public float[] getDash() {
        return this.dash;
    }

    public float getStrokeThickness() {
        return strokeThickness;
    }

    public void setStrokeThickness(float strokeThickness) {
        this.strokeThickness = strokeThickness;
    }

    private void setStrokeComboBox() {
        DefaultComboBoxModel m = new DefaultComboBoxModel();
        for (int i = 0; i < jComboBox3.getItemCount(); i++) {
            m.addElement(i);
        }
        jComboBox3.setModel(m);
        StrokeComboboxRenderer r = new StrokeComboboxRenderer();
        r.setPreferredSize(new Dimension(50, 15));
        jComboBox3.setRenderer(r);
    }

    public Image getImageIcon(String path) {
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource(path));
        return image;
    }
    
    class StrokeComboboxRenderer extends JLabel implements ListCellRenderer {

        private String fileStrokeIcon[] = new String[]{"stroke1", "stroke2"};
        private ImageIcon strokeIcon[] = new ImageIcon[fileStrokeIcon.length];

        public StrokeComboboxRenderer() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
            for (int i = 0; i < fileStrokeIcon.length; i++) {
                strokeIcon[i] = new ImageIcon(getImageIcon("/icon/" + fileStrokeIcon[i] + ".png"));
            }
        }

        @Override  //class  
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
          
            int selectedIndex = ((Integer) value).intValue();

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            ImageIcon icon = strokeIcon[selectedIndex];
            setIcon(icon);
            setText(null);
            return this;
        }

    }


         // Variables declaration - do not modify//GEN-BEGIN:variables
         private javax.swing.JComboBox jComboBox2;
         private javax.swing.JComboBox jComboBox3;
         // End of variables declaration//GEN-END:variables

}
