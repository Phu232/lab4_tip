package com.example.lab1

fun main() {
    // cách 1
    var a:Int=10
    var b:Int=15
    var max1:Int
    if(a>b)
        max1=a
    else
        max1=b
    println("So lon nhat ="+max1)

    // cách 2

    var max = if (a > b) {
        println("Choose a")
        a
    } else {
        println("Choose b")
        b
    }
    println("max =" + max)

    // ví dụ 2 if lồng nhau
    val guests = 30
    if (guests == 0) {
        println("No guests")
    } else if (guests < 20) {
        println("Small group of people")
    } else {
        println("Large group of people!")
    }
// ví dụ 3
    var tb:Double=8.0;
    if(tb>=5)
    {
        println("dau")
    }
    // vi dụ 4
    var dtb:Double=0.0;
    println("Moi ban nhap diem trung binh:")
    var s:String?= readLine()
    if(s!=null)
    {
        dtb=s.toDouble()
        if(dtb>=0 && dtb<=10)
        {
            if (dtb >= 5)
            {
                println("dau")
            }
            else
            {
                println("Rot")
            }
        }
        else
        {
            println("Ban phai nhap diem [0...10]")
        }
    }
    else
    {
        println("Ban nhap sai")
    }
}