name := "dota-match-bot"

version := "0.1"

scalaVersion := "2.12.4"

mainClass := Some("com.shmigel.dotamatchbot.Test")

libraryDependencies += "org.jsoup" % "jsoup" % "1.11.2"
libraryDependencies += "joda-time" % "joda-time" % "2.9.9"
libraryDependencies += "com.softwaremill.sttp" %% "core" % "1.1.6"
libraryDependencies += "com.google.code.gson" % "gson" % "2.8.2"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"