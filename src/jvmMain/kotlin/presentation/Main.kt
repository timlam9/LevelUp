// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import data.repository.Repository
import kotlinx.coroutines.launch
import presentation.ui.composables.MainFAB
import presentation.navigation.Navigation
import presentation.navigation.Screen

private const val APP_TITLE = "Level Up"
fun main() = application {
    getDarkMode()
    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(
            width = 1800.dp,
            height = 1200.dp
        ),
        title = APP_TITLE
    ) {
        App()
    }
}

@Preview
@Composable
fun App(repository: Repository = Repository()) {
    var colors by remember { mutableStateOf(colors()) }
    var surfaceColor by remember { mutableStateOf(getSurfaceColor()) }
    var screenState by rememberSaveable { mutableStateOf<Screen>(Screen.Home) }
    val coroutineScope = rememberCoroutineScope()

    onIsInDarkModeChanged = { _, _ ->
        colors = colors()
        surfaceColor = getSurfaceColor()
    }

    MaterialTheme(colors = colors) {
        Scaffold(
            backgroundColor = surfaceColor,
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                if (screenState == Screen.Home) {
                    MainFAB {
                        screenState = Screen.AddNote
                    }
                }
            }
        ) {
            Navigation(
                screenState = screenState,
                repository = repository,
                onHomeClicked = { screenState = Screen.Home },
                onChartsClicked = { screenState = Screen.Charts },
                onCancelClicked = { screenState = Screen.Home },
                onAddClicked = { note ->
                    coroutineScope.launch {
                        repository.addUserNote("user1", note).also { response ->
                            if (response != null)
                                screenState = Screen.Home
                        }
                    }
                }
            )
        }
    }
}

@Stable
private fun getSurfaceColor() = if (isInDarkMode) Black else LightGray
