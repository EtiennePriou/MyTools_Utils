package com.etienne.mytoolslibrary.extensions
/*

import android.net.Uri
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import ziapps.ilokyou.R
import ziapps.ilokyou.util.picasso.circleTransform

private fun pLoadAvatar(imageView: ImageView, url: String? = null, uri:Uri? = null, isVau:Boolean = false, onError:(() -> Unit)? = null){

    if (url == null && uri == null) return

    val circleTransform = if (isVau) {
        val borderColor = ContextCompat.getColor(imageView.context ,R.color.blue)
        circleTransform(5F, borderColor)
    }else{
        circleTransform()
    }

    Picasso.get().load(uri?.toString() ?: url)
            .apply {
                val drw = ContextCompat.getDrawable(imageView.context, R.drawable.avatar)
                drw?.let {
                    placeholder(drw)
                    error(drw)
                }
            }
            .fit().centerCrop().transform(circleTransform)
            .into(imageView, object :Callback{
                override fun onSuccess() {}
                override fun onError(e: Exception?) { onError?.let { it() } }
            })
}
fun ImageView.loadAvatar(url: String?, isVau:Boolean = false, onError:(() -> Unit)? = null) = pLoadAvatar(this, url, isVau = isVau, onError = onError)
fun ImageView.loadAvatar(uri: Uri, isVau:Boolean = false, onError:(() -> Unit)? = null) = pLoadAvatar(this, uri = uri, isVau = isVau, onError = onError)

fun ImageView.loadUri(uri: Uri?, fitCC:Boolean = false, roundedCornerRadius:Int? = null){
    Picasso.get()
        .load(uri).apply {
            if (fitCC) {
                centerCrop()
                fit()
            }
            roundedCornerRadius?.let {
                transform(RoundedCornersTransformation(it,0))
            }
        }.into(this)
}
fun ImageView.loadUrl(url: String?, fitCC:Boolean = false, roundedCorner:Boolean = false){
    Picasso.get()
            .load(url).apply {
                if (fitCC) {
                    centerCrop()
                    fit()
                }
                if (roundedCorner) transform(RoundedCornersTransformation(32,0))
            }.into(this)
}

fun ImageView.setTint(@ColorRes colorRes:Int){
    drawable.setTint(ContextCompat.getColor(this.context, colorRes))
}*/
