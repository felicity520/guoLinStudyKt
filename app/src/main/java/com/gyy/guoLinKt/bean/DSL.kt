package com.gyy.guoLinKt.bean

import java.lang.StringBuilder

/***
 * 学习DSL
 */
class Dependencies {

    val library = ArrayList<String>()

    fun implementation(lib: String) {
        library.add(lib)
    }
}

fun dependencies(block: Dependencies.() -> Unit): ArrayList<String> {
    val dependencies = Dependencies()
    dependencies.block()
    return dependencies.library
}

class Td {
    var content = ""

    fun html() = "\n\t<td>$content</td>"
}

class Tr {
    private val children = ArrayList<Td>()

    fun td(block: Td.() -> String) {
        val td = Td()
        //这里将 block 函数类型参数的返回值 赋值 给 content
        td.content = td.block()
        children.add(td)
    }

    fun html(): String {
        val builder = StringBuilder()
        builder.append("\n\t<tr>")
        for (chiller in children) {
            builder.append(chiller.html())
        }
        builder.append("\n\t</tr>")
        return builder.toString()
    }
}

class Table() {
    private val children = ArrayList<Tr>()

    fun tr(block: Tr.() -> Unit) {
        val tr = Tr()
        tr.block()
        children.add(tr)
    }

    fun html(): String {
        val builder = StringBuilder()
        builder.append("\n\t<table>")
        for (chiller in children) {
            builder.append(chiller.html())
        }
        builder.append("\n\t</table>")
        return builder.toString()
    }
}

fun table(block: Table.() -> Unit): String {
    val table = Table()
    table.block()
    return table.html()
}