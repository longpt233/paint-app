package paint;

import properties.ColorDialog;
import properties.StrokeState;
import properties.PaintTool;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import shape.*;

public class PadPaint extends javax.swing.JPanel implements MouseListener, MouseMotionListener {

         private PaintTool paintTool = new PaintTool();
         private ColorDialog colorChooser = new ColorDialog();
         private Line line;
         private Rectangle rect;
         private Oval oval;
         private Pencil pencil;
         private Eraser eraser;
         private Bucket bucket;
         private Point locationEraser = new Point();
         // set net
         private StrokeState strokeState = new StrokeState();
         private Color strokeColor = Color.BLACK;
         private Color fillColor = Color.WHITE;
         //set trang thai
         private PaintState paintState = new PaintState();
         private PaintState redoState = new PaintState();

         private BufferedImage buff_img, org_img;
         private Point start, end;
         private Graphics2D g2d, g2;

         public void setPaintTool(PaintTool paintTool) {
                  this.paintTool = paintTool;
         }

         public void setStrokeState(StrokeState strokeState) {
                  this.strokeState = strokeState;
         }

         public void setColorChooser(ColorDialog colorChooser) {
                  this.colorChooser = colorChooser;
         }

         public PaintState getListState() {
                  return paintState;
         }

         public PadPaint(int width, int height) {
                  initComponents();
                  line = new Line();
                  rect = new Rectangle();
                  oval = new Oval();
                  pencil = new Pencil();

                  start = new Point(-1, -1);
                  end = new Point(-1, -1);
                  //Khoi tao khung anh trang goc
                  org_img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                  g2d = (Graphics2D) org_img.getGraphics();
                  g2d.setColor(new Color(255, 255, 255));
                  g2d.fillRect(0, 0, width, height);
                  g2d.dispose();
                  // khoi tao luong anh viet len 
                  buff_img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                 // khoi taO DATA cho PaintState
                  paintState.setData(org_img);
                  // set trang thai dau tien la dung chuot
                  initState();
                  this.addMouseListener(this);
                  this.addMouseMotionListener(this);
                   this.setSize(new Dimension(width, height));

         }

         public void initState() {
                  paintState.addDrawState(pencil);
//                  paintState.addDrawStep(PaintState.PAINTTING);
 paintState.addDrawStep( );
         }

         public BufferedImage getBuffer() {
                  return buff_img;
         }

         public void refresh() {
                  //Trong qua trinh ve anh goc can co cac tham so de chi ra duoc anh goc bi quay tai mot buoc ve nao do
                  g2d.drawImage(org_img, 0, 0, this);
                  repaint();
         }

         public void undo() {
                  if (paintState.isEmpty()) {
                           return;
                  }
                  //Lay ra trang thai cuoi de tro lai trang thai truoc do
//                  int stepState = paintState.removeEndStep();
                  paintState.removeEndStep();
                  redoState.addDrawStep();
         //         if (stepState == 6) {

                           //khoi tao mot buffer moi de ve len panel
                           buff_img = new BufferedImage(org_img.getWidth(), org_img.getHeight(), BufferedImage.TYPE_INT_RGB);
                           g2d = (Graphics2D) buff_img.getGraphics();
                           refresh();
                           //Xoa + lay ra+ cat vao redo  trang thai cuoi
                           DrawType tempDrawType = paintState.removeEndShape();
                           redoState.addDrawState(tempDrawType);
                           
                           //Ve lai toan bo trang thai cua anh tu luc dau den luc 
                          // int shapeIndex = 0;
 //                          for (int i = 0; i < paintState.getDrawStepList().size(); i++) {
                         for (int i = 0; i < paintState.getStepCount(); i++) {
//
//                                    int inStepState = paintState.getDrawStepList().get(i);
//                                    //Lay tung trang thia cua buoc ve
//                                    switch (inStepState) {
//
//                                             case PaintState.PAINTTING:
                                                      //Neu la painting thi se ve lai toan bo anh tu dau
                                                      DrawType inDrawType = paintState.getListState().get(i);
                                                      if (inDrawType instanceof Line) {
                                                               Line inLine = (Line) inDrawType;
                                                               inLine.draw(g2d);

                                                      } else if (inDrawType instanceof Oval) {
                                                               Oval inOval = (Oval) inDrawType;
                                                               inOval.draw(g2d);
                                                      } else if (inDrawType instanceof Pencil) {
                                                               Pencil inPencil = (Pencil) inDrawType;
                                                               for (int j = 1; j < inPencil.getDraggedPoint().size(); j++) {
                                                                        inPencil.setPoint(inPencil.getDraggedPoint().get(j - 1), inPencil.getDraggedPoint().get(j));
                                                                        inPencil.draw(g2d);
                                                               }

                                                      }else if (inDrawType instanceof Bucket) {
                                                               Bucket inBucket = (Bucket) inDrawType;
                                                               inBucket.draw(buff_img);
                                                      }
//                                                      shapeIndex++;
                                                    //  break;
                                    //}
                          }
            //      }
                  repaint();
         }

