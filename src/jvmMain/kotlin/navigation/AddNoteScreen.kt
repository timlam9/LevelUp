package navigation

import Blue
import EMPTY
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composables.DefaultButton
import composables.NoteTextField
import composables.OutlinedButton

@Composable
fun AddNoteScreen(onCancelClicked: () -> Unit, onAddClicked: () -> Unit) {
    val noteTitle = rememberSaveable { mutableStateOf(EMPTY) }
    val noteInput = rememberSaveable { mutableStateOf(EMPTY) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NoteTextField(
            text = noteTitle.value,
            label = "Title",
            maxLines = 1,
            singleLine = true
        ) {
            noteTitle.value = it
        }
        NoteTextField(
            modifier = Modifier.weight(1f),
            text = noteInput.value,
            label = "Add your note here"
        ) {
            noteInput.value = it
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                text = "Cancel",
                color = Blue,
                onclick = onCancelClicked
            )
            Spacer(modifier = Modifier.width(20.dp))
            DefaultButton(
                text = "Add note",
                color = Blue,
                onclick = onAddClicked
            )
        }
    }
}