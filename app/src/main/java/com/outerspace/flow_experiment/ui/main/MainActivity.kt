package com.outerspace.flow_experiment.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.outerspace.flow_experiment.ui.theme.FlowExperimentTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val picturesVM: PicturesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
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
            picturesVM.collectPictures("Ganesh", 1, 5)
        }
    }
}

