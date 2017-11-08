package controllers.helpers

trait GeneratorIdHelper{
  def generatorId = java.util.UUID.randomUUID().toString.replace("-", "")
}
