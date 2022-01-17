package com.nidhin.newsapp.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.nidhin.newsapp.homescreen.viewmodel.MainViewModel
import com.nidhin.newsapp.presentation.homescreen.HomeScreenState
import com.nidhin.newsapp.presentation.homescreen.components.HomeScreen
import com.nidhin.newsapp.presentation.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @ExperimentalComposeUiApi
    @ExperimentalUnitApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        setContent {
            NewsAppTheme() {
                val viewState by viewModel.state
                InitStateObservers(viewState = viewState)
            }
        }
    }


    @ExperimentalUnitApi
    @ExperimentalComposeUiApi
    @Composable
    fun InitStateObservers(viewState: HomeScreenState) {
        var showErrorDialog by remember { mutableStateOf(viewState.error.isNotEmpty()) }

        if (showErrorDialog) {
            Dialog(
                properties = DialogProperties(usePlatformDefaultWidth = false),
                onDismissRequest = {
                    showErrorDialog = !showErrorDialog
                }) {
                Surface(modifier = Modifier.fillMaxWidth(0.85f)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 20.dp)
                    ) {
                        Text(
                            text = viewState.error,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 4.dp)
                        ) {
                            Spacer(modifier = Modifier.weight(1.0f))
                            Button(
                                onClick = {
                                    showErrorDialog = false
                                    viewModel.getTopHeadlines()
                                },
                                modifier = Modifier
                                    .padding(all = 5.dp)
                                    .weight(1.0f, true)
                            ) {
                                Text(text = "Retry")
                            }
                            Button(
                                onClick = { finish() },
                                modifier = Modifier
                                    .padding(all = 5.dp)
                                    .weight(1.0f, true)
                            ) {
                                Text(text = "Exit")
                            }
                        }
                    }
                }
            }
        } else {
            HomeScreen(viewModel = viewModel, onClick = {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(it.url)
                    )
                )
            }, onCategorySelect = { viewModel.getCategoryWiseHeadlines(it) })
        }
    }
}
