package com.francescocervone.openratingview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Ciccio on 23/05/15.
 */
public class RatingView extends LinearLayout {
    public static final int COLOR_WHITE = 0, COLOR_BLACK = 1;
    private int mMaxRating;
    private StarView[] mStars;
    private OnStarClickListener onStarClickListener;
    private int mRating;
    private int mColor = COLOR_WHITE;

    public RatingView(Context context) {
        super(context);
        init();
    }

    public RatingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        loadAttributes(context, attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public RatingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadAttributes(context, attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RatingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        loadAttributes(context, attrs);
        init();
    }

    private void loadAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RatingView, 0, 0);
        try {
            mMaxRating = a.getInteger(R.styleable.RatingView_orv_max_rating, 5);
            mColor = a.getInteger(R.styleable.RatingView_orv_star_color, COLOR_WHITE);
        } finally {
            a.recycle();
        }
    }

    private void init() {
        removeAllViews();
        mStars = new StarView[mMaxRating];
        for (int i = 0; i < mMaxRating; i++) {
            StarView starView = new StarView(getContext(), i + 1, mColor, this);
            addView(starView);
            mStars[i] = starView;
        }
        mRating = 0;
    }

    public OnStarClickListener getOnStarClickListener() {
        return onStarClickListener;
    }

    public void setOnStarClickListener(OnStarClickListener onStarClickListener) {
        this.onStarClickListener = onStarClickListener;
    }

    public int getRating() {
        return mRating;
    }

    public void setRating(int rating) {
        this.mRating = rating;
        for (int i = 0; i < mStars.length; i++) {
            if (i < rating)
                mStars[i].setChecked(true);
            else
                mStars[i].setChecked(false);
        }
    }


    public interface OnStarClickListener {
        void onClick(int position);
    }

}
