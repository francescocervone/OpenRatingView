package com.francescocervone.openratingview;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class StarView extends AppCompatImageView {
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

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mRatingView.setRating(mPosition);
                RatingView.OnStarClickListener listener = mRatingView.getOnStarClickListener();
                if (listener != null)
                    listener.onClick(mPosition);
            }
        });
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
        Picasso.with(getContext())
                .load(getDrawable(mChecked))
                .noFade()
                .resize(mWidth, mWidth)
                .centerInside()
                .into(StarView.this,
                        new Callback() {
                            @Override
                            public void onSuccess() {
                                attempts = 0;
                            }

                            @Override
                            public void onError() {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        attempts++;
                                        Log.d("simpleratingview", "Attempt " + attempts);
                                        if (attempts <= 3)
                                            refreshDrawable();
                                        else
                                            attempts = 0;
                                    }
                                }, 50);
                            }
                        });
    }

    public void setChecked(boolean checked) {
        mChecked = checked;
        refreshDrawable();
    }

    public boolean isChecked() {
        return mChecked;
    }

    private int getDrawable(boolean checked) {
        if (checked) {
            if (mColor == RatingView.COLOR_WHITE)
                return R.drawable.ic_star_white;
            else
                return R.drawable.ic_star_black;
        } else {
            if (mColor == RatingView.COLOR_WHITE)
                return R.drawable.ic_star_outline_white;
            else
                return R.drawable.ic_star_outline_black;
        }
    }
}
