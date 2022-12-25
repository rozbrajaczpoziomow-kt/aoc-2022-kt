package days

import Solution
import kotlin.math.abs
import kotlin.math.sign

class Day9 : Solution {
	override val day: UByte = 9u

	private operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> {
		return Pair(this.first + other.first, this.second + other.second)
	}

	fun solve(cnt: Int): Int {
		val input: List<Pair<Char, Int>> = getInputSplit("\n").map { Pair(it[0], it.drop(2).toInt()) }

		val knots = Array(cnt) { Pair(0, 0) }
		val seen = mutableSetOf(knots.last())

		for (line in input) {
			val (dir, n) = line

			for (i in 0 until n) {
				knots[0] += when (dir) {
					'U' -> Pair(0, 1)
					'D' -> Pair(0, -1)
					'R' -> Pair(1, 0)
					'L' -> Pair(-1, 0)
					else -> throw IllegalArgumentException()
				}

				for (j in 1 until knots.size) {
					val head = knots[j - 1]
					val tail = knots[j]

					if (head.first == tail.first && abs(head.second - tail.second) == 2) {
						knots[j] = Pair(tail.first, tail.second + (head.second - tail.second).sign)
					}

					if (head.second == tail.second && abs(head.first - tail.first) == 2) {
						knots[j] = Pair(tail.first + (head.first - tail.first).sign, tail.second)
					}

					if (abs(head.first - tail.first) >= 1 && abs(head.second - tail.second) >= 2 || abs(head.second - tail.second) >= 1 && abs(head.first - tail.first) >= 2) {
						knots[j] = Pair(tail.first + (head.first - tail.first).sign, tail.second + (head.second - tail.second).sign)
					}
				}

				seen += knots.last()
			}
		}

		return seen.size
}

	override fun solve1(): String {
		return solve(2).toString()
	}

	override fun solve2(): String {
		return solve(10).toString()
	}
}