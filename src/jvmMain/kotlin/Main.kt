// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import composables.NoteList
import data.noteList

private const val APP_TITLE = "Level Up"
fun main() = application {
    getDarkMode()
    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(width = 800.dp, height = 600.dp),
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

    onIsInDarkModeChanged = { _, _ ->
        colors = colors()
        surfaceColor = getSurfaceColor()
    }

    MaterialTheme(colors = colors) {
        Surface(color = surfaceColor) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                NoteList(notes = noteList)
            }
        }
    }
}

@Stable
private fun getSurfaceColor() = if (isInDarkMode) {
    Black
} else {
    LightGray
}




