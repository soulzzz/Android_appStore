package com.example.appstore.activity;

import androidx.appcompat.widget.AppCompatImageView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

public class AppCardShow extends AppCompatImageView {
    float width, height;

    public AppCardShow(Context context) {
        this(context, null);
        init(context, null);
    }

    public AppCardShow(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(context, attrs);
    }

    public AppCardShow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //width是图片的宽，height是图片的高
        if (width >= 50 && height > 50) {
            Path path = new Path();

            path.moveTo(20, 0);//起始位置50是左角的圆
            path.lineTo(width - 20, 0);//画直线，这2句代码是在图片的最上方画一条留了左右圆角半径的直线
            path.quadTo(width, 0, width, 20);//画右上圆角
            path.lineTo(width, height);//画 ‘画右上圆角’后的直线一直到右下角的边界
            path.lineTo(0, height);//从右下角的边界一直画到左下角的直线
            path.lineTo(0, 20);//从左下角一直画到左上角的画圆的开始位置
            path.quadTo(0, 0, 20, 0);//画左上角的圆

            canvas.clipPath(path);
        }
        super.onDraw(canvas);
    }
}
