package com.ad.ff2

fun main() {

   val coins: (Int) -> String = {
      "$it quarters"
   }

   val cupcake = { quantity: Int ->
      "Give $quantity cupcake!"
   }

   val treatFunction = trickOrTreat(false, cupcake)
   val trickFunction = trickOrTreat(true, coins)
   val trickFunction1 = trickOrTreat(false) { "Give $it apple slices!" }

   trickFunction1()
   treatFunction()
   trickFunction()

   repeat(4) {
      trickFunction1()
   }
}

//lambda expression
val trick = {
   println("No treats!")
}

val treat = {
   println("Have a treat!")
}

fun trickOrTreat(isTrick: Boolean, extraTreat: ((Int) -> String)? = null) =
   if (isTrick) trick else {
      {
         extraTreat?.let { println(it(5)) }
         treat()
      }
   }