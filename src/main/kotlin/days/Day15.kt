package days

import Solution
import kotlin.math.abs
import kotlin.math.max

class Day15 : Solution {
	override val day: Int = 15

	override fun solve1(): String {
		val Y: Int = 2000000
		val known: HashSet<Int> = HashSet()
		val intervals: ArrayList<Pair<Int, Int>> = ArrayList()
		for(line in getInputSplit("\n")) {
			val sx: Int = line.dropWhile { it != '=' }.drop(1).dropLastWhile { it != ':' }.dropLastWhile { it != ',' }.dropLast(1).toInt()
			val sy: Int = line.dropWhile { it != '=' }.drop(1).dropWhile { it != '=' }.drop(1).dropLastWhile { it != ':' }.dropLast(1).toInt()
			println(line.dropWhile { it != 'c' })
			val bx: Int = line.dropWhile { it != 'c' }.dropWhile { it != '=' }.drop(1).dropLastWhile { it != ',' }.dropLast(1).toInt()
			val by: Int = line.dropWhile { it != 'c' }.dropWhile { it != '=' }.drop(1).dropWhile { it != '=' }.drop(1).toInt()
			println("$sx $sy $bx $by")
//			val (sx, sy, bx, by) = pattern.findAll(line).map { it.value.toInt() }.toList()
			val d: Int = abs(sx - bx) + abs(sy - by)
			val o: Int = d - abs(sy - Y)

			if(o < 0)
				continue

			intervals.add((sx - o) to (sx + o))
			if(by == Y)
				known.add(bx)
		}
//		intervals.sortBy {  }
		val q: ArrayList<Pair<Int, Int>> = ArrayList()
		for((lo: Int, hi: Int) in intervals) {
			if(q.size == 0) {
				q.add(lo to hi)
				continue
			}
			val (qlo: Int, qhi: Int) = q.last()
			if(lo > qhi + 1) {
				q.add(lo to hi)
				continue
			}
			q[q.lastIndex] = qlo to max(qhi, hi)
		}
		val cannot: HashSet<Int> = HashSet()
		for((lo, hi) in q)
			for(x in lo..hi+1)
				cannot.add(x)
		return cannot.filter { !known.contains(it) }.size.toString()
	}

	override fun solve2(): String {
		return ""
	}
}