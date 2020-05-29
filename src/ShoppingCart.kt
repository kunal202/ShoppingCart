import java.util.*
import kotlin.math.abs
import kotlin.system.exitProcess

//This program let users add items to their cart and
// calculate the total cart value using hashcode of the string value (not the best algo to calculate value :P)
lateinit var list: MutableList<String>
fun main(args: Array<String>){

    println(message = "How many items do you want to add/remove to your cart ??")
    var input = Scanner(System.`in`).nextInt();
    if(input<0){
        println("Please retry again with a valid number")
        exitProcess(0)
    }

    println("Ok Now, tell us you wish to add or remove items")
    var addorremove = Scanner(System.`in`).next()
    var add: Boolean = false
    var remove: Boolean= false
    when (addorremove is String){
        addorremove[0]=='a' ->{
            add = true
            println("Ok, Now tell us the items you wish to add")
        }
        addorremove[0]=='r' ->{
            remove = true
            println("Ok, Now tell us the items you wish to remove")
        }
        else ->{
            println("please tell us to either add or remove items")
            exitProcess(3)
        }
    }

    var inputlist: MutableList<String> = mutableListOf<String>()
    while (input>0){
        inputlist.add(Scanner(System.`in`).next())
        input--
    }

    var user: Shoppinglist = Shoppinglist()
    var finalprice: Int = 0

    list = mutableListOf<String>()
    if(add){
        for (i in 0 until inputlist.size ){
            var price:Int = inputlist[i].hashCode()
            user.addtocart(inputlist[i] , list) { abs(price)}
            finalprice += price
        }
    }else if(remove){
        for (i in 0 until inputlist.size ){
            var price:Int = inputlist[i].hashCode()
            user.removefromcart(inputlist[i]) {price.unaryMinus()}
            finalprice-=price
        }
    }

    if(finalprice<0){
        println("Your Cart is Empty Now And you have a refund of ${finalprice.unaryMinus().div(1000)}")
    }else{
        println("Here is your final cart $list")
        println("And Your total bill is ${finalprice.div(1000)}")
    }
    println("----------------------------------------")
    println("Thank you for choosing our service")
}

class Shoppinglist() {
    fun addtocart(item : String, list: MutableList<String>, total_bill:(Int) -> Int ) {
        total_bill(item.hashCode())
        list.add(item)
    }
    fun removefromcart(item : String, total_bill:(Int) -> Int) {
        total_bill(item.hashCode())
    }
}