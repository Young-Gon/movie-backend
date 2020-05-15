package com.gondev.movie.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.gondev.movie.model.audit.BaseAudit
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import javax.persistence.*

const val LIKE = 1
const val NONE = 0
const val DISLIKE = -1

@Entity
data class Movie(
	val title: String,
	val titleEng: String?,
	val date: String,
	val userRating: Float,
	val audienceRating: Float,
	val reviewerRating: Float,
	val reservationRate: Float,
	val reservationGrade: Int,
	val grade: Int,
	val thumb: String?,
	val image: String?,
	@Column(length = 1024)
	val photos: String?,
	val videos: String?,
	val genre: String?,
	val duration: Int,
	val audience: Long,
	@Column(length = 1024)
	val synopsis: String?,
	val director: String?,
	val actor: String?,
	@Column(name = "movie_like")
	val like: Long,
	val dislike: Long,
    @JsonIgnore
    @OneToMany(mappedBy = "movie", cascade = [CascadeType.ALL], orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    var comments: MutableList<Comment> = mutableListOf()
): BaseAudit()