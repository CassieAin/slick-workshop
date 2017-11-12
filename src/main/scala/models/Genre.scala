package models

import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future

case class Genre (
  id: Option[Long],
  name: String,
  description: Option[String]
)

class GenreTable(tag: Tag) extends Table[Genre](tag, "genre") {
  val id = column[Long]("id", O.PrimaryKey)
  val name = column[String]("name")
  val description = column[Option[String]]("description")
  def * = (id.?, name, description)  <> ((Genre.apply _).tupled, Genre.unapply)
}

object GenreTable{
  val table = TableQuery[GenreTable]
}

class GenreRepository(db: Database) {
  val genreTableQuery = TableQuery[GenreTable]

  def create(genre: Genre): Future[Genre] =
    db.run(genreTableQuery returning genreTableQuery += genre)

  def update(genre: Genre): Future[Int] =
    db.run(genreTableQuery.filter(_.id === genre.id).update(genre))

  def delete(genre: Genre): Future[Int] =
    db.run(genreTableQuery.filter(_.id === genre.id).delete)

  def getById(genre: Genre): Future[Option[Genre]] =
    db.run(genreTableQuery.filter(_.id === genre.id).result.headOption)
}