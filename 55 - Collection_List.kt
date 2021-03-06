fun main() {
    val listInt = listOf<Int>(1,2,3,4,5,6)
    val listString = listOf("A","B","C","D","E","F","G","C")
    val list_notNull = listOfNotNull("a",4,5,null,null,"aa")
    val listEmpty = emptyList<Int>()
    val list_arrayList = arrayListOf<Int>()
    val list_arr_ = arrayListOf("a","b")
    val list_mutable = mutableListOf<Int>()
    val list_mutable_filled = mutableListOf("Aa","Bb")

    println(listInt)             // [1, 2, 3, 4, 5, 6]
    println(listString)          // [A, B, C, D, E, F, G, C]
    println(list_notNull)        // [a, 4, 5, aa]
    println(listEmpty)           // []
    println(list_arrayList)      // []
    println(list_arr_)           // [a, b]
    println(list_mutable)        // []
    println(list_mutable_filled) // [Aa, Bb]
}
________________________________________________________________________________________________________

fun main() {

    val state = arrayOf("MH","MH","GJ","RJ","DL","DL","HP","J&K","DD","SK","AP","TN","TS","KL","KK","MP")

    val al = object : AbstractList<String>(){

        override val size: Int = state.size

        override fun get(index: Int): String{
            return state[index]
        }
    }
    println(al)
}
________________________________________________________________________________________________________

fun main() {

    val state = arrayOf("MH","MH","GJ","RJ","DL","DL","HP",
        "J&K","DD","SK","AP","TN","TS","KL","KK","MP")

    val al = object : AbstractList<String>(){

        override val size: Int = state.size

        override fun get(index: Int): String{
            return state[index]
        }
    }
    al.forEach { println("al : $it") }                  // print every string as it is
    al.stream().forEach { println("al.stream : $it") }  // print every string as it is
    al.parallelStream().forEach { println("al.parallelStream : $it") }  //prints randomly
    println("al.contains : ${al.contains("MH")}")       // true
    println("al.isEmpty : ${al.isEmpty()}")             // false
    println("al.last()  : ${al.last()}")                // MP
    println("al.sorted() : ${al.sorted()}")             // print sorted list
    println("al.any()  :  ${al.any()}")                 // true
}
________________________________________________________________________________________________________

fun main() {

    val mutable = mutableListOf("MH","MH","GJ","RJ","DL","DL","HP","J&K", "DD","SK","AP","TN","TS","KL","KK","MP")

    println("1 : $mutable")     // 1 : [MH, MH, GJ, RJ, DL, DL, HP, J&K, DD, SK, AP, TN, TS, KL, KK, MP]

    mutable.removeIf{ it == "MH" }
    println("2 : $mutable")     // 2 : [GJ, RJ, DL, DL, HP, J&K, DD, SK, AP, TN, TS, KL, KK, MP]

    mutable.removeIf{ it.contains("D")}
    println("3 : $mutable")         // 3 : [GJ, RJ, HP, J&K, SK, AP, TN, TS, KL, KK, MP]

    mutable.add(0,"AAA")

    println("4 : $mutable")         // 4 : [AAA, GJ, RJ, HP, J&K, SK, AP, TN, TS, KL, KK, MP]

    println("5 : mutable.asReversed()  :  ${mutable.asReversed()}")     //5 : mutable.asReversed()  :  [MP, KK, KL, TS, TN, AP, SK, J&K, HP, RJ, GJ, AAA]

    println("6 : mutable.fill(\"LUCK\") :  ${mutable.fill("LUCK")}") // 6 : mutable.fill("LUCK") :  kotlin.Unit

    println("7 : ${mutable}")   //7 : [LUCK, LUCK, LUCK, LUCK, LUCK, LUCK, LUCK, LUCK, LUCK, LUCK, LUCK, LUCK]

    println("8 : mutable.removeAll { it.contains(\"U\") } :  ${mutable.removeAll { it.contains("U") }}")  //8 : mutable.removeAll { it.contains("U") } :  true

    println("9 : ${mutable}")  // 9 : []
    
}
________________________________________________________________________________________________________

data class Student(var id : Int, var name : String, var email : String, val mobile : Long)

