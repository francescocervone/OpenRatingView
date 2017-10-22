package com.francescocervone.openratingview;


import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

class PicassoRequestBuilder extends ImageLoader.RequestBuilder {

    PicassoRequestBuilder(int resource) {
        super(resource);
    }

    @Override
    public void into(@NonNull ImageView imageView) {
        RequestCreator requestCreator = Picasso.with(imageView.getContext())
                .load(mResource)
                .centerInside();

        if (mWidth != 0 && mHeight != 0) {
            requestCreator = requestCreator.resize(mWidth, mHeight);
        }

        requestCreator
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        if (mCallback != null) {
                            mCallback.onSuccess();
                        }
                    }

                    @Override
                    public void onError() {
                        if (mCallback != null) {
                            mCallback.onError();
                        }
                    }
                });
    }
}
