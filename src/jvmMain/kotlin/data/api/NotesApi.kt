package data.api

import data.model.NoteRaw
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import java.net.ConnectException
import java.nio.channels.UnresolvedAddressException

class NotesApi(private val client: HttpClient = DefaultHttpClient.client) {

    suspend fun getUserNotes(userID: String): List<NoteRaw> = try {
        client.get {
            url {
                path(ROUTE_NOTES)
                parameter(USER_ID, userID)
            }
        }
    } catch (ex: RedirectResponseException) {
        println("3xx Error: ${ex.response.status.description}")
        emptyList()
    } catch (ex: ClientRequestException) {
        println("4xx Error: ${ex.response.status.description}")
        emptyList()
    } catch (ex: ServerResponseException) {
        println("5xx Error: ${ex.response.status.description}")
        emptyList()
    } catch (e: UnresolvedAddressException) {
        println("Parsing Error: ${e.message}")
        emptyList()
    } catch (e: Exception) {
        println("Parsing Error 2: ${e.message}")
        emptyList()
    } catch (e: ConnectException) {
        println("Connected Error: ${e.message}")
        emptyList()
    }

    suspend fun addUserNote(userID: String, noteRaw: NoteRaw): NoteRaw? = try {
        client.post<NoteRaw> {
            url {
                path(ROUTE_POST_NOTE)
                body = noteRaw
                parameter(USER_ID, userID)
            }
        }
    } catch (ex: RedirectResponseException) {
        println("3xx Error: ${ex.response.status.description}")
        null
    } catch (ex: ClientRequestException) {
        println("4xx Error: ${ex.response.status.description}")
        null
    } catch (ex: ServerResponseException) {
        println("5xx Error: ${ex.response.status.description}")
        null
    }

    suspend fun updateNote(userID: String, noteRaw: NoteRaw): NoteRaw? = try {
        client.put<NoteRaw> {
            url {
                path(ROUTE_POST_NOTE)
                body = noteRaw
                parameter(USER_ID, userID)
            }
        }
    } catch (ex: RedirectResponseException) {
        println("3xx Error: ${ex.response.status.description}")
        null
    } catch (ex: ClientRequestException) {
        println("4xx Error: ${ex.response.status.description}")
        null
    } catch (ex: ServerResponseException) {
        println("5xx Error: ${ex.response.status.description}")
        null
    }

    suspend fun deleteNote(userID: String, noteRaw: NoteRaw): Boolean = try {
        client.delete<Boolean> {
            url {
                path(ROUTE_POST_NOTE)
                body = noteRaw
                parameter(USER_ID, userID)
            }
        }
        true
    } catch (ex: RedirectResponseException) {
        println("3xx Error: ${ex.response.status.description}")
        false
    } catch (ex: ClientRequestException) {
        println("4xx Error: ${ex.response.status.description}")
        false
    } catch (ex: ServerResponseException) {
        println("5xx Error: ${ex.response.status.description}")
        false
    }

    companion object {

        private const val USER_ID = "user_id"
        private const val ROUTE_NOTES = "notes"
        private const val ROUTE_POST_NOTE = "postNote"
    }
}