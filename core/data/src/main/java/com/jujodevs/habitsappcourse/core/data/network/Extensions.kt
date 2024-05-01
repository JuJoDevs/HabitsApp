package com.jujodevs.habitsappcourse.core.data.network

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun ConnectivityManager.isOnline(): Boolean {
    val network = this.activeNetwork ?: return false
    val actNw = this.getNetworkCapabilities(network) ?: return false
    return when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
}