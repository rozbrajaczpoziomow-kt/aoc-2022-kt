package days

import Solution
import kotlin.math.max
import kotlin.math.min

class Day14 : Solution {
	override val day: Int = 14

	override fun solve1(): String {
		val inp: List<List<Pair<Int, Int>>> = getInputSplit("\n").map { line -> line.split(" -> ").map { sector -> sector.split(",").map { it.toInt() } }.map { xy -> xy[0] to xy[1] } }
		val map: HashMap<Pair<Int, Int>, Boolean> = HashMap()
		for(sector: List<Pair<Int, Int>> in inp) {
			var pX: Int = -1
			var pY: Int = -1
			for(pair: Pair<Int, Int> in sector) {
				if(pX == -1) pX = pair.first
				if(pY == -1) pY = pair.second
				(min(pair.first, pX)..max(pair.first, pX)).forEach { map[it to pair.second] = true }
				(min(pair.second, pY)..max(pair.second, pY)).forEach { map[pair.first to it] = true }
				pX = pair.first
				pY = pair.second
			}
		}
		val yMax: Int = map.keys.maxOf { it.second }
		println("X from ${map.keys.minOf { it.first }} to ${map.keys.maxOf { it.first }}; Y from ${map.keys.minOf { it.second }} to $yMax")
		var fell: Int = 0
		while(true) {
			var x: Int = 500
			var y: Int = 0
			while(true) {
				y++
				if(map[x to y] == true) {
					x--
					if(map[x to y] == true) {
						x += 2
						if(map[x to y] == true) {
							// All possibilities exhausted - return to start pos
							y--
							x--
							break
						}
					}
				}
//				println("X: $x; Y: $y")
				if(y > yMax + 1) break
			}
//			if(fell == 1) break
			if(y > yMax + 1) break
			fell++
//			println("$x $y")
//			println("------------")
			map[x to y] = true
		}
		return fell.toString()
	}

	override fun solve2(): String {
		val inp: List<List<Pair<Int, Int>>> = getInputSplit("\n").map { line -> line.split(" -> ").map { sector -> sector.split(",").map { it.toInt() } }.map { xy -> xy[0] to xy[1] } }
		val map: HashMap<Pair<Int, Int>, Boolean> = HashMap()
		for(sector: List<Pair<Int, Int>> in inp) {
			var pX: Int = -1
			var pY: Int = -1
			for(pair: Pair<Int, Int> in sector) {
				if(pX == -1) pX = pair.first
				if(pY == -1) pY = pair.second
				(min(pair.first, pX)..max(pair.first, pX)).forEach { map[it to pair.second] = true }
				(min(pair.second, pY)..max(pair.second, pY)).forEach { map[pair.first to it] = true }
				pX = pair.first
				pY = pair.second
			}
		}
		var yMax: Int = map.keys.maxOf { it.second }
		(0..1000).forEach { map[it to yMax+2] = true }
		yMax += 2
		println("X from ${map.keys.minOf { it.first }} to ${map.keys.maxOf { it.first }}; Y from ${map.keys.minOf { it.second }} to $yMax")
		var fell: Int = 0
		while(true) {
			var x: Int = 500
			var y: Int = 0
			while(true) {
				y++
				if(map[x to y] == true) {
					x--
					if(map[x to y] == true) {
						x += 2
						if(map[x to y] == true) {
							// All possibilities exhausted - return to start pos
							y--
							x--
							break
						}
					}
				}
			}
			fell++
			map[x to y] = true
			if(map[500 to 0] == true) break
		}
		return fell.toString()
	}
}