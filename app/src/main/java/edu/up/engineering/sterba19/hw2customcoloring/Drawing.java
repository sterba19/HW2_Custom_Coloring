package edu.up.engineering.sterba19.hw2customcoloring;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by sterba19 on 3/26/2017.
 */
public class Drawing extends SurfaceView{

    int x = 750;
    int y = 750;
    int leftEarColor = 0xFF00FF00;
    int rightEarColor = 0xFF0000FF;
    int faceColor = 0xFFFF0000;
    int leftEyeColor = 0xFF000000;
    int rightEyeColor = 0xFFFFFFFF;
    int noseColor = 0xFFFFFF00;
    int r,g,b,selectedObjColor;
    int ObjRGB[] = new int[3];
    String ObjectMessage;
    CustomCircle current, leftEar,rightEar,faceShape,leftEye,rightEye,nose;
    //instantiates shape and initializes array
    public Drawing(Context context, AttributeSet attrs)
    {
        super(context,attrs);

        ObjRGB[0] = 50;
        ObjRGB[1] = 100;
        ObjRGB[2] = 150;
        leftEar = new CustomCircle("Left Ear",leftEarColor,x-300,y-400,250);
        rightEar = new CustomCircle("Right Ear",rightEarColor,x+300,y-400,250);
        faceShape = new CustomCircle("Bear Face",faceColor,x,y,500);
        leftEye = new CustomCircle("Left Eye",leftEyeColor, x-200,y-150,90);
        rightEye = new CustomCircle("Right Eye",rightEyeColor, x+200,y-150,90);
        nose = new CustomCircle("Nose",noseColor, x, y+150,90);

        setWillNotDraw(false);
    }

    //Draws shapes
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        leftEar.drawMe(canvas);
        rightEar.drawMe(canvas);
        faceShape.drawMe(canvas);
        leftEye.drawMe(canvas);
        rightEye.drawMe(canvas);
        nose.drawMe(canvas);

    }

    //First part of onTouch(other in main activity) sets current piece and
    //gets that pieces color
    public boolean onTap(View v, MotionEvent event)
    {
        int x = (int)event.getX();
        int y = (int)event.getY();
        if(nose.containsPoint(x,y))
        {
            ObjectMessage = "Nose";
            selectedObjColor = nose.getColor();

            nose.setColor(parseColor(selectedObjColor));
            current = nose;
            return true;
        }else if(rightEye.containsPoint(x,y))
        {
            ObjectMessage = "Right Eye";
            selectedObjColor = rightEye.getColor();

            rightEye.setColor(parseColor(selectedObjColor));
            current = rightEye;
            return true;
        }else if(leftEye.containsPoint(x,y))
        {
            ObjectMessage = "Left Eye";
            selectedObjColor = leftEye.getColor();

            leftEye.setColor(parseColor(selectedObjColor));
            current = leftEye;
            return true;
        }else if(faceShape.containsPoint(x,y))
        {
            ObjectMessage = "Bear Face";
            selectedObjColor = faceShape.getColor();

            faceShape.setColor(parseColor(selectedObjColor));
            current = faceShape;
            return true;
        }else if(leftEar.containsPoint(x,y))
        {
            ObjectMessage = "Left Ear";
            selectedObjColor = leftEar.getColor();

            leftEar.setColor(parseColor(selectedObjColor));
            current = leftEar;
            return true;
        }else if(rightEar.containsPoint(x,y))
        {
            ObjectMessage = "Right Ear";
            selectedObjColor = rightEar.getColor();

            rightEar.setColor(parseColor(selectedObjColor));
            current = rightEar;
            return true;
        }
        this.invalidate();
        return false;
    }

    //Recieve color recieves the information from the seekBar
    //and changes the colors in the array respectively
    public void recieveColor(int color,char c)
    {

        if(c =='r')
        {
            ObjRGB[0] = color;
        }
        else if(c == 'g')
        {
            ObjRGB[1] = color;
        }
        else if(c == 'b')
        {
            ObjRGB[2] = color;
        }

        //Personally written overload of setColor,
        //takes in three ints and sets color of current
        current.setColor(ObjRGB[0],ObjRGB[1],ObjRGB[2]);

    }

    //parseColor takes in the color and breaks it into rgb values
    //stored in an array, then returns the original color
    public int parseColor(int initColor)
    {
        r = (initColor >> 16) & 0xff;
        g = (initColor >>  8) & 0xff;
        b = (initColor      ) & 0xff;
        ObjRGB[0] = r;
        ObjRGB[1] = g;
        ObjRGB[2] = b;
        return initColor;
    }


    //Getter's and Setters
    public String getObjectMessage(){return ObjectMessage;}

    public int getr(){return ObjRGB[0];}
    public int getg(){return ObjRGB[1];}
    public int getb(){return ObjRGB[2];}

    public void setr(int initr){ObjRGB[0] = initr;}
    public void setb(int initb){ObjRGB[0] = initb;}
    public void setg(int initg){ObjRGB[0] = initg;}

}
