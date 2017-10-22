package com.francescocervone.openratingview;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class StarView extends AppCompatImageView implements ImageLoader.Callback {
    private int mPosition;
    private RatingView mRatingView;
    private boolean mChecked;
    private int mWidth;
    private int mColor;
    private int attempts = 0;

    public StarView(Context context, int position, int color, RatingView ratingView) {
        super(context);
        init(position, color, ratingView);
    }

    public StarView(Context context) {
        super(context);
    }

    public StarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(int position, int color, RatingView ratingView) {
        mRatingView = ratingView;
        mColor = color;
        mPosition = position;
        setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_UP:
                return performClick();
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean performClick() {
        mRatingView.setRating(mPosition);
        RatingView.OnStarClickListener listener = mRatingView.getOnStarClickListener();
        if (listener != null) {
            listener.onClick(mPosition);
        }
        return super.performClick();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        refreshDrawable();
    }

    private void refreshDrawable() {
        if (mWidth <= 0)
            return;

        ImageLoader.load(getDrawable(mChecked))
                .resize(mWidth, mWidth)
                .callback(this)
                .into(this);
    }

    public void setChecked(boolean checked) {
        mChecked = checked;
        refreshDrawable();
    }

    public boolean isChecked() {
        return mChecked;
    }

    @DrawableRes
    private int getDrawable(boolean checked) {
        if (checked) {
            if (mColor == RatingView.COLOR_WHITE) {
                return R.drawable.ic_star_white;
            } else {
                return R.drawable.ic_star_black;
            }
        } else {
            if (mColor == RatingView.COLOR_WHITE) {
                return R.drawable.ic_star_outline_white;
            } else {
                return R.drawable.ic_star_outline_black;
            }
        }
    }

    @Override
    public void onSuccess() {
        attempts = 0;
    }

    @Override
    public void onError() {
        postDelayed(() -> {
            Log.d("simpleratingview", "Attempt " + attempts);
            attempts++;
            if (attempts <= 3) {
                refreshDrawable();
            } else {
                attempts = 0;
            }
        }, 50);
    }
}
