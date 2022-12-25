package days

import Solution
import kotlin.math.max

class Day8 : Solution {
	override val day: UByte = 8u

	override fun solve1(): String {
		val map: List<List<Int>> = getInputSplit("\n").map { row -> row.toList().map { col -> col.toString().toInt() } }
//		println(map)
		var sum: Int = (map.size + map[0].size) * 2 - 4
		for(rowix in 1..map.size - 2) { // Skip top & bottom
			val row: List<Int> = map[rowix]
			for(colix in 1..row.size - 2) { // Skip left & right
				val col: Int = row[colix]
				// tree is visible if every tree along any axis <>^v is shorter
				var visible: Boolean = false
				visible = visible || row.filterIndexed { idx, _ -> idx < colix }.max() < col // <
				visible = visible || row.filterIndexed { idx, _ -> idx > colix }.max() < col // >
				visible = visible || map.filterIndexed { idx, _ -> idx < rowix }.map { it[colix] }.max() < col // ^
				visible = visible || map.filterIndexed { idx, _ -> idx > rowix }.map { it[colix] }.max() < col // v
				sum += if(visible) 1 else 0
//				if(visible) println("Visible @ row $rowix col $colix")
			}
		}
		return sum.toString()
	}

	override fun solve2(): String {
		val map: List<List<Int>> = getInputSplit("\n").map { row -> row.toList().map { col -> col.toString().toInt() } }
//		println(map)
		var maxScenic: Int = 0
		for(rowix in 1..map.size - 2) { // Skip top & bottom
			val row: List<Int> = map[rowix]
			for(colix in 1..row.size - 2) { // Skip left & right
				val col: Int = row[colix]
				// tree is visible if every tree along any axis <>^v is shorter
				var scenicLeft: Int = 0
				var scenicRight: Int = 0
				var scenicUp: Int = 0
				var scenicDown: Int = 0
				for(tree in row.filterIndexed { idx, _ -> idx < colix }.reversed()) { // Every tree to the left
					scenicLeft++
					if(col <= tree) break
				}
				for(tree in row.filterIndexed { idx, _ -> idx > colix }) { // Every tree to the right
					scenicRight++
					if(col <= tree) break
				}
				for(tree in map.filterIndexed { idx, _ -> idx < rowix }.map { it[colix] }.reversed()) { // Every tree to the top
					scenicUp++
					if(col <= tree) break
				}
				for(tree in map.filterIndexed { idx, _ -> idx > rowix }.map { it[colix] }) { // Every tree to the bottom
					scenicDown++
					if(col <= tree) break
				}
				val scenic: Int = scenicLeft * scenicRight * scenicUp * scenicDown
				maxScenic = max(maxScenic, scenic)
//				if(rowix == 3 && colix == 2)
//					println("Val: $col | Left: $scenicLeft, Right: $scenicRight, Up: $scenicUp, Down: $scenicDown | Scenic: $scenic, maxScenic: $maxScenic")
			}
		}
		return maxScenic.toString()
	}
}