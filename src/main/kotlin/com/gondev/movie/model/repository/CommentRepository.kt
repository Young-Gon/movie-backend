package com.gondev.movie.model.repository

import com.gondev.movie.model.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository: JpaRepository<Comment,Long> {
    fun findAllByMovieId(movieId: Long): List<Comment>
}