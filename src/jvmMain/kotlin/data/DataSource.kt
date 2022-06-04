package data

import model.Note

private const val DESC =
    "This is a test card. This is a test card. This is a test card. This is a test card. This is a test card. This is a test card. This is a test card. This is a test card. This is a test card. This is a test card. This is a test card. This is a test card. This is a test card. This is a test card. This is a test card. This is a test card. This is a test card. This is a test card. This is a test card. "

val noteList: List<Note> = List(20) {
    Note("Title ${it + 1}", DESC)
}