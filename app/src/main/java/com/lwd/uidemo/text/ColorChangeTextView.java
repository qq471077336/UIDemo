package com.lwd.uidemo.text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * @AUTHOR lianwd
 * @TIME 1/17/21
 * @DESCRIPTION TODO
 */
public class ColorChangeTextView extends AppCompatTextView {

    private final String TEXT = "笑傲江湖";
    private float percent;

    public ColorChangeTextView(Context context) {
        super(context);
    }

    public ColorChangeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorChangeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCenterLineX(canvas);
        drawCenterLineY(canvas);

        drawCenterText(canvas);
        drawCenterTextChange(canvas);
    }

    private void drawCenterText(Canvas canvas) {
        canvas.save();
        Paint paint = new Paint();
        paint.setTextSize(80);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);

        float width = paint.measureText(TEXT);
        float left = getWidth() / 2 - width / 2;
        float clipLeft = left+ width * (percent);
        float right = left + width;

        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        int baseLine = (int) (getHeight() / 2 - (fontMetrics.descent + fontMetrics.ascent) / 2);

        Rect rect = new Rect((int) clipLeft, 0, (int) right, getHeight());
        canvas.clipRect(rect);

        canvas.drawText(TEXT, left, baseLine, paint);
        canvas.restore();
    }

    private void drawCenterTextChange(Canvas canvas) {
        canvas.save();
        Paint paint = new Paint();
        paint.setTextSize(80);
        paint.setColor(Color.GREEN);
        paint.setAntiAlias(true);

        float width = paint.measureText(TEXT);
        float left = getWidth() / 2 - width / 2;
        float right = left + width * percent;

        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        int baseLine = (int) (getHeight() / 2 - (fontMetrics.descent + fontMetrics.ascent) / 2);

        Rect rect = new Rect((int) left, 0, (int) right, getHeight());
        canvas.clipRect(rect);

        canvas.drawText(TEXT, left, baseLine, paint);
        canvas.restore();
    }

    private void drawCenterLineX(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), paint);
    }

    private void drawCenterLineY(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, paint);
    }
}
