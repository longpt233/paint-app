/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;

import choose.PaintTool;
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
import choose.ColorDialog;
import choose.StrokeState;
 
import shape.Bucket;
 
import shape.DrawType;
import shape.Eraser;
import shape.Line;
import shape.Oval;
import shape.Pencil;
 
import shape.Rectangle;
public class PadPaint extends javax.swing.JPanel implements MouseListener, MouseMotionListener {

    /**
     * Creates new form PadPaint
     */
    private PaintTool paintTool = new PaintTool();
    private ColorDialog colorChooser = new ColorDialog();
    private Line line;
    private Rectangle rect;
    private Oval oval;
    private Pencil pencil;
    private Eraser eraser;
    
    private Point startPolygon = null;
    private Point endPolygon = null;
    private Bucket bucket;
    private Point locationEraser = new Point();
     

    private StrokeState strokeState = new StrokeState();
    
    private PaintState paintState = new PaintState();
    private PaintState redoState = new PaintState();
    private BufferedImage buff_img, org_img, cpy_img;
    private Point start, end, temp;
    private Graphics2D g2d, g2;
    private Color strokeColor = Color.BLACK;
    private Color fillColor = Color.WHITE;
    private int width = 0;
    private int height = 0;
    private boolean isSaved = true;
    private double zoom = 1;

    private boolean draggingMouse = false;

     

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

    public boolean isSaving() {
        return isSaved;
    }

    public double getZoom() {
        return zoom;
    }

    public Point getPoint(Point location) {
        if (location == null) {
            return null;
        }
        Point p = new Point((int) (location.x / zoom), (int) (location.y / zoom));
        return p;
    }
    public PadPaint(int width, int height) {
        initComponents();
        line = new Line();
        rect = new Rectangle();
        oval = new Oval();
        pencil = new Pencil();
         
        start = new Point(-1, -1);
        end = new Point(-1, -1);
        this.width = width;
        this.height = height;
        this.setSize(new Dimension(width, height));
        //Khoi tao anh
        org_img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        g2d = (Graphics2D) org_img.getGraphics();
        g2d.setColor(new Color(255, 255, 255));
        g2d.fillRect(0, 0, width, height);
        g2d.dispose();

        //update by hung
        buff_img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        g2 = (Graphics2D) buff_img.getGraphics();
        g2.setColor(new Color(255, 255, 255));
        g2.fillRect(0, 0, width, height);
        g2.dispose();

        paintState.setData(org_img);
        initState();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

    }

 


    public void initState() {
        Pencil pencil = new Pencil();
        pencil.addDraggedPoint(new Point(-1, -1));
        pencil.addDraggedPoint(new Point(-1, -1));
        pencil.setPoint(new Point(-1, -1), new Point(-1, -1));
        BasicStroke stroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                1.0f, null, 2.0f);
        paintState.addDrawState(pencil);
        paintState.addDrawStep(PaintState.PAINTTING);
    }

    public BufferedImage getBuffer() {
        return buff_img;
    }

    public void refresh() {
        //Trong qua trinh ve anh goc can co cac tham so de chi ra duoc anh goc bi quay tai mot buoc ve nao do
        g2d.drawImage(org_img, 0, 0, this);
        repaint();

    }

    public void addDrawStep(int drawStep) {
        paintState.addDrawStep(drawStep);
    }

    public void undo() {
//        toolChange();
        if (paintState.isEmpty()) {
            return;
        }
        //Lay ra trang thai cuoi de tro lai trang thai truoc do
        int stepState = paintState.removeEndStep();
        redoState.addDrawStep(stepState);
        //lay ra trang thai cua buoc ve cuoi cung
        switch (stepState) {
           
            case PaintState.PAINTTING:
                //Neu la painting thi ve lai tu dau den trang thai truoc do
                //Ve lai anh goc
                //khoi tao mot buffer moi de ve len panel
                buff_img = new BufferedImage(org_img.getWidth(), org_img.getHeight(), BufferedImage.TYPE_INT_RGB);
                g2d = (Graphics2D) buff_img.getGraphics();
                refresh();
                //Xoa trang thai cuoi
                DrawType drawType = paintState.removeEndShape();
                redoState.addDrawState(drawType);
                //Ve lai toan bo trang thai cua anh tu luc dau den luc 
                int shapeIndex = 0;
                for (int i = 0; i < paintState.getDrawStepList().size(); i++) {
                    int inStepState = paintState.getDrawStepList().get(i);
                    //Lay tung trang thia cua buoc ve
                    switch (inStepState) {
                    
                        case PaintState.PAINTTING:
                            //Neu la painting thi se ve lai toan bo anh tu dau
                            DrawType inDrawType = paintState.getListState().get(shapeIndex);
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

                            } //update by Khanh
                            else if (inDrawType instanceof Bucket) {
                                Bucket inBucket = (Bucket) inDrawType;
                                inBucket.draw(buff_img);
                            }
                            shapeIndex++;
                            break;
                    }
                }

                break;
        }

        repaint();
    }

    public void redo() {
        if (!redoState.isEmpty()) {
            int stepState = redoState.removeEndStep();
            paintState.addDrawStep(stepState);
            DrawType drawType0 = redoState.removeEndShape();
            paintState.addDrawState(drawType0);
            buff_img = new BufferedImage(org_img.getWidth(), org_img.getHeight(), BufferedImage.TYPE_INT_RGB);
            g2d = (Graphics2D) buff_img.getGraphics();
            refresh();
            //Ve lai trang thai truoc do
            int shapeIndex = 0;
            for (int i = 0; i < paintState.getDrawStepList().size(); i++) {
                int inStepState = paintState.getDrawStepList().get(i);
                //Lay tung trang thia cua buoc ve
                switch (inStepState) {
                    
                    case PaintState.PAINTTING:
                        DrawType inDrawType = paintState.getListState().get(shapeIndex);
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
                        shapeIndex++;
                }

                repaint();
            }
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
        org_img = null;
        buff_img = null;
        org_img = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_RGB);
        g2 = (Graphics2D) org_img.getGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getSize().width, getSize().height);
        g2.dispose();
        paintState.setData(org_img);
        isSaved = true;
        refresh();
        repaint();
    }

    public void loadImage(BufferedImage img) {
        //Khi anh vua moi duoc mo thi khong can phai luu
        isSaved = true;
        //setZoom(60);
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
            isSaved = true;
        } catch (IOException ex) {
            isSaved = false;
        }
    }

    //Lay g2d ve thi se bi, nhung ma lay g2 ve thi se khong bi
