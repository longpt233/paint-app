package replay;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import paint.PaintState;

public class ReplayDialog extends javax.swing.JDialog {

         private PaintState paintState;
         private ImageIcon playIcon;
         private ImageIcon pauseIcon;
         private ImageIcon stopIcon;
         private ReplayPanel replayPanel;
         private JPanel containerPanel;
         private BufferedImage buff_img;

         public ReplayDialog(java.awt.Frame parent, boolean modal, PaintState paintState) {
                  super(parent, modal);
                  playIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/play.png")));
                  pauseIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/pause.png")));
                  stopIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/stop.png")));

                  this.paintState = paintState;
                  initComponents();
                  bPlay.setIcon(pauseIcon);
                  containerPanel = new JPanel();
                  containerPanel.setLayout(null);
                  
                  replayPanel = new ReplayPanel();
                  replayPanel.setPaintState(paintState);
                   replayPanel.setButton(bPlay);
                   
                  buff_img = replayPanel.getBuffer();
                  containerPanel.setPreferredSize(new Dimension(replayPanel.getWidth() - 50, replayPanel.getHeight()));
                  containerPanel.add(replayPanel);
                  scrollPane.setViewportView(containerPanel);
                  containerPanel.validate();

//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                  this.setTitle("Untitled-Replay Dialog");
                  this.setLocationRelativeTo(null);
                  this.setVisible(true);
         }

         /**
          * This method is called from within the constructor to initialize the form. WARNING: Do
          * NOT modify this code. The content of this method is always regenerated by the Form
          * Editor.
          */
         @SuppressWarnings("unchecked")
         // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
         private void initComponents() {

                  buttonGroup1 = new javax.swing.ButtonGroup();
                  scrollPane = new javax.swing.JScrollPane();
                  speed = new javax.swing.JSlider();
                  bPlay = new javax.swing.JToggleButton();
                  jButton1 = new javax.swing.JButton();
                  jMenuBar1 = new javax.swing.JMenuBar();

                  setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                  addWindowListener(new java.awt.event.WindowAdapter() {
                           public void windowClosing(java.awt.event.WindowEvent evt) {
                                    formWindowClosing(evt);
                           }
                  });

                  speed.setValueIsAdjusting(true);
                  speed.addChangeListener(new javax.swing.event.ChangeListener() {
                           public void stateChanged(javax.swing.event.ChangeEvent evt) {
                                    speedStateChanged(evt);
                           }
                  });

                  buttonGroup1.add(bPlay);
                  bPlay.addActionListener(new java.awt.event.ActionListener() {
                           public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    bPlayActionPerformed(evt);
                           }
                  });

                  jButton1.setText("Save");
                  jButton1.addActionListener(new java.awt.event.ActionListener() {
                           public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    jButton1ActionPerformed(evt);
                           }
                  });
                  setJMenuBar(jMenuBar1);

                  javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                  getContentPane().setLayout(layout);
                  layout.setHorizontalGroup(
                           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                                             .addGroup(layout.createSequentialGroup()
                                                      .addGap(77, 77, 77)
                                                      .addComponent(jButton1)
                                                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                      .addComponent(bPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                      .addGap(273, 273, 273)
                                                      .addComponent(speed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                  );
                  layout.setVerticalGroup(
                           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addGroup(layout.createSequentialGroup()
                                    .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addGroup(layout.createSequentialGroup()
                                                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                               .addComponent(speed, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                               .addComponent(bPlay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                      .addContainerGap())
                                             .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                      .addComponent(jButton1)
                                                      .addGap(23, 23, 23))))
                  );

                  jButton1.getAccessibleContext().setAccessibleName("Save");

                  pack();
         }// </editor-fold>//GEN-END:initComponents

    private void bPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPlayActionPerformed
             containerPanel.setSize(new Dimension(replayPanel.getSize()));
             containerPanel.setMinimumSize(new Dimension(replayPanel.getSize()));
             if (replayPanel.isPlaying()) {
                      bPlay.setIcon(pauseIcon);
                      replayPanel.pauseReplay();

             } else {
                      bPlay.setIcon(playIcon);
                      replayPanel.startReplay();
             }
    }//GEN-LAST:event_bPlayActionPerformed

    private void speedStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_speedStateChanged
             changeSpeed(speed.getValue());
    }//GEN-LAST:event_speedStateChanged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

         private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                  // TODO add your handling code here:
                  saveFile();
         }//GEN-LAST:event_jButton1ActionPerformed


         // Variables declaration - do not modify//GEN-BEGIN:variables
         private javax.swing.JToggleButton bPlay;
         private javax.swing.ButtonGroup buttonGroup1;
         private javax.swing.JButton jButton1;
         private javax.swing.JMenuBar jMenuBar1;
         private javax.swing.JScrollPane scrollPane;
         private javax.swing.JSlider speed;
         // End of variables declaration//GEN-END:variables
    public void changeSpeed(int value) {
                  replayPanel.setDelay(value);
         }

         public void saveFile() {
                  JFileChooser fileSave = new JFileChooser("Save a replay file");
                  int select = 0;
                  File init = new File("Untitled.rep");
                  fileSave.setSelectedFile(init);
                  select = fileSave.showSaveDialog(null);     //Hien thi filechoser cung voi ten mac dinh
                  if (select == JFileChooser.APPROVE_OPTION) {
                           File file = fileSave.getCurrentDirectory();
                           String fileName = file.getPath() + "/" + fileSave.getSelectedFile().getName();
                           try {
                                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
                                    oos.writeObject(paintState);
                                    oos.close();
                           } catch (IOException ex) {
                                    JOptionPane.showMessageDialog(null, "Save file error!", "Error", JOptionPane.ERROR_MESSAGE);
                                    Logger.getLogger(ReplayDialog.class.getName()).log(Level.SEVERE, null, ex);
                           }
                  }
         }
}
