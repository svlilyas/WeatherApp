package com.mobilion.weatherapp.core.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Base64
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.mobilion.weatherapp.R
import timber.log.Timber

@BindingAdapter(
    value = ["app:imageUrl", "app:placeHolder", "app:errorDrawable", "app:isCenterInside"],
    requireAll = false
)
fun ImageView.loadImage(
    url: String?,
    placeHolder: Any?,
    errorDrawable: Any?,
    isCenterInside: Boolean?
) {
    try {

        if (isCenterInside == true) {
            this.scaleType = ImageView.ScaleType.CENTER_INSIDE
        }

        val placeHolderOption = when (placeHolder) {
            is Drawable -> {
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
                    .placeholder(placeHolder)
            }
            is Int -> {
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
                    .placeholder(placeHolder)
            }
            else -> {
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
                    .placeholder(R.drawable.ic_img_placeholder)
            }
        }

        Glide.with(this).load(url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    when (errorDrawable) {
                        is Drawable -> {
                            this@loadImage.setImageDrawable(errorDrawable)
                        }
                        is Int -> {
                            this@loadImage.setImageResource(errorDrawable)
                        }
                        else -> {
                            this@loadImage.setImageResource(R.drawable.ic_img_placeholder)
                        }
                    }
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    this@loadImage.scaleType = ImageView.ScaleType.FIT_CENTER
                    this@loadImage.setImageDrawable(resource)
                    return true
                }
            })
            .apply(placeHolderOption)
            .into(this)
    } catch (e: Exception) {
        Timber.e(e)
    }
}

@BindingAdapter(
    value = ["app:imageUrl", "app:placeHolder", "app:errorDrawable", "app:isCenterInside"],
    requireAll = false
)
fun ImageView.imageUrl(
    url: String?,
    placeHolder: Any? = null,
    errorDrawable: Any? = null,
    isCenterInside: Boolean? = null
) {
    try {
        if (isCenterInside == true) {
            this.scaleType = ImageView.ScaleType.CENTER_INSIDE
        }

        val placeHolderOption = when (placeHolder) {
            is Drawable -> {
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
                    .placeholder(placeHolder)
            }
            is Int -> {
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
                    .placeholder(placeHolder)
            }
            else -> {
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
                    .placeholder(R.drawable.ic_img_placeholder)
            }
        }

        Glide.with(this).load(url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    when (errorDrawable) {
                        is Drawable -> {
                            this@imageUrl.setImageDrawable(errorDrawable)
                        }
                        is Int -> {
                            this@imageUrl.setImageResource(errorDrawable)
                        }
                        else -> {
                            this@imageUrl.setImageResource(R.drawable.ic_img_placeholder)
                        }
                    }
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    this@imageUrl.scaleType = ImageView.ScaleType.FIT_CENTER
                    this@imageUrl.setImageDrawable(resource)
                    return true
                }
            })
            .apply(placeHolderOption)
            .into(this)
    } catch (e: Exception) {
        Timber.e(e)
    }
}

@BindingAdapter("app:imageUrl64")
fun AppCompatImageView.base64url(base64: String) {
    val decodedString: ByteArray = Base64.decode(base64, Base64.DEFAULT)
    val bitMap: Bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    Glide.with(this)
        .asBitmap()
        .load(bitMap)
        .placeholder(R.drawable.ic_img_placeholder)
        .into(this)
}

@BindingAdapter("app:imageUrl64")
fun ImageView.base64url(base64: String) {
    try {
        val decodedString: ByteArray = Base64.decode(base64, Base64.DEFAULT)
        val bitMap: Bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        Glide.with(this)
            .asBitmap()
            .load(bitMap)
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    this@base64url.setImageResource(R.drawable.ic_img_placeholder)
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    this@base64url.setImageBitmap(resource)
                    return true
                }
            })
            .placeholder(R.drawable.ic_img_placeholder)
            .into(this)
    } catch (e: Exception) {
        Timber.e(e)
    }
}

@BindingAdapter("app:imageUrl")
fun AppCompatImageView.loadImage(url: Int?) {
    Glide.with(this).load(url).into(this)
}

@BindingAdapter("app:imageUrl")
fun AppCompatImageView.loadImage(bitmap: Bitmap?) {
    Glide.with(this).load(bitmap).into(this)
}

@BindingAdapter("app:imageUrl")
fun AppCompatImageView.loadImage(url: Drawable?) {
    Glide.with(this).load(url).into(this)
}

@BindingAdapter("app:imageUrl")
fun AppCompatImageView.loadImage2(url: String) {
    Glide.with(this).load(url).into(this)
}
