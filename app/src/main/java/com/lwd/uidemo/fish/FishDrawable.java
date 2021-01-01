package com.lwd.uidemo.fish;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @AUTHOR lianwd
 * @TIME 1/1/21
 * @DESCRIPTION 鱼的drawable
 */
public class FishDrawable extends Drawable {

    private int alpha = 150;
    private Path mPath;
    private Paint mPaint;

    //鱼头半径
    private int HEAD_RADIUS = 50;
    //鱼身长度
    private float BODY_LENGTH = 3.2f * HEAD_RADIUS;
    //鱼头中心到鱼鳍起始点的长度
    private float FIND_FINS_LENGTH = 0.9f * HEAD_RADIUS;
    //鱼鳍宽度
    private float FINS_LENGTH = 1.3F * HEAD_RADIUS;
    //大圆半径
    private float BIG_RADIUS = 0.7F * HEAD_RADIUS;
    //中圆半径
    private float MID_RADIUS = 0.6F * BIG_RADIUS;
    //小圆半径
    private float SMALL_RADIUS = 0.4F * MID_RADIUS;
    //大圆圆心到中圆圆心的距离
    private float FIND_MID_LENGTH = 1.6F * BIG_RADIUS;
    //尾部三角中心到中圆圆心的距离
    private float FIND_TRIANGLE_LENGTH = 2.7F * MID_RADIUS;
    //中圆圆心到小圆圆心的距离
    private float FIND_SMALL_LENGTH = (0.4F + 2.7f) * MID_RADIUS;

    //鱼的重心
    private PointF middlePoint;
    //鱼的朝向角度
    private float fishMainAngle = 90;
    private PointF mHeadPoint;
    private float currentValue;
    private int frequency = 1;

