package com.me.rickmorty

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//import com.google.firebase.analytics.FirebaseAnalytics

@AndroidEntryPoint
abstract class BaseActivity : ParentActivity(), BaseListener {

    //override val navigator: Navigator by inject()
    @Inject
    override lateinit var navigator: Navigator

    override fun onError(throwable: Throwable) {
        if (throwable is UnsupportedVersionException) {
            showPopup(
                title = getString(R.string.error),
                description = getString(R.string.must_update_app),
                positiveButton = getString(R.string.update_available),
                positiveListener = { _, _ ->
                    throwable.remoteConfigVersionModel?.let {
                        //if (it.urlStoreAndroid.isValidUrl())
                            //startActivity(navigator.navigateToWeb(it.urlStoreAndroid))
                    }
                },
                cancelable = false
            )
        } else {
            //CrashReports.logException(throwable)
            super.onError(throwable)
        }
    }


    override fun showNoFeaturePermissionPopup() {
//        showPopup(
//            title = getString(R.string.warning),
//            description = getString(R.string.no_feature_permissions),
//            positiveButton = getString(R.string.accept),
//            positiveListener = { _, _ -> }
//        )
    }

    override fun sendAnalytics(event: String, title: String?) {
        var screenName = event

        title?.let {
            screenName += " - $it"
        }

        val bundle = Bundle()
       // bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        //firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }

    override fun getAnalyticsEvent(): String? = null

    override fun getAnalyticsTitle(): String? = null

    override fun onStart() {
        super.onStart()
        getAnalyticsEvent()?.let {
            sendAnalytics(it, getAnalyticsTitle())
        }
    }
}
