package models.service

trait Service [T]{
  def add(data: T)
  def list(): Seq[T]
  def findById(id: String): T
  def delete(id: String)
  def update(data: T)
}
