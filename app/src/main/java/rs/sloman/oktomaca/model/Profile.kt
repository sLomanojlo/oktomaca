package rs.sloman.oktomaca.model

import com.google.gson.annotations.SerializedName


data class Profile(
    val id : Int,
    val name : String,
    val company: String,
    @SerializedName ("repos_url") val reposUrl: String,
    @SerializedName("avatar_url") val imgUrl: String

)