package presentation.ui.model

import data.model.NoteRaw

data class Note(
    val id: Int,
    val title: String,
    val text: String,
    val completed: Boolean
) {
    fun toRaw(): NoteRaw = NoteRaw(
        id = id,
        title = title,
        text = text,
        completed = completed
    )
}