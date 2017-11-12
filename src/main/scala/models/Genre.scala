package models

case class Genre (
  id: Option[Long],
  name: String,
  description: Option[String]
)
