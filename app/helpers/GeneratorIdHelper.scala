package helpers

object GeneratorIdHelper{
  def generatorId = java.util.UUID.randomUUID().toString.replace("-", "")
}
