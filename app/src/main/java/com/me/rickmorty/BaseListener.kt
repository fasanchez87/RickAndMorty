package com.me.rickmorty
/**
 * Communication fragments and Activitys
 */
interface BaseListener : CoreListener {
    fun showNoFeaturePermissionPopup()
    fun getAnalyticsEvent(): String?
    fun getAnalyticsTitle(): String?
    fun sendAnalytics(event: String, title: String?)
}
