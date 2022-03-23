package rover

import cats.effect.{IOApp, IO, ExitCode}

object Hello extends IOApp with Greeting {
  def run(args: List[String]): IO[ExitCode] =
    IO { println(greeting) }.map(_ => ExitCode.Success)
}

trait Greeting {
  lazy val greeting: String = "hello"
}
