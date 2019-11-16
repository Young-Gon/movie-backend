package com.gondev.movie.model.repository

import com.gondev.movie.model.entity.Movie
import com.gondev.movie.model.projection.MovieListItem
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MovieRepository: JpaRepository<Movie, Long> {
	fun findAllBy(sort: Sort): List<MovieListItem>

	fun findAllByTitleOrTitleEng(title: String, title_eng: String): List<Movie>
}