package com.roger.batteryview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Roger on 2015/10/28.
 */
public class BatteryView extends View{
    private static final String TAG = "BatteryView";
    private int color;
    private int orientation;
    private int width;
    private int height;
    private int percentage = 50;

    public BatteryView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
	
	public void setPercentage(int p){
		this.percentage = p;
        invalidate();
	}

    public BatteryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.Battery);
        color = a.getColor(R.styleable.Battery_batteryColor, 0xFFFFFFFF);
        orientation = a.getInt(R.styleable.Battery_batteryOrientation, 0);
        percentage = a.getInt(R.styleable.Battery_percentage,100);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //
        if(orientation==0){//判断电池方向
            drawHorizontalBattery(canvas);
        }else{
            drawVerticalBattery(canvas);
        }
    }

    private void drawVerticalBattery(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        float strokeWidth = height/20.0f;
        float strokeWidth2 = strokeWidth/2;
        paint.setStrokeWidth(strokeWidth);
        //电池头部宽高
        int headHeight = (int) (strokeWidth+0.5f);
        //外框
        RectF rect = new RectF(strokeWidth2,headHeight+strokeWidth2,width-strokeWidth2,height-strokeWidth2);
        canvas.drawRect(rect, paint);
        paint.setStrokeWidth(0);
        float topOffset = (height-headHeight-strokeWidth)*(100- percentage)/100.0f;
        RectF rect2 = new RectF(strokeWidth, headHeight+strokeWidth+topOffset,width-strokeWidth,height-strokeWidth);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(rect2,paint);
        //画电池头
        RectF headRect = new RectF(width/4.0f,0,width*0.75f,headHeight);
        canvas.drawRect(headRect,paint);

    }

    private void drawHorizontalBattery(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        float strokeWidth = width/20.f;
        float strokeWidth_2 = strokeWidth/2;
        paint.setStrokeWidth(strokeWidth);
        RectF r1 = new RectF(strokeWidth_2,strokeWidth_2,width-strokeWidth-strokeWidth_2,height-strokeWidth_2);
        canvas.drawRect(r1, paint);
        paint.setStrokeWidth(0);
        paint.setStyle(Paint.Style.FILL);
        float offset = (width-strokeWidth*2)*percentage/100.f;
        RectF r2 = new RectF(strokeWidth,strokeWidth,offset,height-strokeWidth);
        canvas.drawRect(r2,paint);
        RectF r3 = new RectF(width-strokeWidth,height*0.25f,width,height*0.75f);
        canvas.drawRect(r3,paint);
    }
}
