package presentation.ui.model

data class Note(
    val id: Int,
    val title: String,
    val text: String,
    val completed: Boolean
)