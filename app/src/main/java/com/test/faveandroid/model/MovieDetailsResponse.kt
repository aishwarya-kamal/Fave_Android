package com.test.faveandroid.model

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class MovieDetailsResponse(
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    @SerializedName("spoken_languages") val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Genre(
        val id: Int,
        val name: String
    ) : Parcelable

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class SpokenLanguage(
        @SerializedName("english_name") val englishName: String,
        @SerializedName("iso_639_1") val iso6391: String,
        val name: String
    ) : Parcelable
}