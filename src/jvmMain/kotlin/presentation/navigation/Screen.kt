package presentation.navigation

sealed class Screen {
    object Home : Screen()
    object Charts : Screen()
    object AddNote : Screen()

}