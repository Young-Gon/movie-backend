package com.gondev.movie.controller

import com.gondev.movie.model.entity.Movie
import com.gondev.movie.model.repository.MovieRepository
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/movie")
class MovieController(
		val movieRepository: MovieRepository
) {
	@GetMapping
	fun getMovieList(@RequestParam("type") type: Int=1) =
		movieRepository.findAllBy(Sort.by(Sort.Direction.DESC, when(type){
			1 -> "reservationRate"
			2 -> "reviewerRating"
			3 -> "date"
			else -> throw IllegalArgumentException("지원 하지 않는 정렬 순서입니다 (order type=$type)")
		}))

	@GetMapping("{movieId}")
	fun getMovieById(@PathVariable movieId:Long)=
			movieRepository.findById(movieId)

	@GetMapping("/search")
	fun searchMovie(@RequestParam("name") name:String)=
			movieRepository.findAllByTitleOrTitleEng(name,name)

	@PostMapping
	fun saveMove(@RequestBody movie: Movie) =
			movieRepository.save(movie)
}