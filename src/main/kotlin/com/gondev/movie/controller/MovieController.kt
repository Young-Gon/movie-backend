package com.gondev.movie.controller

import com.gondev.movie.MovieApplication
import com.gondev.movie.exception.ResourceNotFoundException
import com.gondev.movie.model.entity.Comment
import com.gondev.movie.model.entity.Movie
import com.gondev.movie.model.payload.CommentWithMovieId
import com.gondev.movie.model.projection.MovieListItem
import com.gondev.movie.model.repository.CommentRepository
import com.gondev.movie.model.repository.MovieRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/movie")
class MovieController(
        val movieRepository: MovieRepository,
        val commentRepository: CommentRepository
) {

    var logger: Logger = LoggerFactory.getLogger(MovieApplication::class.java)

    @GetMapping
    fun getMovieList(@RequestParam("type") type: Int = 1): List<MovieListItem> =
        movieRepository.findAllBy(Sort.by(Sort.Direction.DESC, when (type) {
            1 -> "reservationRate"
            2 -> "reviewerRating"
            3 -> "date"
            else -> throw IllegalArgumentException("지원 하지 않는 정렬 순서입니다 (order type=$type)")
        }))

    @GetMapping("/{movieId}")
    fun getMovieById(@PathVariable movieId: Long) =
            movieRepository.findById(movieId)

    @GetMapping("/search")
    fun searchMovie(@RequestParam("name") name: String) =
            movieRepository.findAllByTitleOrTitleEng(name, name)

    @GetMapping("/{movieId}/increaseLike")
    fun increaseLike(@PathVariable movieId: Long,
                     @RequestParam("likeyn") likeyn: String) =
            when {
                likeyn.equals("y", true) ->
                    movieRepository.increaseLike(movieId)
                likeyn.equals("n", true) ->
                    movieRepository.decreaseLike(movieId)
                else -> throw IllegalArgumentException("likeyn은 'y' 또는 'n'으로 표기 되어야 합니다")
            }

    @GetMapping("/{movieId}/increaseDislike")
    fun increaseDislike(@PathVariable movieId: Long,
                        @RequestParam("dislikeyn") likeyn: String) =
            when {
                likeyn.equals("y", true) ->
                    movieRepository.increaseDisLike(movieId)
                likeyn.equals("n", true) ->
                    movieRepository.decreaseDisLike(movieId)
                else -> throw IllegalArgumentException("dislikeyn은 'y' 또는 'n'으로 표기 되어야 합니다")
            }

    @PostMapping
    fun saveMove(@RequestBody movie: Movie) =
        movieRepository.save(movie)

    @DeleteMapping("/{movieId}")
    fun deleteMovie(@PathVariable movieId: Long) =
            movieRepository.deleteById(movieId)

    @GetMapping("/{movieId}/comment")
    fun getCommentList(@PathVariable movieId: Long) =
            commentRepository.findAllByMovieId(movieId).map {
                CommentWithMovieId(it)
            }

    @PostMapping("/{movieId}/comment")
    fun writeComment(@PathVariable movieId: Long, @RequestBody comment:Comment) =
            movieRepository.findById(movieId).map { movie ->
                comment.movie=movie
                commentRepository.save(comment).id
            }.orElseThrow {
                throw ResourceNotFoundException("요청한 영화 정보(id=$movieId)를 찾을 수 없습니다.")
            }
}
