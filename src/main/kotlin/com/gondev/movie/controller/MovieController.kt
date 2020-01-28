package com.gondev.movie.controller

import com.gondev.movie.model.entity.Movie
import com.gondev.movie.model.repository.CommentRepository
import com.gondev.movie.model.repository.MovieRepository
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/movie")
class MovieController(
        val movieRepository: MovieRepository,
        val commentRepository: CommentRepository
) {
    @GetMapping
    fun getMovieList(@RequestParam("type") type: Int = 1) =
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
                            @RequestParam("likeyn") likeyn: String)=
            if(likeyn.equals("y", true)){
                movieRepository.increaseLike(movieId)
            }
            else if(likeyn.equals("n", true)){
                movieRepository.decreaseLike(movieId)
            }
            else throw IllegalArgumentException("likeyn은 'y' 또는 'n'으로 표기 되어야 합니다")

    @GetMapping("/{movieId}/increaseDislike")
    fun increaseDislike(@PathVariable movieId: Long,
                            @RequestParam("dislikeyn") likeyn: String)=
            if(likeyn.equals("y", true)){
                movieRepository.increaseDisLike(movieId)
            }
            else if(likeyn.equals("n", true)){
                movieRepository.decreaseDisLike(movieId)
            }
            else throw IllegalArgumentException("dislikeyn은 'y' 또는 'n'으로 표기 되어야 합니다")

    @PostMapping
    fun saveMove(@RequestBody movie: Movie) =
            movieRepository.save(movie)

    @DeleteMapping("/{movieId}")
    fun deleteMovie(@PathVariable movieId: Long) =
            movieRepository.deleteById(movieId)

    @GetMapping("/{movieId}/comment")
    fun getCommentList(@PathVariable movieId: Long) =
            commentRepository.findAllByMovieId(movieId)

}