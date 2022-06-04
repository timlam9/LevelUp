package composables

import Blue
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun MainFAB(
    backgroundColor: Color = Blue,
    icon: ImageVector = Icons.Filled.Add,
    onClick: () -> Unit
) {
    FloatingActionButton(
        backgroundColor = backgroundColor,
        onClick = onClick
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "FAB"
        )
    }
}