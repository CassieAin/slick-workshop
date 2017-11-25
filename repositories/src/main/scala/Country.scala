import model._
import scala.concurrent.Future
import slick.jdbc.PostgresProfile.api._

class CountryTable(tag: Tag) extends Table[Country](tag, "countries") {
  val id = column[Long]("id", O.PrimaryKey)
  val title = column[String]("title")
  def * = (id.?, title)  <> ((Country.apply _).tupled, Country.unapply)
}

object CountryTable{
  val table = TableQuery[CountryTable]
}

class CountryRepository(db: Database){
  val countryTableQuery = TableQuery[CountryTable]

  def create(country: Country): Future[Country] =
    db.run(countryTableQuery returning countryTableQuery += country)

  def update(country: Country): Future[Int] =
    db.run(countryTableQuery.filter(_.id === country.id).update(country))

  def delete(country: Country): Future[Int] =
    db.run(countryTableQuery.filter(_.id === country.id).delete)

  def getById(country: Country): Future[Option[Country]] =
    db.run(countryTableQuery.filter(_.id === country.id).result.headOption)
}
