import fplibrary._

object PointProgram {

  def createDescription(args: Array[String]): IO[Unit] = IO.create {
    display(hyphens)

    display(question)

    val input: String = prompt()
    val integerAmount: Int = convertStringToInt(input)
    val positiveAmount: Int = ensureAmountIsPositive(integerAmount)
    val balance: Int = round(positiveAmount)
    val message: String = createMessage(balance)

    display(message)
    display(hyphens)

  }

  private val hyphens: String =
    "\u2500" * 50

  private val question: String =
    "How much money would u like to deposit?"

  private def display(input: Any): Unit = {
    println(input)
  }

  private def prompt(): String = "5"
  // scala.io.StdIn.readLine()


  // side effect : exception throw
  private def convertStringToInt(input: String): Int =
    input.toInt

  private def ensureAmountIsPositive(amount: Int): Int =
    if (amount < 1) 1
    else amount

  @scala.annotation.tailrec
  private def round(amount: Int): Int =
    if (isDivisibleByHundred(amount)) amount
    else round(amount + 1)

  private def isDivisibleByHundred(amount: Int): Boolean =
    amount % 100 == 0

  private def createMessage(balance: Int): String =
    s"Congratulatoin u have USD $balance"

}