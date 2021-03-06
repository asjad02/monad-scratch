package fplibrary



trait Monad[ C[_]] {
  def flatMap[A, B](ca: C[A])(acb: A => C[B]): C[B]
  @inline def bind[A, B](ca: C[A])(acb: A => C[B]): C[B] = flatMap(ca)(acb)
  @inline def >>=[A, B](ca: C[A])(acb: A => C[B]): C[B] = flatMap(ca)(acb)
}

// since we are going to use monad as type classes its better to have summoner for them via object
object Monad {
  def apply[C[_]: Monad]: Monad[C] = implicitly[Monad[C]]
}