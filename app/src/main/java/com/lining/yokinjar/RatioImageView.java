package com.lining.yokinjar;



import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;


/**
 * ================================================
 * 作    者：Cate Emial:liuh@80pm.com
 * 版    本：1.0
 * 创建日期：2017/4/24.
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class RatioImageView extends AppCompatImageView {

    /**
     * 是否调整宽度
     */
    private boolean adjustWidth = false;

    /**
     * 宽高比
     */
    private float aspectRatio;

    private int roundRadius = 0;
    private int roundRadiuX = 0;
    private int roundRadiuY = 0;

    int cachedWidth = 0;
    int cachedHeight = 0;

    Path clipPath = new Path();


    public RatioImageView(Context context) {
        super(context);
    }

    public RatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.RatioImageView);

        String ratio = a.getString(R.styleable.RatioImageView_ratio);
        adjustWidth = a.getBoolean(R.styleable.RatioImageView_adjustWidth, adjustWidth);
        roundRadius = a.getDimensionPixelSize(R.styleable.RatioImageView_roundRadius, 0);
        roundRadiuX = a.getDimensionPixelSize(R.styleable.RatioImageView_roundRadiuX, 0);
        roundRadiuY = a.getDimensionPixelSize(R.styleable.RatioImageView_roundRadiuY, 0);

        if (TextUtils.isEmpty(ratio)) {
            aspectRatio = 0;
        } else {
            try {
                if (ratio.contains(":")) {
                    String[] wh = ratio.split(":");
                    float width = Float.valueOf(wh[0]);
                    float height = Float.valueOf(wh[1]);
                    aspectRatio = width / height;
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("ratio格式不正确,请参照1：1");
            }
        }
        a.recycle();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        float[] rs = new float[8];
        if (roundRadius != 0) {
            for (int i = 0; i < rs.length; i++) {
                rs[i] = roundRadius;
            }
        } else {
            rs[0] = roundRadiuX;
            rs[1] = roundRadiuX;
            rs[2] = roundRadiuX;
            rs[3] = roundRadiuX;
            rs[4] = roundRadiuY;
            rs[5] = roundRadiuY;
            rs[6] = roundRadiuY;
            rs[7] = roundRadiuY;
        }
        clipPath.addRoundRect(new RectF(0, 0, cachedWidth, cachedHeight), rs, Path.Direction.CW);
        canvas.clipPath(clipPath);

        super.onDraw(canvas);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (cachedWidth == 0 && cachedHeight == 0) {
            if (adjustWidth) {  //调整宽
                cachedHeight = getMeasuredHeight();
                cachedWidth = (int) (cachedHeight / aspectRatio);
            } else {
                cachedWidth = getMeasuredWidth();
                cachedHeight = (int) (cachedWidth / aspectRatio);
            }
            setMeasuredDimension(cachedWidth, cachedHeight);
        } else {
            setMeasuredDimension(cachedWidth, cachedHeight);
        }

    }


    /***
     * Default: false. If set to true, we will adjust the width to maintain aspect ration instead of the height.
     *
     * @param adjustWidth
     */
    public void setAdjustWidth(boolean adjustWidth) {
        this.adjustWidth = adjustWidth;
        cachedWidth = 0;
        cachedHeight = 0;

    }

    /**
     * 设置当前宽高比
     *
     * @param ratio
     */
    public void setAspectRatio(float ratio) {
        this.aspectRatio = ratio;
        postInvalidate();

    }

}