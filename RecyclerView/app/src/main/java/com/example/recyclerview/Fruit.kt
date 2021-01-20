package com.example.recyclerview

import kotlin.random.Random

data class Fruit(var name:String,
            var location:String,
            var quantity:Int) {
    companion object{

        private val fruitsList = arrayOf("Apple","Mango","Banana","Guava","Grapes","Peach","Melon",
                                    "Watermelon","Orange","Kiwi","Tangerine")
        private val locations = arrayOf("Lucknow","Nagpur","Srinagar","Chennai","Patna")

        fun generateFruitsRandom(n:Int) : ArrayList<Fruit>
        {
            val list = ArrayList<Fruit>()
            for( i in 1..n)
            {
                list.add(Fruit(fruitsList[Random.nextInt(11)],
                                locations[Random.nextInt(5)],
                                Random.nextInt(10)*100))
            }
            return list
        }

    }
}