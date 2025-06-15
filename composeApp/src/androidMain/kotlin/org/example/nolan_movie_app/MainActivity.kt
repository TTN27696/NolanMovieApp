package org.example.nolan_movie_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.nolan_movie_app.di.androidModule
import org.example.nolan_movie_app.di.sharedModule
import org.example.nolan_movie_app.screen.MovieListScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        startKoin {
            androidContext(this@MainActivity)
            modules(sharedModule, androidModule)
        }
        setContent {
            MovieListScreen()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    MovieListScreen()
}