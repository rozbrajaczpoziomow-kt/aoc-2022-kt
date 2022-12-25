package days

import Solution

class Day18 : Solution {
	override val day: UByte = 18u

	private class Coordinate<T : Number>(val x: T, val y: T, val z: T) {
		override fun toString(): String {
			return "Coordinate($x, $y, $z)"
		}
	}

	private fun <E : Number> List<E>.toCoordinate(): Coordinate<E> {
		require(this.size == 3)
		return Coordinate(this[0], this[1], this[2])
	}

	private fun IntArray.toCoordinate(): Coordinate<Int> {
		require(this.size == 3)
		return Coordinate(this[0], this[1], this[2])
	}

	override fun solve1(): String {
		val cubes: ArrayList<Coordinate<Int>> = ArrayList()
		getInputSplit("\n").forEach { cube -> cubes.add(cube.split(",").map { coordinate -> coordinate.toInt() }.toCoordinate()) }
		return cubes.sumOf {
			var s: Int = 0
			if(!cubes.contains(intArrayOf(it.x + 1, it.y, it.z).toCoordinate())) s += 1
			if(!cubes.contains(intArrayOf(it.x, it.y + 1, it.z).toCoordinate())) s += 1
			if(!cubes.contains(intArrayOf(it.x, it.y, it.z + 1).toCoordinate())) s += 1
			if(!cubes.contains(intArrayOf(it.x - 1, it.y, it.z).toCoordinate())) s += 1
			if(!cubes.contains(intArrayOf(it.x, it.y - 1, it.z).toCoordinate())) s += 1
			if(!cubes.contains(intArrayOf(it.x, it.y, it.z - 1).toCoordinate())) s += 1
			s
		}.toString()
	}

	override fun solve2(): String {
		val cubes: ArrayList<Coordinate<Int>> = ArrayList()
		getInputSplit("\n").forEach { cube -> cubes.add(cube.split(",").map { coordinate -> coordinate.toInt() }.toCoordinate()) }
		
		val minX: Int = cubes.minOf { it.x } - 1
		val minY: Int = cubes.minOf { it.y } - 1
		val minZ: Int = cubes.minOf { it.z } - 1
		val maxX: Int = cubes.maxOf { it.x } + 2
		val maxY: Int = cubes.maxOf { it.y } + 2
		val maxZ: Int = cubes.maxOf { it.z } + 2
		val water: ArrayList<Coordinate<Int>> = ArrayList()
		val toFill: HashSet<Coordinate<Int>> = HashSet()
		val history: HashSet<Coordinate<Int>> = HashSet()
		var iter: Long = 0
		toFill.add(Coordinate(minX, minY, minZ))

		while(toFill.size > 0) {
			iter++
			if(iter % 10000L == 0L)
				println("$iter - Water: ${water.size}")
			val coord: Coordinate<Int> = toFill.first()
			toFill.remove(coord)
			if(water.contains(coord) || cubes.contains(coord) || history.contains(coord))
				continue
			water.add(coord)
			history.add(coord)
			for((dX: Int, dY: Int, dZ: Int) in listOf(listOf(1, 0, 0), listOf(0, 1, 0), listOf(0, 0, 1), listOf(-1, 0, 0), listOf(0, -1, 0), listOf(0, 0, -1))) {
				val tX: Int = coord.x + dX
				val tY: Int = coord.y + dY
				val tZ: Int = coord.z + dZ
				if(tX in minX until maxX && tY in minY until maxY && tZ in minZ until maxZ)
					toFill.add(Coordinate(tX, tY, tZ))
			}
		}

		var n: Int = 0
		for(c in cubes)
			for((dX: Int, dY: Int, dZ: Int) in listOf(listOf(1, 0, 0), listOf(0, 1, 0), listOf(0, 0, 1), listOf(-1, 0, 0), listOf(0, -1, 0), listOf(0, 0, -1)))
				if(water.contains(Coordinate(c.x + dX, c.y + dY, c.z + dZ)))
					n++

		return n.toString()
	}
}