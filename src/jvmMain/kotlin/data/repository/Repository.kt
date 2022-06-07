package data.repository

import data.api.DefaultHttpClient
import data.api.NotesApi
import data.model.NoteRaw
import presentation.ui.model.Note

class Repository(private val api: NotesApi = NotesApi(client = DefaultHttpClient.client)) {

    suspend fun getUserNotes(userID: String): List<NoteRaw> = api.getUserNotes(userID)

    suspend fun addUserNote(userID: String, note: Note): NoteRaw? = api.addUserNote(userID, note.toRaw())
}





