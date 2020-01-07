package com.gondev.movie.controller

import com.gondev.movie.model.repository.CommentRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/movie/{movieId}/comment")
class CommentController(
        val commentRepository: CommentRepository
) {
    @GetMapping
    fun getCommentList(@PathVariable movieId: Long) =
            commentRepository.findAllByMovieId(movieId)
}