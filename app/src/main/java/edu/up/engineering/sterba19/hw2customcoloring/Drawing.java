package edu.up.engineering.sterba19.hw2customcoloring;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceView;

/**
 * Created by sterba19 on 3/26/2017.
 */
public class Drawing extends SurfaceView{

    public Drawing(Context context, AttributeSet attrs)
    {
        super(context,attrs);
        setWillNotDraw(false);
    }

    public void onDraw(Canvas canvas)
    {
        
    }
}
