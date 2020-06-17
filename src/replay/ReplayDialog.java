package replay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout; 
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger; 
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane; 
import paint.PaintState;

public class ReplayDialog extends javax.swing.JDialog {
         
         private PaintState paintState;
         private ImageIcon playIcon;
         private ImageIcon pauseIcon;
         private ReplayPanel replayPanel;
         private JPanel containerPanel;
         private BufferedImage buff_img;
         
         private javax.swing.JToggleButton bPlay;
         private javax.swing.JButton jButton1;
         private javax.swing.JSlider speed;
         private JScrollPane scrollPane;

         public ReplayDialog(java.awt.Frame parent, boolean modal, PaintState paintState) {
                  super(parent, modal);
                  playIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/play.png")));
                  pauseIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/pause.png")));
                  
                  this.paintState = paintState;
                  initComponents();
                  containerPanel = new JPanel();
                  containerPanel.setLayout(null);
                  
                  replayPanel = new ReplayPanel(paintState);
                  
                  buff_img = replayPanel.getBuffer();
                  
                  speed = new javax.swing.JSlider();
                  speed.setValueIsAdjusting(true);
                  speed.setSize(new Dimension(200, 50));
                  speed.addChangeListener(new javax.swing.event.ChangeListener() {
                           public void stateChanged(javax.swing.event.ChangeEvent evt) {
                                    changeSpeed(speed.getValue());
                           }
                  });
                  bPlay = new javax.swing.JToggleButton();
                  bPlay.setSize(new Dimension(50, 50));
                  bPlay.setIcon(pauseIcon);
                  bPlay.addActionListener(new java.awt.event.ActionListener() {
                           public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    containerPanel.setSize(new Dimension(replayPanel.getSize()));
                                    containerPanel.setMinimumSize(new Dimension(replayPanel.getSize()));
                                    if (replayPanel.isPlaying()) {
                                             bPlay.setIcon(pauseIcon);
                                             replayPanel.pauseReplay();
                                             
                                    } else {
                                             bPlay.setIcon(playIcon);
                                             replayPanel.startReplay();
                                    }
                           }
                  });
                  jButton1 = new JButton();
                  jButton1.setText("Save");
                  jButton1.setSize(new Dimension(50, 50));
                  jButton1.addActionListener(new java.awt.event.ActionListener() {
                           public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    saveFile();
                           }
                  });

                 
                  containerPanel.setBackground(new Color(153, 153, 153));
                  containerPanel.setPreferredSize(new Dimension(buff_img.getWidth() + 120, buff_img.getHeight() + 50));
                  containerPanel.add(replayPanel);
                  
                  scrollPane = new JScrollPane();
                  containerPanel.validate();
                  scrollPane.setViewportView(containerPanel);
                  
                  setLayout(new BorderLayout());
                  
                  add(scrollPane,BorderLayout.CENTER);
                  
                  JPanel tempJPanel=new JPanel();
                  tempJPanel.setLayout(new FlowLayout());
                  tempJPanel.add(bPlay );
                  tempJPanel.add(speed );
                  tempJPanel.add(jButton1);
                  
                  add(tempJPanel,BorderLayout.SOUTH);
                  
                  setSize(700,600);  // them 50 add nut 
                  setResizable(false);
                  pack();
                  
                  this.setTitle("Untitled-Replay Dialog");
                  this.setLocationRelativeTo(null);
                  this.setVisible(true);
         }
         
         @SuppressWarnings("unchecked")
         // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
         private void initComponents() {

                  setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                  addWindowListener(new java.awt.event.WindowAdapter() {
                           public void windowClosing(java.awt.event.WindowEvent evt) {
                                    formWindowClosing(evt);
                           }
                  });

                  pack();
         }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing


         // Variables declaration - do not modify//GEN-BEGIN:variables
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
