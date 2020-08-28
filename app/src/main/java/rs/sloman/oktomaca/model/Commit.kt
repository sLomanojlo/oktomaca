package rs.sloman.oktomaca.model

import com.google.gson.annotations.SerializedName


data class Commit (
    val message: String,
    @SerializedName("author") val commitAuthor: CommitAuthor
)