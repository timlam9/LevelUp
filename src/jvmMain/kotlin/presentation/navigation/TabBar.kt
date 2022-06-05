package presentation.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.ui.composables.IconButton

@Composable
fun TabBar(onHomeClicked: () -> Unit, onChartsClicked: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(
            icon = Icons.Default.Home,
            onclick = onHomeClicked
        )
        Spacer(modifier = Modifier.width(20.dp))
        IconButton(
            icon = Icons.Default.Star,
            onclick = onChartsClicked
        )
    }
}