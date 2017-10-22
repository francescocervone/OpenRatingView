package com.francescocervone.openratingview;


import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

class ImageLoader {
    @Nullable
    private static Boolean sIsGlideAvailable;
    @Nullable
    private static Boolean sIsPicassoAvailable;

    private ImageLoader() {
    }

    @NonNull
    static RequestBuilder load(@DrawableRes int resource) {
        if (isGlideAvailable()) {
            return new GlideRequestBuilder(resource);
        } else if (isPicassoAvailable()) {
            return new PicassoRequestBuilder(resource);
        }
        throw new IllegalStateException("You must have Picasso or Glide as dependency");
    }

    private static boolean isGlideAvailable() {
        if (sIsGlideAvailable == null) {
            try {
                Class.forName("com.bumptech.glide.Glide");
                sIsGlideAvailable = true;
            } catch (ClassNotFoundException e) {
                sIsGlideAvailable = false;
            }
        }
        return sIsGlideAvailable;
    }

    private static boolean isPicassoAvailable() {
        if (sIsPicassoAvailable == null) {
            try {
                Class.forName("com.squareup.picasso.Picasso");
                sIsPicassoAvailable = true;
            } catch (ClassNotFoundException e) {
                sIsPicassoAvailable = false;
            }
        }
        return sIsPicassoAvailable;
    }

    abstract static class RequestBuilder {
        int mResource;
        int mWidth;
        int mHeight;
        @Nullable
        Callback mCallback;

        RequestBuilder(@DrawableRes int resource) {
            mResource = resource;
        }

        @NonNull
        RequestBuilder resize(@IntRange(from = 1) int width, @IntRange(from = 1) int height) {
            mWidth = width;
            mHeight = height;
            return this;
        }

        @NonNull
        RequestBuilder callback(@Nullable Callback callback) {
            mCallback = callback;
            return this;
        }

        public abstract void into(@NonNull ImageView imageView);
    }

    interface Callback {
        void onSuccess();

        void onError();
    }
}
