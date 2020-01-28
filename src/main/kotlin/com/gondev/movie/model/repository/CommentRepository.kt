package com.gondev.movie.model.repository

import com.gondev.movie.model.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository: JpaRepository<Comment,Long> {
    fun findAllByMovieId(movieId: Long): List<Comment>

    @Query("UPDATE Comment SET recommend = recommend + 1 WHERE id = :commentId")
    fun increaseRecommend(commentId: Long)
}