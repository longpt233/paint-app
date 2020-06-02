/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;


public class Bucket extends Shape implements DrawType {

    private Point start;
    private Color color;
    private boolean filled = true;

    private ArrayList<Point> arrPoint = new ArrayList<Point>();

    public void setStart(Point start) {
        this.start = start;
    }

    @Override
    public Point getStart() {
        return this.start;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public void draw(BufferedImage img) {
        boundaryFill(img);
    }

    //kiểm tra xem vùng cần tô có trùng vs màu muốn tô hay ko, nếu đã trùng thì ko cần tô
    public boolean checkFilled() {
        return filled;
    }

    private void boundaryFill(BufferedImage image) {
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
            listPoint.remove(0);
        }

    }

    public void boundaryFill2(int x, int y, BufferedImage image) {
        int startColor = image.getRGB(x, y);
        int fillColor = this.color.getRGB();

        if (startColor == fillColor) {
            return;
        }
//        Graphics g = image.getGraphics();
//        g.setColor(color);

        if (x >= 0 && x <= image.getWidth() - 2 && y >= 0 && y <= image.getHeight() - 2) {
            image.setRGB(x, y, fillColor);
            if (x - 1 >= 0 && y - 1 >= 0 && image.getRGB(x - 1, y - 1) == startColor) {
                boundaryFill2(x - 1, y - 1, image);
            }
            if (x - 1 >= 0 && image.getRGB(x - 1, y) == startColor) {
                boundaryFill2(x - 1, y, image);
            }
            if (x - 1 >= 0 && image.getRGB(x - 1, y + 1) == startColor) {
                boundaryFill2(x - 1, y + 1, image);
            }
            if (y - 1 >= 0 && image.getRGB(x, y - 1) == startColor) {
                boundaryFill2(x, y - 1, image);
            }
            if (image.getRGB(x, y + 1) == startColor) {
                boundaryFill2(x, y + 1, image);
            }
            if (x - 1 >= 0 && image.getRGB(x - 1, y + 1) == startColor) {
                boundaryFill2(x - 1, y + 1, image);
            }
            if (image.getRGB(x, y + 1) == startColor) {
                boundaryFill2(x, y + 1, image);
            }
            if (image.getRGB(x + 1, y + 1) == startColor) {
                boundaryFill2(x + 1, y + 1, image);
            }
        }
    }

    public Point getPoint() {
        return this.start;
    }

    public void setArrPoint(Point point) {
        arrPoint.add(point);
    }

    public ArrayList<Point> getArrPoint() {
        return arrPoint;
    }

    @Override
    public void draw(Graphics2D g2d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
