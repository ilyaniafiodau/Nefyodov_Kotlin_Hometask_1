package com.example.myapplication.ui.theme

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.res.colorResource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.myapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val viewModel: MyViewModel = viewModel()
    val images = viewModel.images.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val hasError = viewModel.hasError.collectAsState()
    val backgroundColor = colorResource(id = R.color.Yellow)
    val TopColor = topColor

    Scaffold(
        modifier = Modifier.background(TopColor),
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Best Footage",
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 30.sp,
                            color = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = topColor
                )
            )
        }
    ) { paddingValues ->
        when {
            isLoading.value -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.Gray.copy(alpha = 0.5f), shape = RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Loading...", color = Color.White)
                    }
                }
            }

            hasError.value -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Can't connect to the Best Footage", fontSize = 20.sp, color = Color.Black)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.loadImages() }) {
                            Text("Retry")
                        }
                    }
                }
            }

            else -> {
                /*LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 130.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(backgroundColor)
                )*/
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Adaptive(minSize = 130.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(backgroundColor)
                )
                {

                    items(images.value) { image ->
                        imageItem(imageResponse = image)
                    }
                }
            }
        }
    }
}

@Composable
fun imageItem(imageResponse: ImageResponse) {
    val imageWidth = imageResponse.width
    val imageHeight = imageResponse.height
    val myRatio = imageWidth.toFloat()/imageHeight.toFloat()
    val painter = rememberAsyncImagePainter(imageResponse.url)

    Box(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .aspectRatio(myRatio),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

