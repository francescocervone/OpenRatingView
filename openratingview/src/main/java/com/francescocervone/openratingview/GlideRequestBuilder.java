package com.francescocervone.openratingview;


import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

class GlideRequestBuilder extends ImageLoader.RequestBuilder {

    GlideRequestBuilder(int resource) {
        super(resource);
    }

    @Override
    public void into(@NonNull ImageView imageView) {
        Glide.with(imageView)
                .load(mResource)
                .apply(getRequestOptions())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        if (mCallback != null) {
                            mCallback.onError();
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (mCallback != null) {
                            mCallback.onSuccess();
                        }
                        return false;
                    }
                })
                .into(imageView);
    }

    @NonNull
    private RequestOptions getRequestOptions() {
        RequestOptions requestOptions = new RequestOptions()
                .dontAnimate()
                .centerInside();
        if (mWidth != 0 && mHeight != 0) {
            requestOptions = requestOptions.override(mWidth, mHeight);
        }
        return requestOptions;
    }
}
