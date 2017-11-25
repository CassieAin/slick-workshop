import model.Staff

import scala.concurrent.Future
import slick.jdbc.PostgresProfile.api._

class StaffTable(tag: Tag) extends Table[Staff](tag, "staff") {
  val id = column[Long]("id", O.PrimaryKey)
  val name = column[String]("name")
  val rate = column[Double]("rate")
  val age = column[Int]("age")
  def * = (id.?, name, rate, age)  <> ((Staff.apply _).tupled, Staff.unapply)
}

object StaffTable{
  val table = TableQuery[StaffTable]
}

class StaffRepository(db: Database) {
  val staffTableQuery = TableQuery[StaffTable]

  def create(staff: Staff): Future[Staff] =
    db.run(staffTableQuery returning staffTableQuery += staff)

  def update(staff: Staff): Future[Int] =
    db.run(staffTableQuery.filter(_.id === staff.id).update(staff))

  def delete(staff: Staff): Future[Int] =
    db.run(staffTableQuery.filter(_.id === staff.id).delete)

  def getById(staff: Staff): Future[Option[Staff]] =
    db.run(staffTableQuery.filter(_.id === staff.id).result.headOption)
}