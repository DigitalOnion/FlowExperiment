package com.outerspace.flow_experiment.ui.main

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.lifecycleScope
import com.outerspace.flow_experiment.ui.theme.FlowExperimentTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private val picturesVM: PicturesViewModel by viewModels()

    val dataVM : PictureDatabaseViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        //viewModel = ViewModelProvider(this as ViewModelStoreOwner)[MainViewModel::class.java]

        setContent {
            FlowExperimentTheme {
                val modifier = Modifier
                Surface(
                    modifier = modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   welcome(picturesVM.picturesState, modifier)

                }
            }
        }
    }

    override fun onStart() {
        super.onStart()


        lifecycleScope.launch {
            // Note: both solutions are good, fetchPictures is direct; collectPictures uses flows

            // fetch pictures calls the service directly
//            picturesVM.fetchPictures("Ganesh", 1, 5)

            // collect pictures collects from the flow, which in turn emits the result from the service
            // picturesVM.collectPictures("Ganesh", 1, 5)

           if (isOnline(this@MainActivity)) {
                picturesVM.collectPictures("Ganesh", 1, 5)
               //dataVM.initializepicturetable()
            } else {
                Toast.makeText(this@MainActivity, "No Internet", Toast.LENGTH_LONG).show()

            }


        }
    }
}




fun isOnline(context: Context): Boolean {
    // register activity with the connectivity manager service
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {

            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    } else {
        @Suppress("DEPRECATION") val networkInfo =
            connectivityManager.activeNetworkInfo ?: return false
        @Suppress("DEPRECATION")
        return networkInfo.isConnected
    }
}
