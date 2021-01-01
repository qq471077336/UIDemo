package com.lwd.uidemo.fish;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * @AUTHOR lianwd
 * @TIME 1/1/21
 * @DESCRIPTION 鱼塘
 */
public class FishPool extends RelativeLayout {

    private Paint mPaint;
    private float touchX;
    private float touchY;
    private float ripple;
    private int alpha;
    private FishDrawable fishDrawable;
    private ImageView ivFish;

    public FishPool(Context context) {
        this(context, null);
    }

    public FishPool(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FishPool(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setWillNotDraw(false);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);

        ivFish = new ImageView(context);
        fishDrawable = new FishDrawable();
        ivFish.setImageDrawable(fishDrawable);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        ivFish.setLayoutParams(layoutParams);
        addView(ivFish);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setAlpha(alpha);
        canvas.drawCircle(touchX, touchY, ripple * 150, mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        touchX = event.getX();
        touchY = event.getY();

        mPaint.setAlpha(100);
        ObjectAnimator rippleAnimator = ObjectAnimator.ofFloat(this, "ripple", 0, 1f).setDuration(1000);
        rippleAnimator.start();

        fishSwim();

        return super.onTouchEvent(event);
    }

    private void fishSwim() {
        //鱼重心相对坐标
        PointF middlePoint = fishDrawable.getMiddlePoint();
        //起始点，鱼重心的绝对坐标
        PointF startPoint = new PointF(middlePoint.x + ivFish.getX(), middlePoint.y + ivFish.getY());

        //鱼的头中心相对坐标
        PointF headPoint = fishDrawable.getHeadPoint();
        //控制点1，鱼头中心绝对坐标
        PointF controlPoint1 = new PointF(headPoint.x + ivFish.getX(), headPoint.y + ivFish.getY());

        //结束点
        PointF endPoint = new PointF(touchX, touchY);
        float angle = includeAngle(startPoint, controlPoint1, endPoint) / 2;
        float delta = includeAngle(startPoint, new PointF(startPoint.x + 1, startPoint.y), endPoint);
        PointF controlPoint2 = fishDrawable.calculatePoint(startPoint,
                fishDrawable.getHEAD_RADIUS() * 1.6f, angle + delta);

        Path path = new Path();
        path.moveTo(startPoint.x - middlePoint.x, startPoint.y - middlePoint.y);
        path.cubicTo(controlPoint1.x - middlePoint.x, controlPoint1.y - middlePoint.y,
                controlPoint2.x - middlePoint.x, controlPoint2.y - middlePoint.y,
                endPoint.x - middlePoint.x, endPoint.y - middlePoint.y);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ivFish, "x", "y", path);
        objectAnimator.setDuration(2000);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.addListener(new AnimatorListenerAdapter(){
            @Override
            public void onAnimationStart(Animator animation) {
                fishDrawable.setFrequency(2);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                fishDrawable.setFrequency(1);
            }
        });

        PathMeasure pathMeasure = new PathMeasure(path, false);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //百分比
                float fraction = animation.getAnimatedFraction();

                float[] tan = new float[2];
                pathMeasure.getPosTan(pathMeasure.getLength() * fraction, null, tan);
                float angle = (float) Math.toDegrees(Math.atan2(-tan[1], tan[0]));

                fishDrawable.setFishMainAngle(angle);
            }
        });
        objectAnimator.start();
    }

    private float includeAngle(PointF O, PointF A, PointF B) {
//        cosAOB = (OA*OB)/(|OA|*|OB|)
//        OA*OB=(Ax-Ox)(Bx-Ox)+(Ay-Oy)*(By-Oy)
        float AOB = (A.x - O.x) * (B.x - O.x) + (A.y - O.y) * (B.y - O.y);
        float OA = (float) Math.sqrt((A.x - O.x) * (A.x - O.x) + (A.y - O.y) * (A.y - O.y));
        float OB = (float) Math.sqrt((B.x - O.x) * (B.x - O.x) + (B.y - O.y) * (B.y - O.y));
        float cosAOB = AOB / (OA * OB);

        float angleAOB = (float) Math.toDegrees(Math.acos(cosAOB));

        float direction = (B.x - O.x) / (B.y - O.y) - (A.x - O.x) / (A.y - O.y);
        if (direction == 0) {
            if (AOB >= 0) {
                return 0;
            } else {
                return 180;
            }
        } else {
            if (direction > 0) {
                return -angleAOB;
            } else {
                return angleAOB;
            }
        }
    }

    public float getRipple() {
        return ripple;
    }

    public void setRipple(float ripple) {
        alpha = (int) (100 * (1 - ripple));
        this.ripple = ripple;

        invalidate();

    }
}
