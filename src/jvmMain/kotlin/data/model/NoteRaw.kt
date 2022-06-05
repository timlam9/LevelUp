package data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import presentation.ui.model.Note

@Serializable
data class NoteRaw(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("text") val text: String,
    @SerialName("completed") val completed: Boolean
) {

    companion object {

        fun NoteRaw.toNote() = Note(
            id = id,
            title = title,
            text = text,
            completed = completed
        )
    }
}