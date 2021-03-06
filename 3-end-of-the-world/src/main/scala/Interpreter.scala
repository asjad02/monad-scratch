import fplibrary._

object Interpreter {
    def main(args: Array[String]): Unit = {
    print(Console.RED)

    val description: IO[Unit] =
        PointFreeProgram.createDescription(args)

    def interpret[A](description: IO[A]): A =
        description.unsafeRun()

    print(Console.GREEN)
    interpret(description)
    }
}