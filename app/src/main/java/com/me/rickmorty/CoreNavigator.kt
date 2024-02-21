package com.me.rickmorty

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.location.Location
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

abstract class CoreNavigator(protected val context: Context) {

    open fun navigateToWeb(url: String): Intent {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        return i
    }

    open fun navigateToSendMail(address: String, subject: String = ""): Intent {
        return Intent(Intent.ACTION_SENDTO).apply {
            type = "message/rfc822"
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
    }

    open fun navigateToMaps(location: Location): Intent {
        return Intent(
            Intent.ACTION_VIEW,
            Uri.parse("geo:0,0?q=${location.latitude},${location.longitude}")
        ).setPackage("com.google.android.apps.maps")
    }

    open fun navigateToCall(number: String): Intent {
        return Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:$number"))
    }

    open fun getShareIntent(text: String): Intent {
        return Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
    }

    fun navigateToOpenExternalFile(uri: Uri, mimetype: String): Intent {
        require(uri.path != null) { "uri.path is null" }
        return Intent(Intent.ACTION_VIEW)
            .apply {
                setDataAndType(FileProvider.getUriForFile(context, "${context.packageName}.me.picker", File(uri.path!!)), mimetype)
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            }
    }

    fun navigateToDownloadApp(): Intent =
        try {
            Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=${context.packageName}"))
        } catch (e: ActivityNotFoundException) {
            Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=${context.packageName}"))
        }

    open fun navigateToVideoPlayer(uri: Uri): Intent {
        return Intent(Intent.ACTION_VIEW)
            .setDataAndType(uri, "video/*")
            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    //abstract fun navigateToLogin(): Intent?
}
