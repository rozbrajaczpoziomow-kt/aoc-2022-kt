package days

import Solution

class Day6 : Solution {
	override val day: UByte = 6u

	override fun solve1(): String {
		val packet: String = getInput()
		var group: String = ""
		var idx: Int = 0
		for(i in packet) {
			group += i
			idx += 1
			if(group.length < 4) continue
			if(group.length > 4) group = group.drop(1)
			if(group.map { char: Char -> group.count { char == it }.toString() }.count { it == "1" } == 4)
				return idx.toString()
		}
		return "No solution found"
	}

	override fun solve2(): String {
		val packet: String = getInput()
		var group: String = ""
		var idx: Int = 0
		for(i in packet) {
			group += i
			idx += 1
			if(group.length < 14) continue
			if(group.length > 14) group = group.drop(1)
			if(group.map { char: Char -> group.count { char == it }.toString() }.count { it == "1" } == 14)
				return idx.toString()
		}
		return "No solution found"
	}
}