package rover

import cats.effect.{IOApp, IO, ExitCode}

object App extends IOApp with Greeting {
  def run(args: List[String]): IO[ExitCode] =
    IO { println(greeting) }.map(_ => ExitCode.Success)
}

trait Greeting {
  lazy val greeting: String = "Hi, the print functionality hasn't been implement yet, please run `sbt test` to test the navigation and path finding functionality"
}
