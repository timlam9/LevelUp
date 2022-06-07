package presentation.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.repository.Repository
import presentation.navigation.screens.AddNoteScreen
import presentation.navigation.screens.ChartsScreen
import presentation.navigation.screens.HomeScreen
import presentation.ui.model.Note

private val APP_PADDING = 20.dp

@Composable
fun Navigation(
    screenState: Screen,
    repository: Repository,
    onHomeClicked: () -> Unit,
    onChartsClicked: () -> Unit,
    onCancelClicked: () -> Unit,
    onAddClicked: (Note) -> Unit,
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
        Spacer(modifier = Modifier.height(APP_PADDING))
        when (screenState) {
            is Screen.Home -> HomeScreen(repository = repository)
            is Screen.Charts -> ChartsScreen()
            is Screen.AddNote -> AddNoteScreen(
                onCancelClicked = onCancelClicked,
                onAddClicked = onAddClicked
            )
        }
    }
}