package days

import Solution

class Day13 : Solution {
	fun parse(arr: String): ArrayList<List<Int>> {
		println(arr)
		// TODO: JSON Desializartialization to Array/List <Int>
		TODO()
	}

	override val day: UByte = 13u

	override fun solve1(): String {
		val compares: ArrayList<Pair<ArrayList<List<Int>>, ArrayList<List<Int>>>> = ArrayList()
		// i.e.
		getInputSplit("\n\n").forEach {
			val split: List<String> = it.split("\n")
			compares.add(parse(split[0]) to parse(split[1]))
		}
		return ""
	}

	override fun solve2(): String {
		return ""
	}
}