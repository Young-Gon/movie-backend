package com.gondev.movie.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.gondev.movie.model.audit.BaseAudit
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class Comment(
    val writer: String,
    val contents: String , //":"ㅅㅅㅅㅅ",
    val writer_image: String? = null,
    val rating: Float = 0f, //":7.0,
    var recommend: Int=0, //":0
    @JsonIgnore
    @ManyToOne(targetEntity = Movie::class, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="movie_id", nullable = false)
    var movie: Movie?=null
): BaseAudit()