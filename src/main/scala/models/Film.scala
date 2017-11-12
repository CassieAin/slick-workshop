package models

import scala.concurrent.duration.Duration
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future

case class Film (
  id: Option[Long],
  title: String,
  duration: Duration,
  directorId: Long,
  rating: Double
)

class FilmTable(tag: Tag) extends Table[Film](tag, "film") {
  val id = column[Long]("id", O.PrimaryKey)
  val title = column[String]("title")
  val duration = column[Duration]("duration")
  val directorId = column[Long]("director_id")
  val rating = column[Double]("rating")

  val directorFk = foreignKey("director_id_fk", directorId, TableQuery[StaffTable])(_.id)

  def * = (id.?, title, duration, directorId, rating) <> (Film.apply _ tupled, Film.unapply)
}

object FilmTable{
  val table = TableQuery[FilmTable]
}

class FilmRepository(db: Database) {
  val filmTableQuery = TableQuery[FilmTable]

  def create(film: Film): Future[Film] =
    db.run(filmTableQuery returning filmTableQuery += film)

  def update(film: Film): Future[Int] =
    db.run(filmTableQuery.filter(_.id === film.id).update(film))

  def delete(film: Film): Future[Int] =
    db.run(filmTableQuery.filter(_.id === film.id).delete)

  def getById(film: Film): Future[Option[Film]] =
    db.run(filmTableQuery.filter(_.id === film.id).result.headOption)
}


case class FilmToGenre(
  id:  Option[Long],
  filmFk: Long,
  genreFk: Long
)

class FilmToGenreTable(tag: Tag) extends Table[FilmToGenre](tag, "film_to_genre") {
  val id = column[Long]("id", O.PrimaryKey)
  val filmId = column[Long]("film_id")
  val genreId = column[Long]("genre_id")

  val filmIdFk = foreignKey("film_id_fk", filmId, TableQuery[FilmTable])(_.id)
  val genreIdFk = foreignKey("genre_id_fk", genreId, TableQuery[GenreTable])(_.id)

  def * = (id.?, filmId, genreId) <> (FilmToGenre.apply _ tupled, FilmToGenre.unapply)
}

object FilmToGenreTable{
  val table = TableQuery[FilmToGenreTable]
}

class FilmToGenreRepository(db: Database) {
  val filmToGenreTableQuery = TableQuery[FilmToGenreTable]

  def create(film: FilmToGenre): Future[FilmToGenre] =
    db.run(filmToGenreTableQuery returning filmToGenreTableQuery += film)
}


case class FilmToCast(
   id:  Option[Long],
   filmFk: Long,
   staffFk: Long
   )

class FilmToCastTable(tag: Tag) extends Table[FilmToCast](tag, "film_to_cast") {
  val id = column[Long]("id", O.PrimaryKey)
  val filmId = column[Long]("film_id")
  val staffId = column[Long]("staff_id")

  val filmIdFk = foreignKey("film_id_fk", filmId, TableQuery[FilmTable])(_.id)
  val staffIdFk = foreignKey("staff_id_fk", staffId, TableQuery[StaffTable])(_.id)

  def * = (id.?, filmId, staffId) <> (FilmToCast.apply _ tupled, FilmToCast.unapply)
}

object FilmToCastTable{
  val table = TableQuery[FilmToCastTable]
}

class FilmToCastRepository(db: Database) {
  val filmToCastTableQuery = TableQuery[FilmToCastTable]

  def create(film: FilmToCast): Future[FilmToCast] =
    db.run(filmToCastTableQuery returning filmToCastTableQuery += film)
}

case class FilmToCountry(
  id: Option[Long],
  filmFk: Long,
  countryFk: Long
  )

class FilmToCountryTable(tag: Tag) extends Table[FilmToCountry](tag, "film_to_country") {
  val id = column[Long]("id", O.PrimaryKey)
  val filmId = column[Long]("film_id")
  val countryId = column[Long]("staff_id")

  val filmIdFk = foreignKey("film_id_fk", filmId, TableQuery[FilmTable])(_.id)
  val countryIdFk = foreignKey("country_id_fk", countryId, TableQuery[CountryTable])(_.id)

  def * = (id.?, filmId, countryId) <> (FilmToCountry.apply _ tupled, FilmToCountry.unapply)
}

object FilmToCountryTable{
  val table = TableQuery[FilmToCountryTable]
}

class FilmToCountryRepository(db: Database) {
  val filmToCountryTableQuery = TableQuery[FilmToCountryTable]

  def create(film: FilmToCountry): Future[FilmToCountry] =
    db.run(filmToCountryTableQuery returning filmToCountryTableQuery += film)
}