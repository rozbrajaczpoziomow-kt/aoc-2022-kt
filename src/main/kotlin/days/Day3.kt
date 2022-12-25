package days

import Solution

class Day3 : Solution {
	override val day: UByte = 3u

	override fun solve1(): String {
		var sum: Int = 0
		for(line in getInputSplit("\n")) {
			val items: List<Char> = line.toList()
			val com1: List<Char> = items.subList(0, items.size / 2)
			val com2: List<Char> = items.subList(items.size / 2, items.size)
			val shared: Char = com1.filter { com2.contains(it) }[0]
			sum += alphabetBoth.indexOf(shared) + 1
		}
		return sum.toString()
	}

	override fun solve2(): String {
		var sum: Int = 0
		var idx: Int = 0
		val elves: ArrayList<String> = ArrayList()

		for(i in 0..2)
			elves.add(i, "")

		for(line in getInputSplit("\n")) {
			elves[idx] = line
			idx++
			if(idx < elves.size) continue
            val shared: Char = elves[0].filter { elves[1].contains(it) && elves[2].contains(it) }[0]
            sum += alphabetBoth.indexOf(shared) + 1
			idx = 0
		}
		return sum.toString()
	}
}