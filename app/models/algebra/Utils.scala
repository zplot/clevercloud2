package models.algebra


import scala._
import scala.annotation.tailrec

object Utils {


  case class IntMap(map: Map[Int, Int])

  def combinations[T](size: Int, objects: List[T]) : List[List[T]] = {
    if (size == 0)
      List(List())
    else {
      for {
        x  <- objects
        xs <- combinations(size - 1, objects)
      } yield x :: xs
    }
  }

    // TODO Esto no funciona
//  def powerGeneric[A <: Group#T2]( n: A, p: Int): A = p match {
//    case 0 => n * n.inverse
//    case 1 => n
//    case p if p % 2 == 1 => n * powerGeneric(n * n, (p - 1) / 2)
//    case p if p % 2 == 0 => powerGeneric(n * n, p / 2)
//  }


  // Random integer between 0 and p - 1
  def randomP(p: Int) = util.Random.nextInt(p)

  /** Power of n to p
    *
    * Uses repeated squaring algorithm:
    * http://www.algorithmist.com/index.php/Repeated_Squaring
    * 2030534587 = power(3, 27)
    */
  def power( n: Int, p: Int): Int = p match {
    case 0 => 1
    case 1 => n
    case p if p % 2 == 1 => n * power(n * n, (p - 1) / 2)
    case p if p % 2 == 0 => power(n * n, p / 2)
  }

  /** Converts an integral to base 2 in the form of a List[Int]
    * The most significant number to the left.
    *
    * Ex.: toBinary(11) = List(1, 0, 1, 1)
    */
  def toBinary(n: Int): List[Int] = {
    def loop(n: Int, list: List[Int]): List[Int] = n match {
      case 0 => 0 :: list
      case 1 => 1 :: list
      case n => loop(n/2, n % 2 :: list)
    }
    loop(n, List[Int]())
  }


  // All factors of a number (not only primes)
  def factors(num: Int) = {
    (1 to num).filter { divisor =>
      num % divisor == 0
    }
  }



  // Power of integers TODO Usar Squaring
  def expInt(n:Int, m: Int): Int = List.fill(m)(n).product

  // all prime factors with repetition
  def factorize(x: Int): List[Int] = {
    def loop(x: Int, a: Int): List[Int] = if (a * a > x) List(x) else x % a match {
      case 0 => a :: loop(x / a, a)
      case _ => loop(x, a + 1)
    }
    loop(x, 2)
  }


  def primeFactors(number: Int): List[(Int, Int)] = {

    val fact = factorize(number)
    val uniq = fact.toSet.toList
    val emptyList: List[(Int, Int)] = List[(Int, Int)]()
    val unflattened = for (i <- uniq) yield (i, fact.count(_ == i)) :: emptyList
    unflattened.flatten
  }

  /** Return the divisors of n. */
  def divisors(n: Int): List[Int] =
    for (i <- List.range(1, n+1) if n % i == 0) yield i

  /** Is 'n' a prime number? */
  def isPrime(n: Int) = divisors(n).length == 2

  // Jacobi imperativo funciona bien
  def jacobiImperative(up: Int,down: Int): Int = {
    var a = up
    var n = down
    var t = 1
    var tmp: Int = 111
    while (a!=0) {
      while (a % 2 == 0) {
        a = a / 2
        if ((n % 8 == 3) || (n % 8 == 5)) {t = -t}
      }
      tmp = n
      n = a
      a = tmp
      if ((a % 4 == 3) && (n % 4 == 3)) {t = -t}
      a = a % n
    }
    if (n == 1) t else 0
  }


  // Jacobi functional
  def jacobiSymbol(a: Int, n: Int): Int = {
    require(a < n && n % 2 == 1 , "a > n or n pair")

    val t = 1

    def recursion2(a: Int, n: Int, t: Int): (Int, Int, Int) = {
      @tailrec
      def go2(a: Int, n: Int, t: Int): (Int, Int, Int) = {
        if (a % 2 != 0) (a, n, t) else {
          if ((n % 8 == 3) || (n % 8 == 5)) go2(a/2, n, -t) else go2(a/2, n, t)
        }
      }
      go2(a, n, t)
    }

    def recursion1(a: Int, n: Int, t: Int): (Int, Int, Int) = {
      @tailrec
      def go1(a: Int, n: Int, t: Int): (Int, Int, Int) = {
        if (a == 0) (a, n, t) else {
          //recursion2(a, n, t)

          if ((recursion2(a, n, t)._2 % 4 == 3) && (recursion2(a, n, t)._1 % 4 == 3))
            go1(recursion2(a, n, t)._2 % recursion2(a, n, t)._1, recursion2(a, n, t)._1, -t)
            else go1(recursion2(a, n, t)._2 % recursion2(a, n, t)._1, recursion2(a, n, t)._1, recursion2(a, n, t)._3)
        }
      }
      go1(a, n, t)
    }

    val (aFinal, nFinal, tFinal) = recursion1(a, n, t)

    if (nFinal == 1) tFinal else 0
  }

  // Henri Cohen
  //A Course in Computational Algebraic Number Theory
  // Pag. 28

  def kroneckerSymbol2(a: Int, b: Int): Int = {
    def bIs0(a: Int): Int = if (a == 1 || a == -1) 1 else 0
    def bIs2(a: Int): Int = if (a % 2 == 1) 0 else math.pow(-1, ((a*a - 1) / 8)).toInt
    def generalCase(a: Int, b: Int) = {
      val bAllFactors: List[Int] = factorize(b)
      val tmp1: List[Int] = bAllFactors.map(p  => jacobiSymbol(a, p))
      val result = tmp1.product
      result
    }
    (a,b) match {
      case (_, 0) => bIs0(a)
      case (_, 2) => bIs2(a)
      case (_, _) => generalCase(a, b)
    }
  }



















}










