package paint;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import library.Library;
import replay.ReplayDialog;

public class MainFrame extends javax.swing.JFrame {

         private BufferedImage buff_img = null;
         private String fileName = "";
         private File fileImage = null;
         private PadPaint padPaint = null;
        private JPanel backgroundPanel = new JPanel();

         public MainFrame() {
                  initComponents();

                  padPaint = new PadPaint(909, 439);
                  backgroundPanel.setLayout(null);
                 backgroundPanel.setBackground(new Color(153,153,153));
                  buff_img = padPaint.getBuffer();
               backgroundPanel.setPreferredSize(new Dimension(buff_img.getWidth() + 120, buff_img.getHeight() + 50));
                  backgroundPanel.add(padPaint);
                  scrollPane.setViewportView(backgroundPanel);
                  padPaint.setColorChooser(colorDialog);
                  padPaint.setPaintTool(paintTool1);
                  padPaint.setStrokeState(strokeState1);
                  padPaint.flush();
                  this.setLocationRelativeTo(null);
                  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                  this.setTitle("BKPaint");

         }

         @SuppressWarnings("unchecked")
         // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
         private void initComponents() {

                  scrollPane = new javax.swing.JScrollPane();
                  jPanel1 = new javax.swing.JPanel();
                  bUndo = new javax.swing.JButton();
                  bRedo = new javax.swing.JButton();
                  paintTool1 = new properties.PaintTool();
                  colorDialog = new properties.ColorDialog();
                  bLibrary = new javax.swing.JButton();
                  bReplay = new javax.swing.JButton();
                  strokeState1 = new properties.StrokeState();
                  jMenuBar1 = new javax.swing.JMenuBar();
                  miNew = new javax.swing.JMenu();
                  newFile = new javax.swing.JMenuItem();
                  openFile = new javax.swing.JMenuItem();
                  saveFile = new javax.swing.JMenuItem();
                  jSeparator1 = new javax.swing.JPopupMenu.Separator();
                  exitFile = new javax.swing.JMenuItem();
                  jMenu4 = new javax.swing.JMenu();
                  jMenuItem10 = new javax.swing.JMenuItem();
                  jMenuItem11 = new javax.swing.JMenuItem();

                  setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                  setBackground(new java.awt.Color(233, 247, 247));

                  jPanel1.setBackground(new java.awt.Color(153, 153, 153));

                  bUndo.setText("undo");
                  bUndo.addActionListener(new java.awt.event.ActionListener() {
                           public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    bUndoActionPerformed(evt);
                           }
                  });

