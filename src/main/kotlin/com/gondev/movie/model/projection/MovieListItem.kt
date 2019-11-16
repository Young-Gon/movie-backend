package com.gondev.movie.model.projection

interface MovieListItem {
	val id: Long
	val title: String?
	val titleEng: String?
	val date: String?
	val userRating: Float
	val audienceRating: Float
	val reviewerRating: Float
	val reservationRate: Float
	val reservationGrade: Int
	val grade: Int
	val thumb: String?
	val image: String?
}