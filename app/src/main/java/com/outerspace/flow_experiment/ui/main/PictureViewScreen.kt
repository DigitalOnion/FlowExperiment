package com.outerspace.flow_experiment.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.outerspace.flow_experiment.data.PictureEntity
import com.outerspace.flow_experiment.ui.theme.Purple40
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PictureViewScreen(
    picturedataVM: PictureDatabaseViewModel,
    picturedataList: SnapshotStateList<PictureEntity>,
    modifier: Modifier
) {
    val picturescope = rememberCoroutineScope()

    var pictureList: List<PictureEntity> = remember { listOf() }

    picturescope.launch {
        pictureList = picturedataVM.getall()
        picturedataList.addAll(pictureList)
    }
    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Pixabay Photos ", color = Color.White) },
            colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Purple40)
        )
    }) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(top = 90.dp)
        ) {
            picturedataList.forEach() { pictureEntity ->

                GlideImage(
                    model = pictureEntity.url,
                    contentDescription = "load image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                        .padding(top = 4.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }
        }
    }

}

