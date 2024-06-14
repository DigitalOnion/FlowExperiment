package com.outerspace.flow_experiment.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.outerspace.flow_experiment.data.PictureItem
import com.outerspace.flow_experiment.ui.theme.FlowExperimentTheme

@SuppressLint("ComposableNaming")
@Composable
fun welcome(pictureList: SnapshotStateList<PictureItem>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        pictureList.forEach { pictureItem ->
            Text (
                text = "URL: ${pictureItem.url}",
                modifier = modifier
            )
        }
    }
}

@SuppressLint("ComposableNaming")
@Preview(showBackground = true)
@Composable
fun welcomePreview() {
    val pictureList: SnapshotStateList<PictureItem> = remember {
        mutableStateListOf(
            PictureItem("photo", "tag", "https://the-picture.com")
        )
    }
    FlowExperimentTheme {
        welcome(pictureList, Modifier)
    }
}