    public FishDrawable() {
        init();
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setARGB(alpha, 244, 92, 71);

        middlePoint = new PointF(4.19f * HEAD_RADIUS, 4.19f * HEAD_RADIUS);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.f, 3600.f);
        valueAnimator.setDuration(15 * 1000);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentValue = (float) animation.getAnimatedValue() * frequency;
                invalidateSelf();
            }
        });
        valueAnimator.start();
    }

    public PointF calculatePoint(PointF startPoint, float length, float angle) {
        PointF pointF = new PointF();

        pointF.x = (float) (Math.cos(Math.toRadians(angle)) * length + startPoint.x);
        pointF.y = (float) (-Math.sin(Math.toRadians(angle)) * length + startPoint.y);

        return pointF;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        float fishAngle = (float) (this.fishMainAngle + Math.sin(Math.toRadians(currentValue * 1.2)) * 10);

        //鱼头
        mHeadPoint = calculatePoint(middlePoint, BODY_LENGTH / 2, fishAngle);
        canvas.drawCircle(mHeadPoint.x, mHeadPoint.y, HEAD_RADIUS, mPaint);

        //鱼鳍
        float finsAngle = 110;
        PointF rightFinsPoint = calculatePoint(mHeadPoint, FIND_FINS_LENGTH, fishAngle - finsAngle);
        drawFins(canvas, rightFinsPoint, fishAngle, true);
        PointF leftFinsPoint = calculatePoint(mHeadPoint, FIND_FINS_LENGTH, fishAngle + finsAngle);
        drawFins(canvas, leftFinsPoint, fishAngle, false);

        //鱼身
        PointF bodyEndPoint = calculatePoint(mHeadPoint, BODY_LENGTH, fishAngle - 180);
        drawBody(canvas, mHeadPoint, bodyEndPoint, fishAngle);

        //鱼节肢1
        PointF upperCenterPoint = drawSegment(canvas, bodyEndPoint, fishAngle,
                FIND_MID_LENGTH, MID_RADIUS, BIG_RADIUS, true);
        //鱼节肢2
        PointF tailCenterPoint = drawSegment(canvas, upperCenterPoint, fishAngle,
                FIND_SMALL_LENGTH, SMALL_RADIUS, MID_RADIUS, false);
        //尾巴上的圆
        canvas.drawCircle(tailCenterPoint.x, tailCenterPoint.y, SMALL_RADIUS, mPaint);
        //鱼尾
        drawTail(canvas, upperCenterPoint, FIND_TRIANGLE_LENGTH, BIG_RADIUS, fishAngle);
        drawTail(canvas, upperCenterPoint, FIND_TRIANGLE_LENGTH - 20, MID_RADIUS, fishAngle);
    }

    private void drawBody(Canvas canvas, PointF bodyStartCenterPoint, PointF bodyEndCenterPoint, float fishAngle) {
        PointF bodyStartLeftPoint = calculatePoint(bodyStartCenterPoint, HEAD_RADIUS, fishAngle + 90);
        PointF bodyStartRightPoint = calculatePoint(bodyStartCenterPoint, HEAD_RADIUS, fishAngle - 90);
        PointF bodyEndLeftPoint = calculatePoint(bodyEndCenterPoint, BIG_RADIUS, fishAngle + 90);
        PointF bodyEndRightPoint = calculatePoint(bodyEndCenterPoint, BIG_RADIUS, fishAngle - 90);

        PointF leftControlPoint = calculatePoint(bodyStartLeftPoint, BODY_LENGTH * 0.3f,
                fishAngle + 160);
        PointF rightControlPoint = calculatePoint(bodyStartRightPoint, BODY_LENGTH * 0.3f,
                fishAngle - 160);

        mPath.reset();
        mPath.moveTo(bodyStartLeftPoint.x, bodyStartLeftPoint.y);
        mPath.quadTo(leftControlPoint.x, leftControlPoint.y, bodyEndLeftPoint.x, bodyEndLeftPoint.y);
        mPath.lineTo(bodyEndRightPoint.x, bodyEndRightPoint.y);
        mPath.quadTo(rightControlPoint.x, rightControlPoint.y, bodyStartRightPoint.x, bodyStartRightPoint.y);
        canvas.drawPath(mPath, mPaint);
    }

    private void drawTail(Canvas canvas, PointF startPoint, float findCenterPointLength,
                          float findEdgeLength, float fishAngle) {
        //和节肢2的速度一样
        float tailAngle = (float) (fishAngle + Math.sin(Math.toRadians(currentValue * 1.5)) * 30);
        float edgeLength = (float) Math.abs((Math.sin(Math.toRadians(currentValue * 1.5)) * findEdgeLength));

        PointF centerPoint = calculatePoint(startPoint, findCenterPointLength, tailAngle - 180);
        PointF leftPoint = calculatePoint(centerPoint, edgeLength, tailAngle + 90);
        PointF rightPoint = calculatePoint(centerPoint, edgeLength, tailAngle - 90);

        mPath.reset();
        mPath.moveTo(startPoint.x, startPoint.y);
        mPath.lineTo(leftPoint.x, leftPoint.y);
        mPath.lineTo(rightPoint.x, rightPoint.y);
        canvas.drawPath(mPath, mPaint);

    }

    /**
     *
     * @param canvas
     * @param bottomCenterPoint
     * @param fishAngle
     * @param findLength
     * @param upRadius
     * @param bottomRadius
     * @param isFirst 是否节肢1
     * @return
     */
    private PointF drawSegment(Canvas canvas, PointF bottomCenterPoint, float fishAngle,
                             float findLength, float upRadius, float bottomRadius, boolean isFirst) {
        float segmentAngle;
        if (isFirst) {
            segmentAngle = (float) (fishAngle + Math.cos(Math.toRadians(currentValue * 1.5)) * 15);
        } else {
            segmentAngle = (float) (fishAngle + Math.sin(Math.toRadians(currentValue * 1.5)) * 30);
        }

        PointF upCenterPoint = calculatePoint(bottomCenterPoint, findLength, segmentAngle - 180);

        PointF upLeftPoint = calculatePoint(upCenterPoint, upRadius, segmentAngle + 90);
        PointF upRightPoint = calculatePoint(upCenterPoint, upRadius, segmentAngle - 90);
        PointF bottomLeftPoint = calculatePoint(bottomCenterPoint, bottomRadius, segmentAngle + 90);
        PointF bottomRightPoint = calculatePoint(bottomCenterPoint, bottomRadius, segmentAngle - 90);

        mPath.reset();
        mPath.moveTo(upLeftPoint.x, upLeftPoint.y);
        mPath.lineTo(upRightPoint.x, upRightPoint.y);
        mPath.lineTo(bottomRightPoint.x, bottomRightPoint.y);
        mPath.lineTo(bottomLeftPoint.x, bottomLeftPoint.y);
        canvas.drawPath(mPath, mPaint);

        canvas.drawCircle(bottomCenterPoint.x, bottomCenterPoint.y, bottomRadius, mPaint);

        return upCenterPoint;
    }

    /**
     *
     * @param canvas
     * @param startPoint
     * @param fishAngle
     * @param isRight 是否右鱼鳍
     */
    private void drawFins(Canvas canvas, PointF startPoint, float fishAngle, boolean isRight) {
        PointF endPoint = calculatePoint(startPoint, FINS_LENGTH, fishAngle - 180);

        float controlAngle = 115;
        PointF controlPoint = calculatePoint(startPoint, FINS_LENGTH * 1.8f,
                isRight ? fishAngle - controlAngle : fishAngle + controlAngle);

        mPath.reset();
        mPath.moveTo(startPoint.x, startPoint.y);
        mPath.quadTo(controlPoint.x, controlPoint.y, endPoint.x, endPoint.y);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicWidth() {
        return (int) (8.38f * HEAD_RADIUS);
    }

    @Override
    public int getIntrinsicHeight() {
        return (int) (8.38 * HEAD_RADIUS);
    }

    public PointF getMiddlePoint() {
        return middlePoint;
    }

    public void setMiddlePoint(PointF middlePoint) {
        this.middlePoint = middlePoint;
    }

    public PointF getHeadPoint() {
        return mHeadPoint;
    }

    public void setHeadPoint(PointF headPoint) {
        mHeadPoint = headPoint;
    }

    public int getHEAD_RADIUS() {
        return HEAD_RADIUS;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setFishMainAngle(float fishMainAngle) {
        this.fishMainAngle = fishMainAngle;
    }
}
