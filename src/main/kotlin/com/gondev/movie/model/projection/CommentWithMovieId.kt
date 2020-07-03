package com.gondev.movie.model.projection

import java.time.LocalDateTime

interface CommentWithMovieId {
        val id:Long
        val movieId: Long
        val writer: String
        val contents: String //":"ㅅㅅㅅㅅ",
        val writerImage: String
        val rating: Float  //":7.0,
        val createAt: LocalDateTime
        val modifyAt: LocalDateTime
}