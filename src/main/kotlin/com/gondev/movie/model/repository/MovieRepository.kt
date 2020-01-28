package com.gondev.movie.model.repository

import com.gondev.movie.model.entity.Movie
import com.gondev.movie.model.projection.MovieListItem
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MovieRepository: JpaRepository<Movie, Long> {
	fun findAllBy(sort: Sort): List<MovieListItem>

	fun findAllByTitleOrTitleEng(title: String, title_eng: String): List<Movie>

	@Query("UPDATE Movie SET movie_like = movie_like + 1 WHERE id = :movieId")
	fun increaseLike(movieId: Long)

	@Query("UPDATE Movie SET dislike = dislike + 1 WHERE id = :movieId")
	fun increaseDisLike(movieId: Long)

	@Query("UPDATE Movie SET movie_like = movie_like - 1 WHERE id = :movieId")
	fun decreaseLike(movieId: Long)

	@Query("UPDATE Movie SET dislike = dislike - 1 WHERE id = :movieId")
	fun decreaseDisLike(movieId: Long)
}