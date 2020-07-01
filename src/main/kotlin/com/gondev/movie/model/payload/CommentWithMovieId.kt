package com.gondev.movie.model.payload

import com.gondev.movie.model.entity.Comment
import java.time.LocalDateTime

data class CommentWithMovieId(
        var id:Long=0,
        var movieId: Long,
        val writer: String,
        val contents: String, //":"ㅅㅅㅅㅅ",
        val writer_image: String? = null,
        val rating: Float = 0f, //":7.0,
        var createAt: LocalDateTime?=null,
        var modifyAt: LocalDateTime?=null
) {
    constructor(comment: Comment):this(
            comment.id,
            comment.movie?.id?:0L,
            comment.writer,
            comment.contents,
            comment.writer_image,
            comment.rating,
            comment.createAt,
            comment.modifyAt
    )
}