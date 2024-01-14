fun main() {
    var sum =0;
    var number:Int;
    
    while(true){
        print("print a number :");

        number = readLine()!!.toInt()

        if(number == 0){
            break
        }

        sum += number;
    }

    print("\nadad "+sum+"\n");
}