
package shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Bucket extends Shape implements DrawType {

    private ArrayList<Point> start=new ArrayList<Point>();
    private ArrayList<Color> color=new ArrayList<>();
    private boolean filled = true;

  //  private ArrayList<Point> arrPoint = new ArrayList<Point>();

    public void setStart(Point start) {
        this.start.add( start);
             System.out.println("shape.Bucket.setStart()"+this.start.size());
    }

   public  ArrayList<Point> getPointFirst(){
            ArrayList<Point> tempArrayList=start;
          //   Point remove = start.remove(0);
             System.out.println("shape.Bucket.getPointFirst()"+tempArrayList.size()+"  "+start.size());
            return tempArrayList;
            
   }
   
   public Color getColorWithPointAt(){
            Color tempColor=color.get(0);
            color.remove(0);
            return tempColor;
   }

    public void setColor(Color color) {
        this.color.add( color);
    }

   

   

    //kiểm tra xem vùng cần tô có trùng vs màu muốn tô hay ko, nếu đã trùng thì ko cần tô
    public boolean checkFilled() {
        return filled;
    }

    public void boundaryFill(BufferedImage image) {
        int startColor = image.getRGB(this.start.get(0).x, this.start.get(0).y);
        int fillColor = this.color.get(0).getRGB();
        if (startColor == fillColor) {
            return;
        }
        image.setRGB(this.start.get(0).x, this.start.get(0).y, fillColor);

//        Graphics g = image.getGraphics();
//        g.setColor(color);
        ArrayList<Point> listPoint = new ArrayList<>();
        listPoint.add(this.start.get(0));
        while (!listPoint.isEmpty()) {
            Point temp = listPoint.get(0);
            if ((temp.x >= 0 && temp.x <= image.getWidth() - 2 && temp.y >= 0) && temp.y <= image.getHeight() - 2) {
                if (temp.x - 1 >= 0 && temp.y - 1 >= 0 && (image.getRGB(temp.x - 1, temp.y - 1) == startColor)) {
                    image.setRGB(temp.x - 1, temp.y - 1, fillColor);
                    listPoint.add(new Point(temp.x - 1, temp.y - 1));
                }
                if (temp.x - 1 >= 0 && image.getRGB(temp.x - 1, temp.y) == startColor) {
                     image.setRGB(temp.x - 1, temp.y, fillColor);
                    listPoint.add(new Point(temp.x - 1, temp.y));
                }
                if (temp.x - 1 >= 0 && image.getRGB(temp.x - 1, temp.y + 1) == startColor) {
                   image.setRGB(temp.x - 1, temp.y + 1, fillColor);
                    listPoint.add(new Point(temp.x - 1, temp.y + 1));
                }
                if (temp.y - 1 >= 0 && image.getRGB(temp.x, temp.y - 1) == startColor) {
                     image.setRGB(temp.x, temp.y - 1, fillColor);
                    listPoint.add(new Point(temp.x, temp.y - 1));
                }
                if (image.getRGB(temp.x, temp.y + 1) == startColor) {
                     image.setRGB(temp.x, temp.y + 1, fillColor);
                    listPoint.add(new Point(temp.x, temp.y + 1));
                }
                if (temp.x - 1 >= 0 && image.getRGB(temp.x - 1, temp.y + 1) == startColor) {
                    image.setRGB(temp.x - 1, temp.y + 1, fillColor);
                    listPoint.add(new Point(temp.x - 1, temp.y + 1));
                }
                if (image.getRGB(temp.x, temp.y + 1) == startColor) {
                   image.setRGB(temp.x, temp.y + 1, fillColor);
                    listPoint.add(new Point(temp.x, temp.y + 1));
                }
                if (image.getRGB(temp.x + 1, temp.y + 1) == startColor) {
                    image.setRGB(temp.x + 1, temp.y + 1, fillColor);
                    listPoint.add(new Point(temp.x + 1, temp.y + 1));
                }

            } else {

            }
      //      arrPoint.add(listPoint.get(0));
            listPoint.remove(0);
        }

    }


   

//    public void setArrPoint(Point point) {
//        arrPoint.add(point);
//    }
//
//    public ArrayList<Point> getArrPoint() {
//        return arrPoint;
//    }

   
     public void draw(BufferedImage img) {
        boundaryFill(img);
    }

         @Override
         public void draw(Graphics2D g2d) {
                  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         }
}
