package com.test.faveandroid.model

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import kotlinx.android.parcel.RawValue

@SuppressLint("ParcelCreator")
@Parcelize
data class MovieResponse(
    @SerializedName("total_results") val totalResults: Int,
    val page: Int,
    @SerializedName("total_pages") val totalPages: Int,
    val results: List<Result>
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Result(
        @SerializedName("vote_average") val voteAverage: Int,
        val id: Int,
        val overview: String,
        @SerializedName("release_date") val releaseDate: String,
        val popularity: Double,
        val adult: Boolean,
        @SerializedName("backdrop_path") val backdropPath: @RawValue Any?,
        @SerializedName("genre_ids") val genreIds: List<Int>,
        @SerializedName("poster_path") val posterPath: String?,
        @SerializedName("original_language") val originalLanguage: String,
        @SerializedName("original_title") val originalTitle: String,
        val title: String,
        @SerializedName("vote_count") val voteCount: Int,
        val video: Boolean
    ) : Parcelable
}