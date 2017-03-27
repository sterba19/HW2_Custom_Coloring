package edu.up.engineering.sterba19.hw2customcoloring;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    Drawing surfaceView;
    TextView ObjectNameView, RedValue, GreenValue, BlueValue;
    SeekBar RedSeek, BlueSeek, GreenSeek;

    //Sets all objects and listeners
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Set Widgets
        surfaceView = (Drawing)findViewById(R.id.SurfaceView);
        surfaceView.setOnTouchListener(new tapper());

        ObjectNameView = (TextView)findViewById(R.id.ObjectNameView);
        RedValue = (TextView)findViewById(R.id.RedValueView);
        RedValue.setText(surfaceView.getr()+"");
        BlueValue = (TextView)findViewById(R.id.BlueValueView);
        BlueValue.setText(surfaceView.getb()+"");
        GreenValue = (TextView)findViewById(R.id.GreenValueView);
        GreenValue.setText(surfaceView.getg()+"");
        RedSeek = (SeekBar)findViewById(R.id.RedSeekBar);
        RedSeek.setProgress(surfaceView.getr());
        RedSeek.setMax(255);
        BlueSeek = (SeekBar)findViewById(R.id.BlueSeekBar);
        BlueSeek.setProgress(surfaceView.getb());
        BlueSeek.setMax(255);
        GreenSeek = (SeekBar)findViewById(R.id.GreenSeekBar);
        GreenSeek.setProgress(surfaceView.getg());
        GreenSeek.setMax(255);
        //Set Listeners
        RedSeek.setOnSeekBarChangeListener(new seekBarSetup());
        BlueSeek.setOnSeekBarChangeListener(new seekBarSetup());
        GreenSeek.setOnSeekBarChangeListener(new seekBarSetup());

        surfaceView.invalidate();
    }

    //Seekbar listener, sends color to drawing via recieveColor
    protected class seekBarSetup implements SeekBar.OnSeekBarChangeListener
    {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            if(seekBar == RedSeek)
            {
                surfaceView.setr(progress);
                RedValue.setText(surfaceView.getr()+"");
                surfaceView.recieveColor(progress,'r');
            }
            else if(seekBar == BlueSeek)
            {
                surfaceView.setb(progress);
                BlueValue.setText(surfaceView.getb()+"");
                surfaceView.recieveColor(progress,'b');
            }
            else if(seekBar == GreenSeek)
            {
                surfaceView.setg(progress);
                GreenValue.setText(surfaceView.getg()+"");
                surfaceView.recieveColor(progress,'g');
            }
            surfaceView.invalidate();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    }

    //Second part of OnTouch(first in Drawing) sets seek bars
    //to color of current object
    public class tapper implements View.OnTouchListener
    {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            surfaceView.onTap(v, event);

            ObjectNameView.setText(surfaceView.getObjectMessage());

            setSeekBar(surfaceView.getr(),surfaceView.getg(),surfaceView.getb());

            return false;
        }
    }

    //Helper method that sets seek bars, and text views
    public void setSeekBar(int initR, int initG, int initB)
    {
        RedSeek.setProgress(initR);
        GreenSeek.setProgress(initG);
        BlueSeek.setProgress(initB);

        RedValue.setText(initR+"");
        GreenValue.setText(initG+"");
        BlueValue.setText(initB+"");
    }
}