package composables

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun BoxScope.ClassicButton() {
    var text by remember { mutableStateOf("Hello, World!") }

    Button(
        modifier = Modifier.align(Alignment.Center),
        onClick = {
            text = "Hello, Desktop!"
        }
    ) {
        Text(
            text = text,
            fontSize = 30.sp
        )
    }
}