         public void redo() {
                  System.out.println("paint.PadPaint.redo()");
                  if (!redoState.isEmpty()) {
   //                        int stepState = redoState.removeEndStep();
//                           paintState.addDrawStep(stepState);
                           paintState.addDrawStep( );
                           DrawType tempDrawType = redoState.removeEndShape();
                           paintState.addDrawState(tempDrawType);
                           buff_img = new BufferedImage(org_img.getWidth(), org_img.getHeight(), BufferedImage.TYPE_INT_RGB);
                           g2d = (Graphics2D) buff_img.getGraphics();
                           refresh();
                           //Ve lai trang thai truoc do
                 //          int shapeIndex = 0;
//                           for (int i = 0; i < paintState.getDrawStepList().size(); i++) {
                           for (int i = 0; i < paintState.getStepCount(); i++) {
//                                    int inStepState = paintState.getDrawStepList().get(i);
//                                    //Lay tung trang thia cua buoc ve
//                                    switch (inStepState) {

                                     //        case PaintState.PAINTTING:
                                                      DrawType inDrawType = paintState.getListState().get(i);
                                                      if (inDrawType instanceof Line) {
                                                               Line inLine = (Line) inDrawType;
                                                               inLine.draw(g2d);

                                                      } else if (inDrawType instanceof Rectangle) {
                                                               Rectangle inRect = (Rectangle) inDrawType;
                                                               inRect.draw(g2d);

                                                      } else if (inDrawType instanceof Oval) {
                                                               Oval inOval = (Oval) inDrawType;
                                                               inOval.draw(g2d);
                                                      } else if (inDrawType instanceof Pencil) {
                                                               Pencil inPencil = (Pencil) inDrawType;
                                                               for (int j = 1; j < inPencil.getDraggedPoint().size(); j++) {
                                                                        inPencil.setPoint(inPencil.getDraggedPoint().get(j - 1), inPencil.getDraggedPoint().get(j));
                                                                        inPencil.draw(g2d);
                                                               }

                                                      } //update by Khanh
                                                      else if (inDrawType instanceof Bucket) {
                                                               Bucket inBucket = (Bucket) inDrawType;
                                                               inBucket.draw(buff_img);
                                                      }
//                                                      shapeIndex++;
                                    }

                                    repaint();
                     //      }
                  }
         }

