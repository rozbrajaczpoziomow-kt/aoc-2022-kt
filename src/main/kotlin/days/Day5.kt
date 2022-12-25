package days

import Solution

class Day5 : Solution {
	override val day: Int = 5

	override fun solve1(): String {
		var gsplit: List<String> = getInputSplit("\n\n") // 0=initial; 1=moves
		val initial: List<String> = gsplit[0].split("\n").dropLast(1)
		val moves: List<String> = gsplit[1].split("\n")
		val crates: HashMap<Int, String> = HashMap()
		for(i in 1..initial.last().count { it == '[' })
			crates[i] = ""

		for(init in initial.reversed())
			"  $init".filterIndexed { idx: Int, c: Char -> (idx + 1) % 4 == 0 }.mapIndexed { idx: Int, c: Char -> crates[idx + 1] = crates[idx + 1] + (if(c != ' ') c else "") }

		for(mv in moves) {
			val split: List<Int> = mv.split(" ").filter { it.isNumber() }.map { it.toInt() }
			val count: Int = split[0]
			val from: Int = split[1]
			val to: Int = split[2]
			repeat(count) {
				val move: Char = crates[from]!!.last()
				crates[from] = crates[from]!!.dropLast(1)
				crates[to] = crates[to] + move
			}
		}
		return crates.map { it.value.last() }.joinToString("")
	}

	override fun solve2(): String {
		var gsplit: List<String> = getInputSplit("\n\n") // 0=initial; 1=moves
		val initial: List<String> = gsplit[0].split("\n").dropLast(1)
		val moves: List<String> = gsplit[1].split("\n")
		val crates: HashMap<Int, String> = HashMap()
		for(i in 1..initial.last().count { it == '[' })
			crates[i] = ""

		for(init in initial.reversed())
			"  $init".filterIndexed { idx: Int, c: Char -> (idx + 1) % 4 == 0 }.mapIndexed { idx: Int, c: Char -> crates[idx + 1] = crates[idx + 1] + (if(c != ' ') c else "") }

		for(mv in moves) {
			val split: List<Int> = mv.split(" ").filter { it.isNumber() }.map { it.toInt() }
			val count: Int = split[0]
			val from: Int = split[1]
			val to: Int = split[2]
//			println("Pre-move $count crates from $from (${crates[from]}) to $to (${crates[to]})")
			var totalmove: String = ""
			repeat(count) {
				val move: Char = crates[from]!!.last()
				crates[from] = crates[from]!!.dropLast(1)
				totalmove += move
			}
			crates[to] = crates[to] + totalmove.reversed()
//			println("Post-move $count crates from $from (${crates[from]}) to $to (${crates[to]})")
//			println()
		}

//		println(crates.map { (k: Int, v: String) -> "$k = $v [len = ${v.length}]\n" }.joinToString("").dropLast(1))
		return crates.map { it.value.last() }.joinToString("")
	}
}