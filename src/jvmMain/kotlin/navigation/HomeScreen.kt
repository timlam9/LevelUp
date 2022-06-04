package navigation

import androidx.compose.runtime.Composable
import composables.NoteList
import data.noteList

@Composable
fun HomeScreen() {
    NoteList(notes = noteList)
}