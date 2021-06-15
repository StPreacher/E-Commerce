package com.mdgn.ecommerce.algorithm
import kotlin.math.sqrt

data class Detail (val avarage : Double, val zeroList : MutableList<Int>)

fun main(){

    val user1List : ArrayList<Int> = arrayListOf(5,4,1,4,0)
    val user2List : ArrayList<Int> = arrayListOf(3,1,2,3,3)
    val user3List : ArrayList<Int> = arrayListOf(4,3,4,3,5)
    val user4List : ArrayList<Int> = arrayListOf(3,3,1,5,4)


    println("User1List avarage : ${myUserAvarage(user1List).avarage}")
    println("User1List avarage : ${myUserAvarage(user1List).zeroList.toList()}")
    println("User2List avarage : ${userAvarage(user2List,myUserAvarage(user1List).zeroList)}")
    println("User3List avarage : ${userAvarage(user3List,myUserAvarage(user1List).zeroList)}")
    println("User4List avarage : ${userAvarage(user4List,myUserAvarage(user1List).zeroList)}")
    println(userUpdateList(user1List, myUserAvarage(user1List).avarage, myUserAvarage(user1List).zeroList))
    println(userUpdateList(user2List, userAvarage(user2List, myUserAvarage(user1List).zeroList), myUserAvarage(user1List).zeroList))
    println(userUpdateList(user3List, userAvarage(user3List, myUserAvarage(user1List).zeroList), myUserAvarage(user1List).zeroList))
    println(userUpdateList(user4List, userAvarage(user4List, myUserAvarage(user1List).zeroList), myUserAvarage(user1List).zeroList))

    val user1newList = userUpdateList(user1List, myUserAvarage(user1List).avarage, myUserAvarage(user1List).zeroList)
    val user2newList = userUpdateList(user2List, userAvarage(user2List, myUserAvarage(user1List).zeroList), myUserAvarage(user1List).zeroList)
    val user3newList = userUpdateList(user3List, userAvarage(user3List, myUserAvarage(user1List).zeroList), myUserAvarage(user1List).zeroList)
    val user4newList = userUpdateList(user4List, userAvarage(user4List, myUserAvarage(user1List).zeroList), myUserAvarage(user1List).zeroList)

    println("User1 - User2 : ${similarityUser(user1newList,user2newList)}")
    println("User1 - User3 : ${similarityUser(user1newList,user3newList)}")
    println("User1 - User4 : ${similarityUser(user1newList,user4newList)}")
    println("User1 - User1 : ${similarityUser(user1newList,user1newList)}")


}

fun similarityUser(user1newList: MutableList<Double>, user2newList: MutableList<Double>) : Double {

    var top: Double = 0.0
    var bottom: Double = 0.0
    var firstSum: Double = 0.0
    var secondSum: Double = 0.0
    for (i in 0 until user1newList.size) {
        top += (user1newList[i] * user2newList[i])
        firstSum += (user1newList[i] * user1newList[i])
        secondSum += (user2newList[i] * user2newList[i])
    }

    bottom = sqrt(firstSum.toDouble()) * sqrt(secondSum.toDouble())

    return top / bottom
}

fun userUpdateList(userOyList: ArrayList<Int>, avarage: Double, zeroList: MutableList<Int>) : MutableList<Double> {

    val newList : MutableList<Double> = arrayListOf()
    for (i in 0 until userOyList.size){
        if (!zeroList.contains(i)){
            newList.add(userOyList[i]-avarage)
        }
    }
    return newList
}

fun myUserAvarage(userOyList : ArrayList<Int>) : Detail {

    var sum : Int = 0
    val includeZeroIndexList : MutableList<Int> = arrayListOf()
    var counter : Double = 0.0
    for (i in 0 until userOyList.size){
        if (userOyList[i] != 0){
            sum += userOyList[i]
            counter++
        }else{
            includeZeroIndexList.add(i)
        }
    }
    return Detail((sum/counter),includeZeroIndexList)
}

fun userAvarage(userOyList: ArrayList<Int>,zeroIndexList : MutableList<Int>) : Double{

    var sum : Int = 0
    var counter : Double = 0.0

    for (i in 0 until userOyList.size){
        if (!zeroIndexList.contains(i)){
            sum += userOyList[i]
            counter++
        }
    }


    return (sum/counter)
}

