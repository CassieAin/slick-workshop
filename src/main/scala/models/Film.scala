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


