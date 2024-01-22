package com.example.images.navigator

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import com.example.images.ImageListContract

class ImageListNavigatorImpl(
    private val context: Context
) : ImageListContract.Navigator {

    override fun openBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(context, browserIntent, null)
    }

}
