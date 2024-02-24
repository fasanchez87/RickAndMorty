package com.me.rickmorty.util.extensions

import android.graphics.Color
import android.util.Patterns
import android.webkit.URLUtil
import java.io.File
import java.text.DecimalFormat
import java.util.regex.Pattern

fun String?.isValidUrl(): Boolean =
    URLUtil.isValidUrl(this)

fun Int.formatToDecimal(): String {
    val dec = DecimalFormat("#,###")
    return dec.format(this).toString()
}


fun String.isValidEmail() =
    this.trim().isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPhone() = this.trim().isNotEmpty() && Patterns.PHONE.matcher(this).matches()

fun String.isValidDni(): Boolean {
    val validChars = "TRWAGMYFPDXBNJZSQVHLCKE"
    val nifRexp = "^[0-9]{8}[" + validChars + "]\$"
    val nieRexp = "^[XYZ][0-9]{7}[" + validChars + "]\$"
    val str = this.toUpperCase()

    if (!Pattern.compile(nifRexp).matcher(str).matches() && !Pattern.compile(nieRexp).matcher(str)
            .matches()
    ) return false

    val nie =
        str.replace(Regex("^[X]"), "0").replace(Regex("^[Y]"), "1").replace(Regex("^[Z]"), "2")

    val letter = str.substring(str.length - 1)
    val charIndex = nie.substring(0, 8).toInt() % 23

    if (validChars.get(charIndex).toString() == letter) return true

    return false
}

fun String.isFirebasePath(): Boolean {
    return !isUrl() && !isFilePath()
}

fun String.isUrl() = this.trim().isNotEmpty() && Patterns.WEB_URL.matcher(this).matches()
fun String.isFile() =
    this.trim().isNotEmpty() && this.startsWith("file:") || this.startsWith("/storage")

fun String.isFilePath() = File(this).exists()

fun String.fromHexToDecimalColor() = Color.parseColor(this)

fun String.isFloat() = this.toFloatOrNull() != null

fun String.parseToFloat(): Float? {
    return try {
        DecimalFormat.getInstance().parse(this)?.let {
            when (it) {
                is Double -> it.toFloat()
                is Float -> it
                is Long -> it.toFloat()
                else -> null
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun String.isInt() = this.toIntOrNull() != null
