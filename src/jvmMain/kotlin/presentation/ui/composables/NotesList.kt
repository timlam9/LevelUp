package presentation.ui.composables

import Green
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import presentation.ui.model.Note

@Composable
fun NoteList(
    modifier: Modifier = Modifier,
    notes: List<Note>,
    showDetailsIcons: Boolean,
    title: String = "Notes",
    keyboardActionCode: Int,
    onDeletedClicked: (note: Note) -> Unit,
    onUpdateClicked: (note: Note) -> Unit,
    onCompletedClicked: (note: Note) -> Unit,
) {
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        item {
            Text(
                text = title,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    color = MaterialTheme.colors.onBackground
                ),
                modifier = modifier.padding(20.dp)
            )
        }
        items(items = notes) { note ->
            ExpandableCard(
                title = note.title,
                description = note.text,
                showDetailsIcons = showDetailsIcons,
                color = if (note.completed) Green else MaterialTheme.colors.background,
                completed = note.completed,
                keyboardActionCode = keyboardActionCode,
                onDeletedClicked = { onDeletedClicked(note) },
                onUpdateClicked = { onUpdateClicked(note.copy(text = it)) },
                onCompletedClicked = { onCompletedClicked(note) })
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}