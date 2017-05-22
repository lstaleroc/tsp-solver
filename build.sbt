name := """tsp_solver"""
organization := "com.example"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
  filters,
  javaJdbc,
  javaJpa,
  cache,
  javaWs,
  "org.hibernate" % "hibernate-core" % "5.2.4.Final",
  "mysql" % "mysql-connector-java" % "5.1.40",
  "org.hibernate" % "hibernate-envers" % "5.2.4.Final",
  "dom4j" % "dom4j" % "1.6.1"
)
