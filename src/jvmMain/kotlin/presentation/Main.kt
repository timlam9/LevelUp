// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import data.repository.Repository
import kotlinx.coroutines.launch
import presentation.navigation.Navigation
import presentation.navigation.Screen
import presentation.ui.composables.MainFAB

private const val APP_TITLE = "Level Up"
fun main() = application {
    val windowState: WindowState = rememberWindowState(width = 1200.dp, height = 800.dp)
    var keyboardActionCode: Int by remember { mutableStateOf(-1) }

    getDarkMode()
    Window(
        onCloseRequest = ::exitApplication,
        state = windowState,
        title = APP_TITLE,
        onKeyEvent = {
            keyboardActionCode = it.getNativeKeyCode()
            println("KEY: $keyboardActionCode")
            false
        }
    ) {
        App(
            windowState = windowState,
            keyboardActionCode = keyboardActionCode,
            onUpdateClicked = { keyboardActionCode = -1 }
        )
    }
}

fun KeyEvent.getNativeKeyCode() = nativeKeyEvent.toString()
    .substringAfter("keyCode=")
    .takeWhile { char -> char.isDigit() }
    .toInt()

@Preview
@Composable
fun App(
    repository: Repository = Repository(),
    windowState: WindowState,
    keyboardActionCode: Int,
    onUpdateClicked: () -> Unit
) {
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
                windowState = windowState,
                repository = repository,
                keyboardActionCode = keyboardActionCode,
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
                },
                onUpdateClicked = onUpdateClicked
            )
        }
    }
}

@Stable
private fun getSurfaceColor() = if (isInDarkMode) Black else LightGray
