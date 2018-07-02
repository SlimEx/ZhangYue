package com.slim.imageloader;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * 项目：Xndroid
 * 作者：Yuki - 2018/1/7
 **/
public class ImageLoader {
    public static void loadImage(@NonNull ImageView imageView, @Nullable String url) {
        loadImage(imageView, url, 0, 0);
    }

    public static void loadImage(@NonNull ImageView imageView, @Nullable String url, int placeholderRes) {
        loadImage(imageView, url, placeholderRes, 0);
    }

    public static void loadImage(@NonNull ImageView imageView, @Nullable String url, int placeholderRes, int errorRes) {
        loadImage(imageView, url, placeholderRes, errorRes, null);
    }

    public static void loadImage(final ImageView imageView, final String url, int placeholderRes, int errorRes, final XCallback callback) {
        if(TextUtils.isEmpty(url))
            return;
        GlideApp.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeholderRes)
                .error(errorRes)
                .centerCrop()
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        if(callback != null)
                            callback.onLoadFailed();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        if(callback != null)
                            callback.onResourceReady();
                        return false;
                    }
                })
                .into(imageView);

    }

    public static void loadRoundImage(@NonNull ImageView imageView, @Nullable String url, int placeholderRes) {
        loadRoundImage(imageView, url, placeholderRes, 0);
    }

    public static void loadRoundImage(@NonNull ImageView imageView, @Nullable String url, int placeholderRes, int errorRes) {
        GlideApp.with(imageView)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeholderRes)
                .error(errorRes)
                .circleCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    public interface XCallback {
        void onLoadFailed();

        void onResourceReady();
    }

}
