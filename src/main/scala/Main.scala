import slick.jdbc.PostgresProfile.api._
import models._
import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Main extends App{
  val db = Database.forConfig("scalaxdb")

  Await.result(db.run(CountryTable.table.schema.create), Duration.Inf)
  Await.result(db.run(StaffTable.table.schema.create), Duration.Inf)
  Await.result(db.run(GenreTable.table.schema.create), Duration.Inf)
  Await.result(db.run(FilmTable.table.schema.create), Duration.Inf)
  Await.result(db.run(FilmToGenreTable.table.schema.create), Duration.Inf)
  Await.result(db.run(FilmToCountryTable.table.schema.create), Duration.Inf)
  Await.result(db.run(FilmToCastTable.table.schema.create), Duration.Inf)
}
