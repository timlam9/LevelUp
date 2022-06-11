package presentation.navigation.screens

import Green
import Red
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.model.NoteRaw.Companion.toNote
import data.repository.Repository
import presentation.ui.composables.ProgressChart

@Composable
fun ChartsScreen(repository: Repository) {
    var notesSize by remember { mutableStateOf(0) }
    var completedNotesSize by remember { mutableStateOf(0) }

    LaunchedEffect(true) {
        val notes = repository.getUserNotes("user1").map { it.toNote() }
        notesSize = notes.size
        completedNotesSize = notes.filter { it.completed }.size
    }

    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (notesSize > 0) {
            Box(contentAlignment = Alignment.Center) {
                ProgressChart(
                    total = notesSize,
                    completed = completedNotesSize,
                    size = 350.dp
                )
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "$completedNotesSize/$notesSize | ${(completedNotesSize * 100) / notesSize}%",
                        color = Green,
                        fontSize = 24.sp
                    )
                    Text(
                        text = "Notes Completed",
                        color = Red,
                        fontSize = 24.sp
                    )
                }
            }
        }
    }
}