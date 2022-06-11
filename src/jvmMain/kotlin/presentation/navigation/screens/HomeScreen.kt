package presentation.navigation.screens

import androidx.compose.runtime.*
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import data.model.NoteRaw.Companion.toNote
import data.repository.Repository
import kotlinx.coroutines.launch
import presentation.ui.composables.NoteList
import presentation.ui.model.Note

@Composable
fun HomeScreen(
    repository: Repository,
    windowState: WindowState,
    keyboardActionCode: Int,
    onUpdateClicked: () -> Unit
) {
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
        keyboardActionCode = keyboardActionCode,
        onDeletedClicked = {
            coroutineScope.launch {
                repository.deleteNote(USER_1, it)
                notes = updateNotes(repository)
            }
        },
        onUpdateClicked = {
            onUpdateClicked()
            coroutineScope.launch {
                repository.updateNote(USER_1, it)
                notes = updateNotes(repository)
            }
        },
        onCompletedClicked = {
            coroutineScope.launch {
                repository.updateNote(USER_1, it.copy(completed = !it.completed))
                notes = updateNotes(repository)
            }
        }
    )
}

private const val USER_1 = "user1"

private suspend fun updateNotes(repository: Repository) = repository.getUserNotes(USER_1)
    .map { it.toNote() }
    .sortedBy { it.completed }