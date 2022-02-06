import fplibrary._

object PointFreeProgram {

  // lazy val createDescription: Array[String] => Description[Unit] = args =>
  //   Description.brokenCreate(
  //     display(
  //       hyphens(
  //         display(
  //           createMessage(
  //             round(
  //               ensureAmountIsPositive(
  //                 convertStringToInt(
  //                   prompt(
  //                     display(
  //                       question(
  //                         display(
  //                           hyphens(
  //                             args
  //                           )
  //                         )
  //                       )
  //                     )
  //                   )
  //                 )
  //               )
  //             )
  //           )
  //         )
  //       )
  //     )
  //   )

  import IO._

  lazy val createDescription: Array[String] => IO[Unit] =
    ignoreArgs               -->
      hyphens                -->
      displayKliesli          >=>
      questions              -->
      displayKliesli          >=>
      promptKleisli           >=>
      convertStringToInt     -->       
      ensureAmountIsPositive -->                
      round                  -->
      createMessage          -->   
      displayKliesli          >=>
      hyphens                -->
      displayKliesli          

  private lazy val ignoreArgs: Array[String] => Unit = _ => ()

  private lazy val hyphens: Any => String = _ => "--" * 50

  private lazy val questions: Any => String = _ =>
    "How much money would u like to deposit?"

  private lazy val display: Any => Unit = input => {
    println(input)
  }
  private lazy val displayKliesli: Any => IO[Unit] = input =>
    IO.create {
      println(input)
    }

  private lazy val prompt: Any => String = _ => "5"
  private lazy val promptKleisli: Any => IO[String] = _ => IO.create("5")
  // scala.io.StdIn.readLine()

  // side effect : exception throw
  private lazy val convertStringToInt: String => Int = input => input.toInt

  private lazy val ensureAmountIsPositive: Int => Int = amount =>
    if (amount < 1) 1
    else amount

  private lazy val round: Int => Int = amount =>
    if (isDivisibleByHundred(amount)) amount
    else round(amount + 1)

  private lazy val isDivisibleByHundred: Int => Boolean = amount =>
    amount % 100 == 0

  private lazy val createMessage: Int => String = balance =>
    s"Congratulatoin u have USD $balance"

}
