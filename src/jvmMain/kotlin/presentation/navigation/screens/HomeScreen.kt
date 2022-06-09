package presentation.navigation.screens

import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import data.model.NoteRaw.Companion.toNote
import data.repository.Repository
import kotlinx.coroutines.launch
import presentation.ui.composables.NoteList
import presentation.ui.model.Note

@Composable
fun HomeScreen(repository: Repository, windowState: WindowState) {
    var notes by remember { mutableStateOf(emptyList<Note>()) }
    val coroutineScope = rememberCoroutineScope()
    val showDetailsIcons = when {
        windowState.size.width < 700.dp -> false
        windowState.size.width < 1000.dp -> true
        else -> true
    }
    LaunchedEffect(true) {
        notes = updateNotes(repository)
    }
    NoteList(
        notes = notes,
        showDetailsIcons = showDetailsIcons,
        onDeletedClicked = {
            coroutineScope.launch {
                repository.deleteNote("user1", it)
                notes = updateNotes(repository)
            }
        },
        onCompletedClicked = {
            coroutineScope.launch {
                repository.updateNote("user1", it.copy(completed = !it.completed))
                notes = updateNotes(repository)
            }
        }
    )
}

private suspend fun updateNotes(repository: Repository) = repository.getUserNotes("user1")
    .map { it.toNote() }
    .sortedBy { it.completed }