         public void flush() {
                  start = null;
                  end = null;
                  paintState.removeAll();
                  initState();
                  redoState.removeAll();
                  org_img.flush();
                  buff_img.flush();
                  System.gc();
                  buff_img = null;
                  org_img = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_RGB);
                  g2 = (Graphics2D) org_img.getGraphics();
                  g2.setColor(Color.WHITE);
                  g2.fillRect(0, 0, getSize().width, getSize().height);
                  g2.dispose();
                  paintState.setData(org_img);
//                  isSaved = true;
                  refresh();
                  repaint();
         }

         public void loadImage(BufferedImage img) {
                  loadImage((Image) img);
         }

         public void loadImage(java.awt.Image img) {
                  if (img != null) {
                           flush();
                           org_img = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
                           g2 = (Graphics2D) org_img.getGraphics();
                           g2.drawImage(img, 0, 0, img.getWidth(null), img.getHeight(null), this);
                           g2.dispose();
                           buff_img = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
                           g2 = (Graphics2D) buff_img.getGraphics();
                           g2.drawImage(img, 0, 0, img.getWidth(null), img.getHeight(null), this);
                           g2.dispose();
                          paintState.setData(org_img);
                           g2d = (Graphics2D) buff_img.getGraphics();

                           this.setSize(new Dimension(org_img.getWidth(), org_img.getHeight()));
                           this.setMinimumSize(new Dimension(org_img.getWidth(), org_img.getHeight()));
                           this.setMaximumSize(new Dimension(org_img.getWidth(), org_img.getHeight(null)));
                           this.revalidate();
                           repaint();
                  }
         }

         public void saveImage(File f, String extension) {
                  try {
                           ImageIO.write(buff_img, extension, f);
                  } catch (IOException ex) {
                           System.out.println("paint.PadPaint.saveImage() can not save");
                  }
         }

         //Lay g2d ve thi se bi, nhung ma lay g2 ve thi se khong bi
         @Override
         public void paintComponent(Graphics g) {
                  super.paintComponent(g);
                  g2 = (Graphics2D) g;
                  if (buff_img == null) {
                           buff_img = (BufferedImage) createImage(getSize().width, getSize().height);
                           g2d = (Graphics2D) buff_img.getGraphics();
                           g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                           g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                           refresh();
                  }
                  g2.scale(1, 1);
                  g2.drawImage(buff_img, null, 0, 0);
                  if (start != null && end != null) {
                           switch (paintTool.getDrawMode()) {
                                    case LINE:
                                             line.draw(g2);
                                             break;
                                    case RECT:
                                             rect.draw(g2);
                                             break;
                                    case OVAL:
                                             oval.draw(g2);
                                    case PENCIL:
                                             pencil.draw(g2);
                                             break;
                                    case BUCKET:
                                             bucket.draw(buff_img);
                                             break;

                           }

                  };

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
         public void mouseClicked(MouseEvent e) {
         }

         @Override
         public void mouseEntered(MouseEvent e) {
         }

         @Override
         public void mouseExited(MouseEvent e) {

         }

         @Override
         public void mousePressed(MouseEvent e) {

                  //Xóa hết hình tồn tại troang redoStack
                  if (!redoState.isEmpty()) {
                           redoState.removeAll();
                  }
                  start = e.getPoint();    // start gio la toa do 2 cchieu chu k phai la e 
                  // e gom nhieu thong tin khac nhu nut bam,....
                  switch (paintTool.getDrawMode()) {
                           case LINE:
                                    line = new Line();
                                    line.setStroke(strokeState.getStroke());
                                    line.setStrokeColor(colorChooser.getStrokeColor());
                                    line.addDraggedPoint(start);
                                    line.setPoint(start, start);
                           case RECT:
                                    rect = new Rectangle();
                                    rect.setStroke(strokeState.getStroke());
                                    rect.setStrokeColor(colorChooser.getStrokeColor());
                                    rect.setFillColor(colorChooser.getFillColor());
                                    rect.addDraggedPoint(start);
                                    rect.setPoint(start, start);
                                    break;
                           case OVAL:
                                    oval = new Oval();
                                    oval.setStroke(strokeState.getStroke());
                                    oval.setStrokeColor(colorChooser.getStrokeColor());
                                    oval.setFillColor(colorChooser.getFillColor());
                                    oval.setPoint(start, start);
                                    oval.addDraggedPoint(start);
                                    break;
                           case PENCIL:
                                    pencil = new Pencil();
                                    pencil.setStroke(strokeState.getStroke());
                                    pencil.setStrokeColor(colorChooser.getStrokeColor());
                                    pencil.setPoint(start, start);
                                    pencil.addDraggedPoint(start);
                                    pencil.draw(g2d);
                                    break;
                           case BUCKET:
                                    bucket = new Bucket();
                                    bucket.setStart(start);
                                    bucket.setArrPoint(start);
                                    bucket.setColor(colorChooser.getFillColor());
                                    bucket.draw(buff_img);
                                    paintState.addDrawState(bucket);
                                    break;
                           case ERASER:
                                    eraser = new Eraser();
                                    eraser.setStroke(strokeState.getStroke());
                                    eraser.setStrokeColor(colorChooser.getFillColor());
                                    eraser.setPoint(start, start);
                                    eraser.addDraggedPoint(start);
                                    eraser.draw(g2d);
                                    locationEraser.move((int) (e.getPoint().x), (int) (e.getPoint().y));
                                    break;

                  }

                  repaint();
         }

         @Override
         public void mouseReleased(MouseEvent e) {
                  // nha chuot ra la ve 
                  switch (paintTool.getDrawMode()) {
                           case LINE:
                           //         System.out.println("paint.PadPaint.mouseReleased()");
                                    paintState.addDrawState(line);
                                    line.draw(g2d);
                                    break;
                           case RECT:
                                    paintState.addDrawState(rect);
                                    rect.draw(g2d);
                                    break;
                           case OVAL:
                                    paintState.addDrawState(oval);
                                    oval.draw(g2d);
                                    break;
                           case ERASER:
                                    paintState.addDrawState(eraser);
                                    break;
                           case PENCIL:
                                    paintState.addDrawState(pencil);
                                    break;

                  }//</editor-fold>
//                  paintState.addDrawStep(PaintState.PAINTTING);
            paintState.addDrawStep( );
                  start = null;
                  end = null;
                  repaint();
         }

         @Override
         public void mouseDragged(MouseEvent e) {
                  //chỉ khi dragged chuột thì mới cập nhật hình lên bufer==>Lúc này mới ccaafn phải lưu ảnh
                  end = e.getPoint();
                  switch (paintTool.getDrawMode()) {
                           case LINE:
                   //                 System.out.println("paint.PadPaint.mouseDragged()" + end.toString());
                                    line.setPoint(start, end);
                                    line.addDraggedPoint(end);
                                    break;
                           case RECT:
                                    rect.setPoint(start, end);
                                    rect.addDraggedPoint(end);
                                    break;

                           case OVAL:
                                    oval.setPoint(start, end);
                                    oval.addDraggedPoint(end);
                                    break;
                           case ERASER:
                                    eraser.setPoint(start, end);
                                    eraser.addDraggedPoint(end);
                                    start = end;
                                    eraser.draw(g2d);
                                   locationEraser.move((int) (e.getPoint().x), (int) (e.getPoint().y));
                                    break;
                           case PENCIL:
                                    pencil.setPoint(start, end);
                                    pencil.addDraggedPoint(end);
                                    start = end;
                                    pencil.draw(g2d);
                                    break;

                  }//</editor-fold>
                  repaint();
         }

         @Override
         public void mouseMoved(MouseEvent e) {
         }
}
