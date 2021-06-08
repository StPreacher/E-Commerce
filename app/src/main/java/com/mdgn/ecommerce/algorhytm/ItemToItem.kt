package com.mdgn.ecommerce.algorhytm

import kotlin.math.sqrt

fun main(){
    val item1List : ArrayList<Int> = arrayListOf(2,5,3,0)
    val item2List : ArrayList<Int> = arrayListOf(0,2,3,2)
    val item3List : ArrayList<Int> = arrayListOf(3,0,1,2)


    println("Similarity of I1 and I2 : ${simililarity(item1List,item2List)}")
    println("Similarity of I1 and I3 : ${simililarity(item1List,item3List)}")
    println("Similarity of I2 and I3 : ${simililarity(item2List,item3List)}")
}

fun simililarity(firstItemList: ArrayList<Int>, secondItemList: ArrayList<Int>) : Double {

    val firstList : MutableList<Int> = arrayListOf()
    val secondList : MutableList<Int> = arrayListOf()

    for (i in 0 until firstItemList.size){
        if (firstItemList[i] !=0 && secondItemList[i] != 0){
            firstList.add(firstItemList[i])
            secondList.add(secondItemList[i])
        }
    }

    var top : Int =0
    var bottom : Double = 0.0
    var firstSum : Int = 0
    var secondSum : Int = 0
    for (i in 0 until firstList.size){
        top += (firstList[i] * secondList[i])
        firstSum += (firstList[i] * firstList[i])
        secondSum += (secondList[i] * secondList[i])
    }

    bottom = sqrt(firstSum.toDouble()) * sqrt(secondSum.toDouble())

    return (top / bottom)
}
