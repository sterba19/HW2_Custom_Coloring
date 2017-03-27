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
public class Drawing extends SurfaceView implements View.OnTouchListener{

    int x = 750;
    int y = 750;
    int r,g,b,selectedObjColor,tempColor;
    int ObjRGB[] = new int[3];
    String ObjectMessage;
    CustomCircle current, leftEar,rightEar,faceShape,leftEye,rightEye,nose;
    public Drawing(Context context, AttributeSet attrs)
    {
        super(context,attrs);

        ObjRGB[0] = 50;
        ObjRGB[1] = 100;
        ObjRGB[2] = 150;
        leftEar = new CustomCircle("Left Ear",0xFF00FF00,x-300,y-400,250);
        rightEar = new CustomCircle("Right Ear",0xFF0000FF,x+300,y-400,250);
        faceShape = new CustomCircle("Bear Face",0xFFFF0000,x,y,500);
        leftEye = new CustomCircle("Left Eye",0xFF000000, x-200,y-150,90);
        rightEye = new CustomCircle("Right Eye",0xFFFFFFFF, x+200,y-150,90);
        nose = new CustomCircle("Nose",0xFFFFFF00, x, y+150,90);

        setWillNotDraw(false);
    }

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

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        int x = (int)event.getX();
        int y = (int)event.getY();
        if(nose.containsPoint(x,y))
        {
            ObjectMessage = "Nose";
            selectedObjColor = nose.getColor();

            nose.setColor(parseColor(selectedObjColor));
            current = nose;
            this.invalidate();
            return true;
        }else if(rightEye.containsPoint(x,y))
        {
            ObjectMessage = "Right Eye";
            selectedObjColor = rightEye.getColor();

            rightEye.setColor(parseColor(selectedObjColor));
            current = rightEye;
            this.invalidate();
            return true;
        }else if(leftEye.containsPoint(x,y))
        {
            ObjectMessage = "Left Eye";
            selectedObjColor = leftEye.getColor();

            leftEye.setColor(parseColor(selectedObjColor));
            current = leftEye;
            this.invalidate();
            return true;
        }else if(faceShape.containsPoint(x,y))
        {
            ObjectMessage = "Bear Face";
            selectedObjColor = faceShape.getColor();

            faceShape.setColor(parseColor(selectedObjColor));
            current = faceShape;
            this.invalidate();
            return true;
        }else if(leftEar.containsPoint(x,y))
        {
            ObjectMessage = "Left Ear";
            selectedObjColor = leftEar.getColor();

            leftEar.setColor(parseColor(selectedObjColor));
            current = leftEar;
            this.invalidate();
            return true;
        }else if(rightEar.containsPoint(x,y))
        {
            ObjectMessage = "Right Ear";
            selectedObjColor = rightEar.getColor();

            rightEar.setColor(parseColor(selectedObjColor));
            current = rightEar;
            this.invalidate();
            return true;
        }
        return false;
    }

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

        current.setColor(ObjRGB[0],ObjRGB[1],ObjRGB[2]);

    }

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

    public String getObjectMessage(){return ObjectMessage;}

    public int getr(){return ObjRGB[0];}
    public int getg(){return ObjRGB[1];}
    public int getb(){return ObjRGB[2];}

    public void setr(int initr){ObjRGB[0] = initr;}
    public void setb(int initb){ObjRGB[0] = initb;}
    public void setg(int initg){ObjRGB[0] = initg;}

}