                  bRedo.setText("redo");
                  bRedo.addActionListener(new java.awt.event.ActionListener() {
                           public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    bRedoActionPerformed(evt);
                           }
                  });

                  bLibrary.setText("Library ");
                  bLibrary.addActionListener(new java.awt.event.ActionListener() {
                           public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    bLibraryActionPerformed(evt);
                           }
                  });

                  bReplay.setText("Replay");
                  bReplay.addActionListener(new java.awt.event.ActionListener() {
                           public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    bReplayActionPerformed(evt);
                           }
                  });

                  javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                  jPanel1.setLayout(jPanel1Layout);
                  jPanel1Layout.setHorizontalGroup(
                           jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                             .addComponent(bUndo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                             .addComponent(bRedo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(paintTool1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(12, 12, 12)
                                    .addComponent(strokeState1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(colorDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(30, 30, 30)
                                    .addComponent(bLibrary)
                                    .addGap(39, 39, 39)
                                    .addComponent(bReplay, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(81, Short.MAX_VALUE))
                  );
                  jPanel1Layout.setVerticalGroup(
                           jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addComponent(strokeState1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addGroup(jPanel1Layout.createSequentialGroup()
                                                      .addComponent(bUndo)
                                                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                      .addComponent(bRedo))
                                             .addComponent(paintTool1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                      .addComponent(bLibrary)
                                                      .addComponent(bReplay))
                                             .addComponent(colorDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                  );

                  jMenuBar1.setMargin(new java.awt.Insets(0, 0, 0, 500));
                  jMenuBar1.setPreferredSize(new java.awt.Dimension(20, 21));

                  miNew.setText("File");

                  newFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
                  newFile.setText("New");
                  newFile.addActionListener(new java.awt.event.ActionListener() {
                           public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    newFileActionPerformed(evt);
                           }
                  });
                  miNew.add(newFile);

                  openFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
                  openFile.setText("Open");
                  openFile.addActionListener(new java.awt.event.ActionListener() {
                           public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    openFileActionPerformed(evt);
                           }
                  });
                  miNew.add(openFile);

                  saveFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
                  saveFile.setText("Save");
                  saveFile.addActionListener(new java.awt.event.ActionListener() {
                           public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    saveFileActionPerformed(evt);
                           }
                  });
                  miNew.add(saveFile);
                  miNew.add(jSeparator1);

                  exitFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
                  exitFile.setText("Exit");
                  exitFile.addActionListener(new java.awt.event.ActionListener() {
                           public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    exitFileActionPerformed(evt);
                           }
                  });
                  miNew.add(exitFile);

                  jMenuBar1.add(miNew);

                  jMenu4.setText("Help");

                  jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.CTRL_MASK));
                  jMenuItem10.setText("Help");
                  jMenu4.add(jMenuItem10);

                  jMenuItem11.setText("About");
                  jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
                           public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    jMenuItem11ActionPerformed(evt);
                           }
                  });
                  jMenu4.add(jMenuItem11);

                  jMenuBar1.add(jMenu4);

                  setJMenuBar(jMenuBar1);

                  javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                  getContentPane().setLayout(layout);
                  layout.setHorizontalGroup(
                           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                             .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                             .addComponent(scrollPane))
                                    .addContainerGap())
                  );
                  layout.setVerticalGroup(
                           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                                    .addContainerGap())
                  );

                  pack();
         }// </editor-fold>//GEN-END:initComponents

    private void openFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileActionPerformed
             openAnImage();
    }//GEN-LAST:event_openFileActionPerformed

    public void openAnImage() {
                  JFileChooser filechooser = new JFileChooser("Open A File");
                  int result = 0; 

                  result = filechooser.showOpenDialog(null);
                  if (result == JFileChooser.APPROVE_OPTION) {    // neu ma nguoiu dunug chon j do 
                           File file = filechooser.getSelectedFile();
                           BufferedImage img = null;
                           try {
                                    img = ImageIO.read(new File(file.getPath()));
                           } catch (IOException ex) {
                                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           if (img != null) {
                                    padPaint.loadImage(img);
                                    backgroundPanel.setPreferredSize(new Dimension(img.getWidth() + 20, img.getHeight() + 20));
                           }
                  }
         }
    private void newFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newFileActionPerformed
             padPaint.flush();
    }//GEN-LAST:event_newFileActionPerformed

         private void saveFileActionPerformed(java.awt.event.ActionEvent evt) {
                  saveImageToFile();
         }

         public boolean saveImageToFile() {
                  JFileChooser saveFile = new JFileChooser("Save File");
                  int result = 0;

                  File demo = new File("Untitled.png");
                  saveFile.setSelectedFile(demo);
                  result = saveFile.showSaveDialog(null);

                  if (result == JFileChooser.APPROVE_OPTION) {
                           fileImage = saveFile.getCurrentDirectory();

                           // cai duogn dan nay hdh khac nhau la khac nhau   window "\\"
                           fileName = fileImage.getPath() + "/" + saveFile.getSelectedFile().getName();
                           fileImage = new File(fileName);                                         

                           String extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
                   
                                    padPaint.saveImage(fileImage, extension);
                                    return true;
                      
                  }
                  return false;
         }


    private void exitFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitFileActionPerformed
             System.exit(0);

    }//GEN-LAST:event_exitFileActionPerformed

         private void bUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUndoActionPerformed
                  padPaint.undo();// TODO add your handling code here:
         }//GEN-LAST:event_bUndoActionPerformed

         private void bRedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRedoActionPerformed
                  padPaint.redo();                  // TODO add your handling code here:
         }//GEN-LAST:event_bRedoActionPerformed

         private void colorDialog1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_colorDialog1MouseClicked
                  // TODO add your handling code here:
         }//GEN-LAST:event_colorDialog1MouseClicked

         private void colorDialog1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_colorDialog1PropertyChange
                  // TODO add your handling code here:
         }//GEN-LAST:event_colorDialog1PropertyChange

         private void paintToolPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_paintToolPropertyChange
                  // TODO add your handling code here:
         }//GEN-LAST:event_paintToolPropertyChange

         private void bReplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bReplayActionPerformed
                  System.gc();
                  new ReplayDialog(this, true, padPaint.getListState());

         }//GEN-LAST:event_bReplayActionPerformed

         private void bLibraryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLibraryActionPerformed
                  // TODO add your handling code here:
                  Library library = new Library(this, true);
                  buff_img = library.getBufferedImage();

                  padPaint.loadImage(buff_img);
                  backgroundPanel.setPreferredSize(new Dimension(buff_img.getWidth() + 120, buff_img.getHeight() + 50));

         }//GEN-LAST:event_bLibraryActionPerformed

         private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
                  // TODO add your handling code here:
                  new JOptionPane().showMessageDialog(this, "hello");
         }//GEN-LAST:event_jMenuItem11ActionPerformed

         /**
          * @param args the command line arguments
          */
         public static void main(String args[]) {
                  new MainFrame().setVisible(true);

         }
         // Variables declaration - do not modify//GEN-BEGIN:variables
         private javax.swing.JButton bLibrary;
         private javax.swing.JButton bRedo;
         private javax.swing.JButton bReplay;
         private javax.swing.JButton bUndo;
         private properties.ColorDialog colorDialog;
         private javax.swing.JMenuItem exitFile;
         private javax.swing.JMenu jMenu4;
         private javax.swing.JMenuBar jMenuBar1;
         private javax.swing.JMenuItem jMenuItem10;
         private javax.swing.JMenuItem jMenuItem11;
         private javax.swing.JPanel jPanel1;
         private javax.swing.JPopupMenu.Separator jSeparator1;
         private javax.swing.JMenu miNew;
         private javax.swing.JMenuItem newFile;
         private javax.swing.JMenuItem openFile;
         private properties.PaintTool paintTool1;
         private javax.swing.JMenuItem saveFile;
         private javax.swing.JScrollPane scrollPane;
         private properties.StrokeState strokeState1;
         // End of variables declaration//GEN-END:variables
}
