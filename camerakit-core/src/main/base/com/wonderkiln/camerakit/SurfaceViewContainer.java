package com.wonderkiln.camerakit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class SurfaceViewContainer extends FrameLayout {

    private Size mPreviewSize;
    private int mDisplayOrientation;

    public SurfaceViewContainer(@NonNull Context context) {
        super(context);
    }

    public SurfaceViewContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SurfaceViewContainer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // Measure the Child View
        if (mPreviewSize != null && getChildCount() > 0) {
            int previewWidth;
            int previewHeight;
            if (mDisplayOrientation % 180 == 0) {
                //portrait
                previewWidth = mPreviewSize.getWidth();
                previewHeight = mPreviewSize.getHeight();
            } else {
                //landscape
                previewWidth = mPreviewSize.getHeight();
                previewHeight = mPreviewSize.getWidth();
            }

            final int width = getMeasuredWidth();
            final int height = getMeasuredHeight();

            final View child = getChildAt(0);
            if (width * previewHeight > height * previewWidth) {
                child.measure(
                        MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec(width * previewHeight / previewWidth,
                                MeasureSpec.EXACTLY));
            } else {
                child.measure(
                        MeasureSpec.makeMeasureSpec(height * previewWidth / previewHeight,
                                MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
            }
        }
    }

    public void setPreviewSize(Size previewSize) {
        this.mPreviewSize = previewSize;
    }

    public void setDisplayOrientation(int displayOrientation) {
        mDisplayOrientation = displayOrientation;
    }

}
