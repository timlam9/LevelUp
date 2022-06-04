// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import navigation.Navigation
import navigation.Screen

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
fun App() {
    var colors by remember { mutableStateOf(colors()) }
    var surfaceColor by remember { mutableStateOf(getSurfaceColor()) }
    var screenState by remember { mutableStateOf<Screen>(Screen.Home) }

    onIsInDarkModeChanged = { _, _ ->
        colors = colors()
        surfaceColor = getSurfaceColor()
    }

    MaterialTheme(colors = colors) {
        Surface(color = surfaceColor) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                floatingActionButton = {
                    FloatingActionButton(
                        backgroundColor = Blue,
                        onClick = {}
                    ) {
                        Icon(Icons.Filled.Add, "FAB")
                    }
                }
            ) {
                Navigation(
                    screenState = screenState,
                    onHomeClicked = { screenState = Screen.Home },
                    onChartsClicked = { screenState = Screen.Charts }
                )
            }
        }
    }
}

@Stable
private fun getSurfaceColor() = if (isInDarkMode) Black else LightGray
