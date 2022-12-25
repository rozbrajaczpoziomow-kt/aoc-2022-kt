package days

import Solution
import kotlin.math.abs
import kotlin.math.max

class Day15 : Solution {
	override val day: UByte = 15u

	override fun solve1(): String {
		val Y: Long = 2000000
		var ans: Long = 0
		val inps: ArrayList<LongArray> = ArrayList()
		getInputSplit("\n").forEach { line ->
			val sx: Long = line.dropWhile { it != '=' }.drop(1).dropLastWhile { it != ':' }.dropLastWhile { it != ',' }.dropLast(1).toLong()
			val sy: Long = line.dropWhile { it != '=' }.drop(1).dropWhile { it != '=' }.drop(1).dropLastWhile { it != ':' }.dropLast(1).toLong()
			val bx: Long = line.dropWhile { it != 'c' }.dropWhile { it != '=' }.drop(1).dropLastWhile { it != ',' }.dropLast(1).toLong()
			val by: Long = line.dropWhile { it != 'c' }.dropWhile { it != '=' }.drop(1).dropWhile { it != '=' }.drop(1).toLong()
			inps.add(longArrayOf(sx, sy, bx, by))
		}
		val intervals: ArrayList<Pair<Long, Long>> = ArrayList()
		for((x, y, xx, yy) in inps) {
			val dist: Long = abs(x - xx) + abs(y - yy)
			val yDist: Long = abs(y - Y)
			if(yDist > dist)
				continue
			val dx: Long = dist - yDist
			intervals.add((x - dx) to (x + dx))
		}
		intervals.sortBy { it.first }
		val nIntervals: ArrayList<Pair<Long, Long>> = ArrayList()
		for((l, r) in intervals) {
			if(nIntervals.size == 0 || l > nIntervals.last().second) {
				nIntervals.add(l to r)
				continue
			}
			val r: Long = max(r, nIntervals.last().second)
			val (ll: Long, rr: Long) = nIntervals.removeAt(nIntervals.lastIndex)
			nIntervals.add(ll to r)
		}
		for((l, r) in nIntervals)
			ans += r - l + 1
		return (ans - 1).toString()
	}

	override fun solve2(): String {
		var ans: Long = 0
		val inps: ArrayList<LongArray> = ArrayList()
		getInputSplit("\n").forEach { line ->
			val sx: Long = line.dropWhile { it != '=' }.drop(1).dropLastWhile { it != ':' }.dropLastWhile { it != ',' }.dropLast(1).toLong()
			val sy: Long = line.dropWhile { it != '=' }.drop(1).dropWhile { it != '=' }.drop(1).dropLastWhile { it != ':' }.dropLast(1).toLong()
			val bx: Long = line.dropWhile { it != 'c' }.dropWhile { it != '=' }.drop(1).dropLastWhile { it != ',' }.dropLast(1).toLong()
			val by: Long = line.dropWhile { it != 'c' }.dropWhile { it != '=' }.drop(1).dropWhile { it != '=' }.drop(1).toLong()
			inps.add(longArrayOf(sx, sy, bx, by))
		}
		for(i in 0..4000000) {
			val intervals: ArrayList<Pair<Long, Long>> = ArrayList()
			for((x, y, xx, yy) in inps) {
				val dist: Long = abs(x - xx) + abs(y - yy)
				val yDist: Long = abs(y - i)
				if(yDist > dist)
					continue
				val dx: Long = dist - yDist
				intervals.add((x - dx) to (x + dx))
			}
			intervals.sortBy { it.first }
			val nIntervals: ArrayList<Pair<Long, Long>> = ArrayList()
			for((l, r) in intervals) {
				if(nIntervals.size == 0 || l > nIntervals.last().second) {
					nIntervals.add(l to r)
					continue
				}
				val r: Long = max(r, nIntervals.last().second)
				val (ll: Long, rr: Long) = nIntervals.removeAt(nIntervals.lastIndex)
				nIntervals.add(ll to r)
			}
			if(nIntervals.size > 1)
				return nIntervals.maxOf { (it.first - 1) * 4000000 + it.second }.toString()
		}
		return "solution not found"
	}
}