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
    val writer_image: String? ,
    val rating: Float, //":7.0,
    val contents: String , //":"ㅅㅅㅅㅅ",
    var recommend: Int, //":0
    @JsonIgnore
    @ManyToOne(targetEntity = Movie::class, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="movie_id", nullable = false)
    var movie: Movie?=null
): BaseAudit()