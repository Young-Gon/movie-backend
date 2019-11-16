package com.gondev.movie.model.entity

import javax.persistence.*

const val LIKE = 1
const val NONE = 0
const val DISLIKE = -1

@Entity
data class Movie(
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	val id: Long,
	val title: String?,
	val titleEng: String?,
	val date: String?,
	val userRating: Float,
	val audienceRating: Float,
	val reviewerRating: Float,
	val reservationRate: Float,
	val reservationGrade: Int,
	val grade: Int,
	val thumb: String?,
	val image: String?,
	val photos: String?,
	val videos: String?,
	val genre: String?,
	val duration: Int,
	val audience: Long,
	val synopsis: String?,
	val director: String?,
	val actor: String?,
	@Column(name = "movie_like")
	val like: Long,
	val dislike: Long
) 