fun main() {
    val list = mutableListOf<Student>()
    list.add(Student(1,"Alex","alex@gmail.com",9988776655))
    list.add(Student(2,"Berry","ber@yahoo.com",8877665544))
    list.add(Student(3,"Charline","charlie@hotmail.com",7766554433))
    list.add(Student(4,"Dom","dom@yandex.com",6677889900))

    println(list)    /* will print following
    [
      Student(id=1, name=Alex, email=alex@gmail.com, mobile=9988776655), 
      Student(id=2, name=Berry, email=ber@yahoo.com, mobile=8877665544), 
      Student(id=3, name=Charline, email=charlie@hotmail.com, mobile=7766554433), 
      Student(id=4, name=Dom, email=dom@yandex.com, mobile=6677889900)
    ] */
  
    //-----------------------------------------------------------------------------------------------------
    val email = list.associateBy { it.email }  // email becomes keys of list
    println(email)   /* will print following 
    {
        alex@gmail.com=Student(id=1, name=Alex, email=alex@gmail.com, mobile=9988776655), 
        ber@yahoo.com=Student(id=2, name=Berry, email=ber@yahoo.com, mobile=8877665544), 
        charlie@hotmail.com=Student(id=3, name=Charline, email=charlie@hotmail.com, mobile=7766554433),
        dom@yandex.com=Student(id=4, name=Dom, email=dom@yandex.com, mobile=6677889900)
    }*/
    //-----------------------------------------------------------------------------------------------------
    
    val nameMobile = list.associateBy(Student::name,Student::mobile)
    println(nameMobile) /* will print following
    {
        Alex=9988776655, 
        Berry=8877665544, 
        Charline=7766554433, 
        Dom=6677889900
    }*/
    //-----------------------------------------------------------------------------------------------------
    
    val nameMobile = list.groupBy(Student::name,Student::mobile)
    println(nameMobile)
    /*
    {
        Alex=[9988776655], 
        Berry=[8877665544], 
        Charline=[7766554433], 
        Dom=[6677889900]
    }
    */

}



________________________________________________________________________________________________________
fun main() {
    val list = listOf(1,2,3,4,5,6,7,8,9,10)

    val (even, odd) = list.partition { it % 2 == 0 }

    println("$even <==> $odd") //   [2, 4, 6, 8, 10] <==> [1, 3, 5, 7, 9]

    val first5 = list.partition { it in 0..4 }
    val last5 = list.partition { it in 5..10 }

    println("$first5")  //  ([1, 2, 3, 4], [5, 6, 7, 8, 9, 10])
    println("$last5")   //  ([5, 6, 7, 8, 9, 10], [1, 2, 3, 4])

}
________________________________________________________________________________________________________

- In listOf() >
    val list = listOf(...)
      1. list.any { condition } => return true if any element in list which satisfies our conditions    //work like ||
      2. list.all { condition } => return true if all element in list which satisfies our conditions    //work like &&
      3. list.none{ condition } => return true if none of element in list which satisfies our condition //work like !
      4. list.find{ condition } => return first element in list which satisfies our conditions || if can't find return null
      5. list.findLast{ condition } => return last finded element from list which satisfies our conditions 
      6. list.first() => return first element in list
      7. list.last() => return last element in list
      8. list.first{ condition } => return first element from list which satisfies our condition
      9. list.last{ condition } => return last element from list which satisfies our condition
     10. list.firstOrNull() => return element if list array is not empty else return null
     11. list.lastOrNull() => return last element of array if list array is not empty else return null
     13. list.firstOrNull{ condition } => if(listIsNotEmpty) return first element which satisfies our condition else return null
     14. list.lastOrNull { condition } => if(listIsNotEmpty) return last founded last element from list which satisfies our condition else return null
     15. list.count() => return size of array
     16. list.count{ condition } => return size of array which satisfies our condition
     17. list.numbers.minOrNull() => return min number from list
     18. list.numbers.maxOrNull() => return max number from list
     20. list.sorted() => sort array in asc order
     21. list.sortedBy { -it }  => sort array in desc order
     22. list.sortedDescending() => sort array in desc order
     23. list.sortedByDescending { it % 2 == 0 }  => sort first given condition then remain takes as it is
     24. list1.zip(list2) => makes pair of two list , it makes pair until both array index match 
     25. list.getOrElse(index) { it/defaultNum } => get element at given index or else return our own define default element
