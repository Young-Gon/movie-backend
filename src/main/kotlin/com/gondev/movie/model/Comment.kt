package com.gondev.movie.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.gondev.movie.model.audit.BaseAudit
import com.gondev.movie.model.entity.Movie
import javax.persistence.*

@Entity
data class Comment (
		@JsonIgnore
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="movie_id")
		val movieId: Movie?,
		val writer: String,
		val writer_image: String?,
		val rating: Float, //":7.0,
		val contents: String, //":"ㅅㅅㅅㅅ",
		var recommend: Int //":0
):BaseAudit()