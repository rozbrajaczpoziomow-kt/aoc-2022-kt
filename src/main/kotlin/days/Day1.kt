package days

import Solution

class Day1 : Solution {
	override val day: Int = 1;

	override fun solve1(): String {
		var max = 0
		for(elf in getInputSplit("\n\n")) {
			val food = elf.split("\n").sumOf { it.toInt() }
			if(food > max) max = food
		}
		return max.toString()
	}

	override fun solve2(): String {
		val foods: ArrayList<Int> = ArrayList()
		for(elf in getInputSplit("\n\n"))
			foods.add(elf.split("\n").sumOf { it.toInt() })
		foods.sortDescending()
		return (foods[0] + foods[1] + foods[2]).toString()
	}
}