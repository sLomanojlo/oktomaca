package rs.sloman.oktomaca.model


data class CommitBase(
    val sha: String,
    val commit: Commit,
    val author: Author
)