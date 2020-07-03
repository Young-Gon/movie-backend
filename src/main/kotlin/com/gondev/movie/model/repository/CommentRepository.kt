package com.gondev.movie.model.repository

import com.gondev.movie.model.entity.Comment
import com.gondev.movie.model.projection.CommentWithMovieId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository: JpaRepository<Comment,Long> {
    fun findAllByMovieId(movieId: Long): List<CommentWithMovieId>

    @Query("UPDATE Comment SET recommend = recommend + 1 WHERE id = :commentId")
    fun increaseRecommend(commentId: Long)
}