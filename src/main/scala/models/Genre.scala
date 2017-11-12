package models

import slick.jdbc.PostgresProfile.api._

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