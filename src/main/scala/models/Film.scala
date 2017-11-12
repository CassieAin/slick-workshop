package models

import scala.concurrent.duration.Duration
import slick.jdbc.PostgresProfile.api._

case class Film (
  id: Option[Long],
  title: String,
  duration: Duration,
  directorId: Long,
  rating: Double
)

class FilmTable(tag: Tag) extends Table[Film](tag, "film") {
  val id = column[Option[Long]]("id", O.PrimaryKey)
  val title = column[String]("title")
  val duration = column[Duration]("duration")
  val directorId = column[Long]("directorId")
  val rating = column[Double]("rating")
  def * = (id, title, duration, directorId, rating)  <> ((Film.apply _).tupled, Film.unapply)
}

case class FilmToGenre(
  id: Option[Long],
  filmId: Long,
  genreId: Long
)

class FilmToGenreTable(tag: Tag) extends Table[FilmToGenre](tag, "film_to_genre") {
  val id = column[Option[Long]]("id", O.PrimaryKey)
  val filmId = column[Long]("film_id")
  val genreId = column[Long]("genre_id")
  def * = (id, filmId, genreId)  <> ((FilmToGenre.apply _).tupled, FilmToGenre.unapply)
}

case class FilmToCast(
  id: Option[Long],
   filmId: Long,
   staffId: Long
   )

class FilmToCastTable(tag: Tag) extends Table[FilmToCast](tag, "film_to_cast") {
  val id = column[Option[Long]]("id", O.PrimaryKey)
  val filmId = column[Long]("film_id")
  val staffId = column[Long]("staff_id")
  def * = (id, filmId, staffId)  <> ((FilmToCast.apply _).tupled, FilmToCast.unapply)
}

case class FilmToCountry(
  id: Option[Long],
  filmId: Long,
  countryId: Long
  )

class FilmToCountryTable(tag: Tag) extends Table[FilmToCountry](tag, "film_to_country") {
  val id = column[Option[Long]]("id", O.PrimaryKey)
  val filmId = column[Long]("film_id")
  val countryId = column[Long]("country_id")
  def * = (id, filmId, countryId)  <> ((FilmToCountry.apply _).tupled, FilmToCountry.unapply)
}
