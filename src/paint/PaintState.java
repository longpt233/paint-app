 
package paint;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.Serializable;
import java.util.ArrayList;
import shape.DrawType;
 
public class PaintState implements Serializable{
   
    private int StepCount=0;
    // do thi mang nay dc add them motj trong thai painting
    private ArrayList<DrawType> listState;
   // private DrawType drawType;
    private int[] data = null;  
    private int w,h;
    public PaintState(){
        listState = new ArrayList<>();
        StepCount=0;
       // listState.add(drawType);
    }
    public void addDrawStep(){
        StepCount++;
    }
    public int getStepCount(){
             return  StepCount;
    }
    public void addDrawState(DrawType drawType){
        listState.add(drawType);
    }
    public ArrayList<DrawType> getListState(){
        return listState;
    }
 
    public DrawType removeEndState(){
        if(listState.isEmpty())
            return null;
        return listState.remove(listState.size()-1);
    }
    public int removeEndStep(){
         if(StepCount==0) return -1;
         StepCount--;
         return 0;
    }
    public boolean isEmpty(){
         return StepCount==0;
    }
    public void removeAll(){
        listState = new ArrayList<>();
        StepCount=0;
        System.gc();
    }
    
   // set va get data cho luong replay 
    public void setData(BufferedImage buff_img){   // goi o padpaint
        w = buff_img.getWidth();
        h = buff_img.getHeight();
        data = new int[w*h*3];
        final WritableRaster wr = buff_img.getRaster();
        data = wr.getPixels(0, 0, w, h,data);
    }
    public int[] getData(){    // lay lai o trong replay 
        return data;
    }
    public int getWidth(){
        return w;
    }
    public int getHeight(){
        return h;
    }
}