package presentation.ui.composables

import Green
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import presentation.ui.model.Note

@Composable
fun NoteList(
    notes: List<Note>,
    modifier: Modifier = Modifier,
    title: String = "Notes"
) {
    LazyColumn {
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
        items(items = notes) {
            ExpandableCard(
                title = it.title,
                description = it.text,
                color = if (it.completed) Green else MaterialTheme.colors.background
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}