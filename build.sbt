name := """abase"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala).dependsOn()

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
                              jdbc,
                              cache,
                              ws,
                              javaJdbc,
                              evolutions,
                              "org.liquibase" % "liquibase-core" % "3.5.3",
                              "org.squeryl" %% "squeryl" % "0.9.5-7",
                              "org.postgresql" % "postgresql" % "9.3-1102-jdbc4"
                          )