//<editor-fold defaultstate="collapsed" desc="paint">

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
//        setSizeStatusInfo();
        g2.scale(zoom, zoom);
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
//</editor-fold>

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        start = e.getPoint();
        temp = e.getPoint();

        switch (paintTool.getDrawMode()) {
            //<editor-fold defaultstate="collapsed" desc="#">
            case LINE:
                line = new Line();
                line.setStroke(strokeState.getStroke());
                //set màu cho stroke
                line.setStrokeColor(colorChooser.getStrokeColor());
                //Thêm điểm đầu vào danh sách điểm di chuột
                line.addDraggedPoint(getPoint(start));
                //Thêm điểm vào đ
                line.setPoint(getPoint(start), getPoint(start));
            case RECT:
                rect = new Rectangle();
                rect.setStroke(strokeState.getStroke());
                rect.setStrokeColor(colorChooser.getStrokeColor());
                rect.setFillColor(colorChooser.getFillColor());
                rect.addDraggedPoint(getPoint(start));
                rect.setPoint(getPoint(start), getPoint(start));
                break;
            
             
            case OVAL:
                oval = new Oval();
                oval.setStroke(strokeState.getStroke());
                oval.setStrokeColor(colorChooser.getStrokeColor());
                oval.setFillColor(colorChooser.getFillColor());
                oval.setPoint(getPoint(start), getPoint(start));
                oval.addDraggedPoint(getPoint(start));
                break;
            case PENCIL:
                pencil = new Pencil();
                pencil.setStroke(strokeState.getStroke());
                pencil.setStrokeColor(colorChooser.getStrokeColor());
                pencil.setPoint(getPoint(start), getPoint(start));
                pencil.addDraggedPoint(getPoint(start));
                pencil.draw(g2d);
                break;
            case BUCKET:
                bucket = new Bucket();
                bucket.setStart(getPoint(start));
                bucket.setArrPoint(getPoint(start));
                bucket.setColor(colorChooser.getFillColor());
                bucket.draw(buff_img);
                paintState.addDrawState(bucket);
                break;
            case ERASER:
                eraser = new Eraser();
                eraser.setStroke(strokeState.getStroke());
                eraser.setStrokeColor(colorChooser.getFillColor());
                eraser.setPoint(getPoint(start), getPoint(start));
                eraser.addDraggedPoint(getPoint(start));
                eraser.draw(g2d);
                locationEraser.move((int) (e.getPoint().x / zoom), (int) (e.getPoint().y / zoom));
                break;

      }

        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (paintTool.getDrawMode()) {
            //<editor-fold defaultstate="collapsed" desc="#">
            case LINE:
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
                eraser.draw(g2d);
                locationEraser.move((int) (e.getPoint().x / zoom), (int) (e.getPoint().y / zoom));
                break;
            case PENCIL:
                pencil.setPoint(pencil.getDraggedPoint().get(0), pencil.getDraggedPoint().get(0));
                paintState.addDrawState(pencil);
                pencil.draw(g2d);
                break;

        }//</editor-fold>
        paintState.addDrawStep(PaintState.PAINTTING);
        start = null;
        end = null;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //chỉ khi dragged chuột thì mới cập nhật hình lên bufer==>Lúc này mới ccaafn phải lưu ảnh
        isSaved = false;
//        lbLocation.setText(getPoint(e.getPoint()).x + ", " + getPoint(e.getPoint()).y + "px");
        end = e.getPoint();
        switch (paintTool.getDrawMode()) {
            //<editor-fold defaultstate="collapsed" desc="#">
            case LINE:
                line.setPoint(getPoint(start), getPoint(end));
                line.addDraggedPoint(getPoint(end));
                break;
            case RECT:
                rect.setPoint(getPoint(start), getPoint(end));
                rect.addDraggedPoint(getPoint(end));
                break;
            
            case OVAL:
                oval.setPoint(getPoint(start), getPoint(end));
                oval.addDraggedPoint(getPoint(end));
                break;
            case ERASER:
                eraser.setPoint(getPoint(start), getPoint(end));
                eraser.addDraggedPoint(getPoint(end));
                start = end;
                eraser.draw(g2d);
                locationEraser.move((int) (e.getPoint().x / zoom), (int) (e.getPoint().y / zoom));
                break;
            case PENCIL:
                pencil.setPoint(getPoint(start), getPoint(end));
                pencil.addDraggedPoint(getPoint(end));
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
