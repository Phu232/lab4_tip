package com.example.lab1

import java.util.*
fun main(args: Array<String>){
    var M:IntArray= IntArray(10)

    var rd=Random()
    for(i in M.indices)
        M[i]=rd.nextInt(100)
    println("Mang sau khi nhap - duyet theo giá trị:")
    for (i in M)
        print("$i\t")
    println()
    println("Mang sau khi nhap - duyet theo vị trí:")
    for (i in M.indices)
        print("${M[i]}\t")
    println()
    //số lớn nhất
    println("MAX=${M.maxOrNull()}}")
    //số nhỏ nhất
    println("MIN=${M.minOrNull()}")
    //tổng mảng
    println("SUM=${M.sum()}")
    //trung bình mảng
    println("AVERAGE=${M.average()}")
    //đếm số chẵn
    println("So chan=${M.count { x->x%2==0 }}")
    //đếm số lẻ
    println("So le=${M.count { x->x%2==1 }}")
    //sắp xếp tăng dần
    M.sort()
    println("Tang dan:")
    for (i in M)
        print("$i\t")
    println()
    //sắp xếp giảm dần
    M.sortDescending()
    println("Giam dan:")
    for (i in M)
        print("$i\t")
    println()
    //lọc các số chẵn trong mảng
    var dsChan= M.filter { x->x%2==0 }
    println("Cac so chan:")
    for (i in dsChan)
        print("$i\t")
    println()
    //lọc các số lẻ trong mảng
    var dsLe= M.filter { x->x%2==1 }
    println("Cac so Le:")
    for (i in dsLe)
        print("$i\t")
    println()
    var k=50
    //lọc các số >50 trong mảng
    var dsTim=M.filter { x->x>k }
    println("Cac so > $k:")
    for (i in dsTim)
        print("$i\t")
    println()
}