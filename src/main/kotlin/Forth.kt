class Forth {
    private var stack :MutableList <Int> = mutableListOf()
    private var hashMap :MutableMap <String,String> = mutableMapOf()

    fun evaluate(vararg line: String): List<Int> {
        line.forEach {
            calculateUserFunc(it)
        }
        line.forEach {
            val newString = calculateStringReplacement(it)
            calculateString(newString)
        }
        return this.stack
    }

    private fun calculateUserFunc(string :String){
            if(string.startsWith(":") && string.endsWith(";")){
                //getting the user func  string without : and ;
                var subString :String = string.substring(2,string.length-2)
                var split = subString.split(" ")
                val func = split[0]
                if (func.toIntOrNull() != null) {
                    throw Exception("illegal operation")
                }
                //getting the func value without the name
                subString = subString.replaceFirst(split[0].plus(" ") , "",true)
                split = subString.split(" ")
                //checking if func value is in hashMap or not
                split.forEach{
                    val substringValue = hashMap[it]
                    if(substringValue != null){
                        subString = subString.replace(it , substringValue,true)
                    }
                }
                hashMap[func] = subString
            }
    }

    private fun calculateStringReplacement(string :String): String {
        var newString = string
        if(!newString.startsWith(":")) {
            val split = newString.split(" ")
            split.forEach {
                if (it.toIntOrNull() == null) {
                    val value = this.hashMap[it]
                    if (value != null) {
                            newString = newString.replace(it, value, true)
                    }
                }
            }
        }
        return newString
    }

    private fun calculateString(string :String) {
        if (!string.startsWith(":")) {
            val split = string.split(" ")
            split.forEach {
                val lastIndex = this.stack.lastIndex
                if (it == "+" || it == "*" || it == "-" || it == "/") {
                    when (lastIndex) {
                        -1 -> throw Exception("empty stack")
                        0 -> throw Exception("only one value on the stack")
                    }
                    val lastElement: Int = this.stack[lastIndex]
                    val lastMinusOneElement: Int = this.stack[lastIndex - 1]
                    if (it == "/" && (lastElement == 0 || lastMinusOneElement == 0)) {
                        throw Exception("divide by zero")
                    }
                    this.stack.removeAt(lastIndex)
                    this.stack.removeAt(lastIndex - 1)
                    var result = 0
                    when (it) {
                        "+" -> result = lastMinusOneElement + lastElement
                        "-" -> result = lastMinusOneElement - lastElement
                        "*" -> result = lastMinusOneElement * lastElement
                        "/" -> result = lastMinusOneElement / lastElement
                    }
                    this.stack.add(result)
                } else if (it.equals("dup", true)) {
                    if (lastIndex == -1) {
                        throw Exception("empty stack")
                    } else {
                        val lastElement: Int = this.stack[lastIndex]
                        this.stack.add(lastElement)
                    }
                } else if (it.equals("drop", true)) {
                    if (lastIndex == -1) {
                        throw Exception("empty stack")
                    } else {
                        this.stack.removeAt(lastIndex)
                    }
                } else if (it.equals("swap", true)) {
                    when (lastIndex) {
                        -1 -> throw Exception("empty stack")
                        0 -> throw Exception("only one value on the stack")
                    }
                    val lastElement: Int = this.stack[lastIndex]
                    val lastMinusOneElement: Int = this.stack[lastIndex - 1]

                    this.stack[lastIndex] = lastMinusOneElement
                    this.stack[lastIndex - 1] = lastElement
                } else if (it.equals("over", true)) {
                    when (lastIndex) {
                        -1 -> throw Exception("empty stack")
                        0 -> throw Exception("only one value on the stack")
                    }
                    if (lastIndex == 1) {
                        val element = this.stack[0]
                        this.stack.add(element)
                    } else {
                        val element: Int = this.stack[1]
                        this.stack.add(element)
                    }
                } else {
                    if(it.toIntOrNull() == null){
                        throw Exception("undefined operation")
                    }
                    this.stack.add(it.toInt())
                }
            }
        }
    }
}
