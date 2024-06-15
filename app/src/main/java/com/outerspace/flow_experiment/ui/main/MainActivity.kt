package com.outerspace.flow_experiment.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.outerspace.flow_experiment.R
import com.outerspace.flow_experiment.data.PictureItem
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
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Weight is essential here so the scrollable content is filling all the available space
                        scrollableContent(pictureList = picturesVM.picturesState, modifier = Modifier.weight(1f))
                        buttonsContainer(onClick = onClickRefresh)
                    }

//                    Column(modifier = modifier.fillMaxSize()) {
//                        welcome(picturesVM.picturesState, modifier = modifier.weight(0.8f))
//                    }
//                    Button(onClick = onClickRefresh, modifier = Modifier.fillMaxWidth()) {
//                        Text(stringResource(R.string.refresh_button_face))
//                    }
                }
            }
        }
    }

    @Composable
    private fun scrollableContent(pictureList: SnapshotStateList<PictureItem>, modifier: Modifier) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
        ) {
            welcome(modifier, pictureList)
        }
    }

    @Composable
    private fun buttonsContainer(onClick: () -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = onClickRefresh, modifier = Modifier.fillMaxWidth()) {
                Text(stringResource(R.string.refresh_button_face))
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

    private val onClickRefresh: () -> Unit = {
        lifecycleScope.launch {
            picturesVM.collectPictures("Ganesh", 2, 5)
        }
    }
}

