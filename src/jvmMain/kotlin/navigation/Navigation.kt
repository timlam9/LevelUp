package navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private val APP_PADDING = 20.dp

@Composable
fun Navigation(
    screenState: Screen,
    onHomeClicked: () -> Unit,
    onChartsClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = APP_PADDING, end = APP_PADDING, top = APP_PADDING)
    ) {
        TabBar(
            onHomeClicked = onHomeClicked,
            onChartsClicked = onChartsClicked
        )
        when (screenState) {
            is Screen.Home -> HomeScreen()
            is Screen.Charts -> ChartsScreen()
        }
    }
}