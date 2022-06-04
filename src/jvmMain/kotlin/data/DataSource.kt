package data

import model.Note

private const val DESC = "This is a test card. This is a test card. This is a test card. This is a test card. This is" +
        " a test card. This is a test card. This is a test card. This is a test card. This is a test card. This is a" +
        " test card. This is a test card. This is a test card. This is a test card. This is a test card. This is a test" +
        " card. This is a test card. This is a test card. This is a test card. This is a test card. "

val noteList: List<Note> = List(20) {
    Note(
        title = "Title ${it + 1}",
        description = DESC,
        completed = (it + 1).rem(2) == 0
    )
}.sortedBy { it.completed }