package rs.sloman.oktomaca.model

import com.google.gson.annotations.SerializedName


data class UserRepo (
    val id : Int,
    val name: String,
    val description : String = "",
    val size : Int,
    @SerializedName("open_issues") val openIssues : Int,
    @SerializedName("commits_url") val commitsUrl : String,
    @SerializedName("default_branch") val defaultBranch : String
)