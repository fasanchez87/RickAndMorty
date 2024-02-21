package com.me.rickmorty

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat
import java.util.*

/**
 * Check if the device is connected to the internet
 * <b>Sample usage</b>
 *
 * ```
 * if(context.hasNetworkConnection()) {
 *  // do something
 * }
 * ```
 *
 * @return true if connected, false otherwise
 */
@SuppressLint("MissingPermission")
fun Context.hasNetworkConnection(): Boolean {
    var result = false
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        connectivityManager.run {
            connectivityManager.activeNetworkInfo?.run {
                result = when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
    }

    return result
}

/**
 * Function de extension de [Context]
 *
 * Retorna un string por el nombre de la clave
 * <b>Sample usage</b>
 *
 * ```
 * context.getStringByName("unexpected_error")
 * ```
 *
 * @param resName string nombre del recurso, la key del string.
 * @return string valor del recurso, null si este no se encuentra
 */
fun Context.getStringByName(resName: String) =
    try {
        this.resources.getString(getResourceId(resName, "string"))
    } catch (exception: Resources.NotFoundException) {
        null
    }

/**
 * Function de extension de [Context]
 *
 * Retorna un recurso de drawable a partir del nombre.
 * <b>Sample usage</b>
 *
 * ```
 * context.getDrawableByName("ic_back")
 * ```
 *
 * @param resName Drawable nombre del recurso, el nombre del drawable que se quiere rescatar
 * @return Drawable drawable resuelto, null si este no se encuentra
 */
fun Context.getDrawableByName(resName: String) =
    try {
        ContextCompat.getDrawable(this, getResourceId(resName, "drawable"))
    } catch (exception: Resources.NotFoundException) {
        null
    }

/**
 * Funcion de extension de [Context]
 *
 * Retorna el id de un recurso por su nombre y su tipo de recurso
 * <b>Sample usage</b>
 *
 * ```
 * getResourceId(resName, "string")
 * getResourceId(resName, "drawable")
 * ```
 *
 * @param resName key of resource
 * @param resType type of resource
 * @return Drawable value of drawable resource
 * @see Resources.getIdentifier
 */
fun Context.getResourceId(resName: String, resType: String) = resources.getIdentifier(resName, resType, this.packageName)

/**
 * Function de extension de [Context]
 *
 * Retorna el current locale de la aplicacion
 * <b>Sample usage</b>
 *
 * ```
 * context.getCurrentLocale()
 * ```
 *
 * @return Locale current locale object
 * @see Resources.configuration.locales
 */
fun Context.getCurrentLocale(): Locale {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        resources.configuration.locales[0]
    } else {
        resources.configuration.locale
    }
}

/**
 * Metodo overload de getCurrentLocale
 *
 * @return tag del lenguaje de la aplicacion
 * @see ContextExt.getCurrentLocale
 */
fun Context.getCurrentTagLanguage(): String = getCurrentLocale().language

/**
 * Funcion de extension de [Context]
 *
 * Ejecuta la funcion dada en el tiempo que se le indica, en segundo plano.
 * <b>Sample usage</b>
 *
 * ```
 *  context.after(10){
 *   //Code to execute
 *  }
 * ```
 * @param milis Tiempo en milisegundos para ejecutar la funcion de la
 * @param closure Funcion de callback que se ejecutara cuando termine la funcion.
 * @see Handler.postDelayed
 */
fun Context.after(milis: Long, closure: () -> Unit) {
    Handler(Looper.myLooper()!!).postDelayed(
        Runnable {
            closure()
        },
        milis
    )
}

/**
 * Funcion de extension de [Context]
 *
 * Crea un intent de la activity indicada y le pasa ese mismo intent a la funcion
 * que se recibe para meterle parametros, la funcion de construccion es opcional
 * <b>Sample usage</b>
 *
 * ```
 * // Without params
 * getCallingIntent<MainActivity>()
 *
 * // With params
 * getCallingIntent<MainActivity>{
 *     putExtra(EXTRA_USER, user)
 *  }
 * ```
 * @param block Funcion que recibira el intent creado para construirle sus parametros
 * @param T Tipo de la actividad que se quiere llamar.
 * @see Intent
 */
inline fun <reified T : Activity> Context.getCallingIntent(block: Intent.() -> Unit = {}) = Intent(this, T::class.java).apply(block)
