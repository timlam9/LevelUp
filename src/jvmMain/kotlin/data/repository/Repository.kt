package data.repository

import data.api.DefaultHttpClient
import data.api.NotesApi
import data.model.NoteRaw

class Repository(private val api: NotesApi = NotesApi(client = DefaultHttpClient.client)) {

    suspend fun getUserNotes(userID: String): List<NoteRaw> = api.getUserNotes(userID)

    suspend fun addUserNote(userID: String, noteRaw: NoteRaw): NoteRaw? = api.addUserNote(userID, noteRaw)
}





