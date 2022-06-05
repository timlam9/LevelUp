package presentation.navigation.screens

import androidx.compose.runtime.*
import data.repository.Repository
import data.model.NoteRaw.Companion.toNote
import presentation.ui.composables.NoteList
import presentation.ui.model.Note

@Composable
fun HomeScreen(repository: Repository = Repository()) {
    var notes by remember { mutableStateOf(emptyList<Note>()) }
    LaunchedEffect(true) {
        notes = repository.getUserNotes("user1")
            .map { it.toNote() }
            .sortedBy { it.completed }
    }
    NoteList(notes = notes)
}