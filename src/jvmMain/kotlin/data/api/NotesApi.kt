package data.api

import data.api.DefaultHttpClient
import data.model.NoteRaw
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import java.nio.channels.UnresolvedAddressException

class NotesApi(private val client: HttpClient = DefaultHttpClient.client) {

    suspend fun getUserNotes(userID: String): List<NoteRaw> = try {
        client.get {
            url {
                path("$userID/notes")
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
    }

    suspend fun addUserNote(userID: String, noteRaw: NoteRaw): NoteRaw? = try {
        client.post<NoteRaw> {
            url {
                path("$userID/postNote")
                body = noteRaw
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
}