package ua.example.weatherapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

// TODO (STEP 2: Create the activities package and utils package and add the MainActivity class to it and create the constant object in utils.)\
// START
object Constants {

    const val APP_ID = "1ac534d367450caa939e0de4509f2f70"
    const val BASE_URL: String = "http://api.openweathermap.org/data/"
    const val METRIC_UNIT = "metric"

    const val PREFERENCE_NAME = "WeatherAppPreference"
    const val WEATHER_RESPONSE_DATA = "weather_response_data"

    // TODO (STEP 3: Add a function to check the network connection is available or not.)
    /**
     * This function is used check the weather the device is connected to the Internet or not.
     */
    fun isNetworkAvailable(context: Context): Boolean {
        // It answers the queries about the state of network connectivity.
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network      = connectivityManager.activeNetwork ?: return false
            val activeNetWork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetWork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetWork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //for other device how are able to connect with Ethernet
                activeNetWork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            // Returns details about the currently active default data network.
                @Suppress("DEPRECATION")
                val networkInfo = connectivityManager.activeNetworkInfo
                @Suppress("DEPRECATION")
                return networkInfo != null && networkInfo.isConnectedOrConnecting
        }
    }
}