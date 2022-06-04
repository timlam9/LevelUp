package composables

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
import model.Note

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
                description = it.description
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}