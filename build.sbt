name := "sber_tasks"

version := "0.1"

scalaVersion := "2.13.8"

val DoobieVersion = "1.0.0-RC1"
val NewTypeVersion = "0.4.4"

libraryDependencies ++= Seq(
  "org.tpolecat" %% "doobie-core"     % DoobieVersion,
  "org.tpolecat" %% "doobie-postgres" % DoobieVersion,
  "org.tpolecat" %% "doobie-hikari"   % DoobieVersion,
  "io.estatico"  %% "newtype"         % NewTypeVersion
)