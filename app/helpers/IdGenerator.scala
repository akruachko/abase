package helpers

object IdGenerator{
  def randomId = java.util.UUID.randomUUID().toString.replace("-", "")
}
