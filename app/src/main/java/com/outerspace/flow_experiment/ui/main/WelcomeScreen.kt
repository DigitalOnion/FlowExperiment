package com.outerspace.flow_experiment.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.outerspace.flow_experiment.data.PictureItem
import com.outerspace.flow_experiment.ui.theme.FlowExperimentTheme
import com.outerspace.flow_experiment.ui.theme.Purple40
import java.time.format.TextStyle

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@SuppressLint("ComposableNaming", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun welcome(pictureList: SnapshotStateList<PictureItem>, modifier: Modifier = Modifier) {
    /*This code shows all urls from the list */
    /*  Column(modifier = modifier) {
          pictureList.forEach { pictureItem ->
             /* Text (
                  text = "URL: ${pictureItem.url}",
                  modifier = modifier
              )*/
            GlideImage(
                model = pictureItem.url,
                contentDescription = "load image"
                )
          }
      }*/

    //Adding all images from urls to GlideImage
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pixabay Photos", color = Color.White) },
                colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Purple40)
            )
        }
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(top = 90.dp)
        ) {
            pictureList.forEach { pictureItem ->

                GlideImage(
                    model = pictureItem.url,
                    contentDescription = "load image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                        .padding(top = 4.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .aspectRatio(16f / 9f)
                )
            }
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
