package com.gondev.movie.controller

import com.gondev.movie.model.entity.Comment
import com.gondev.movie.model.repository.CommentRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/movie/{movieId}/comment")
class CommentController(
        val commentRepository: CommentRepository
) {

    @GetMapping("/{commentId}/recommend")
    fun increaseRecommend(@PathVariable commentId: Long,
                          @RequestParam("writer") writer: String) =
            commentRepository.increaseRecommend(commentId)

    @PostMapping
    fun writeComment(@PathVariable movieId: Long,
                     @RequestParam("writer") writer: String,
                     @RequestParam("contents") contents: String) =
            commentRepository.save(Comment(writer, contents))
}
