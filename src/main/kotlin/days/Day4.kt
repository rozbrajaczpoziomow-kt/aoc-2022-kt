package days

import Solution

class Day4 : Solution {
	override val day: UByte = 4u

	override fun solve1(): String {
		var sum: Int = 0
		for(line in getInputSplit("\n")) {
			val split: List<String> = line.split(",")
			val from: List<String> = split[0].split("-")
			val to: List<String> = split[1].split("-")
			val fromrange: List<Int> = (from[0].toInt()..from[1].toInt()).toList()
			val torange: List<Int> = (to[0].toInt()..to[1].toInt()).toList()
			if(fromrange.all { torange.contains(it) } || torange.all { fromrange.contains(it) })
				sum++
		}
		return sum.toString()
	}

	override fun solve2(): String {
		var sum: Int = 0
		for(line in getInputSplit("\n")) {
			val split: List<String> = line.split(",")
			val from: List<String> = split[0].split("-")
			val to: List<String> = split[1].split("-")
			val fromrange: List<Int> = (from[0].toInt()..from[1].toInt()).toList()
			val torange: List<Int> = (to[0].toInt()..to[1].toInt()).toList()
			if(fromrange.any { torange.contains(it) })
				sum++
		}
		return sum.toString()
	}
}