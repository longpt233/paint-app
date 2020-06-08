
package shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Bucket extends Shape implements DrawType {

    private  Point start ;
    private Color color;

    public void setStart(Point start) {
        this.start=start; 
    }

   public  Point getPoin(){
        
            return start;
            
   }
   
   public Color getColor(){
            
            return color;
   }

    public void setColor(Color color) {
        this.color=color;
    }

    

    public void boundaryFill(BufferedImage image) {
        int startColor = image.getRGB(this.start.x, this.start.y);
        int fillColor = this.color.getRGB();
        if (startColor == fillColor) {
            return;
        }
        image.setRGB(this.start.x, this.start.y, fillColor);

//        Graphics g = image.getGraphics();
//        g.setColor(color);
        ArrayList<Point> listPoint = new ArrayList<>();
        listPoint.add(this.start);
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



   
     public void draw(BufferedImage img) {
        boundaryFill(img);
    }

         @Override
         public void draw(Graphics2D g2d) {
                  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         }
}
