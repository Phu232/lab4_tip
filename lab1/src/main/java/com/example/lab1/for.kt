package com.example.lab1

fun main()
{
    var sum:Int=0
    val n:Int=5
    for (i in 1 until n)
    {
        sum += i
    }
    println("Tong=$sum")

    // hien thi danh sach
    var dsSanPham= arrayOf("kotlin","java","c#","python","R")
    for (item in dsSanPham)
        println(item)

    for (i in dsSanPham.indices)
        println("San pham thu $i co ten "+dsSanPham[i])
    println("-------------")
    for ((index,value) in dsSanPham.withIndex())
    {
        println("San pham thu $index co ten $value")
    }
}