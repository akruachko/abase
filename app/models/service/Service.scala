package models.service

trait Service [T]{
  def entInsert(data: T)
  def entSelectAll(): Seq[T]
  def entSelectById(id: String): T
  def entDelete(id: String)
}
