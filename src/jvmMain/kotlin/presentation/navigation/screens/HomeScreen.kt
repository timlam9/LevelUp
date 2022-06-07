package presentation.navigation.screens

import androidx.compose.runtime.*
import data.repository.Repository
import data.model.NoteRaw.Companion.toNote
import kotlinx.coroutines.launch
import presentation.ui.composables.NoteList
import presentation.ui.model.Note

@Composable
fun HomeScreen(repository: Repository) {
    var notes by remember { mutableStateOf(emptyList<Note>()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(true) {
        notes = updateNotes(repository)
    }
    NoteList(notes = notes) {
        coroutineScope.launch {
            repository.updateNote("user1", it.copy(completed = !it.completed))
            notes = updateNotes(repository)
        }
    }
}

private suspend fun updateNotes(repository: Repository) = repository.getUserNotes("user1")
    .map { it.toNote() }
    .sortedBy { it.completed }