import sbt._

object Dependencies {

  object Test {
    private val scalatestVersion = "3.2.10"

    val deps = Seq(
      "org.scalatest" %% "scalatest" % scalatestVersion  % "test"
    )
  }

  object Cats {
    private val catsEffectVersion = "3.3.4"
    private val catsVersion = "2.7.0"

    val deps = Seq(
        "org.typelevel" %% "cats-effect" % catsEffectVersion
      , "org.typelevel" %% "cats-core" % catsVersion
    )
  }
}
