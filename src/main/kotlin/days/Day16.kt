package days

import Solution
import kotlin.math.max

class Day16 : Solution {
	override val day: UByte = 16u

	override fun solve1(): String {
		val valves: HashSet<String> = HashSet()
		val tunnels: HashMap<String, HashSet<String>> = HashMap()
		val flow: HashMap<String, Long> = HashMap()
		var s: Long = 0
		for(line in getInputSplit("\n")) {
			val split: List<String> = line.split(" ")
			val name: String = split[1]
			val f: Long = split[4].dropWhile { !it.isDigit() }.dropLast(1).toLong()
			val to: List<String> = split.dropWhile { it != "valves" }.drop(1).map { it.replace(",", "") }
			valves.add(name)
			tunnels[name] = HashSet(to)
			flow[name] = f
		}
		var m: ArrayList<Triple<String, HashSet<String>, Long>> = ArrayList() // HashSet<String>?
		m.add(Triple("AA", HashSet(), 0))
		val memo: HashMap<Pair<String, String>, Long> = HashMap()
		var rt: Long = 0
		for(i in 0..29) {
			val new: ArrayList<Triple<String, HashSet<String>, Long>> = ArrayList()
			for(x in m) {
				val (name: String, state: HashSet<String>, pressure: Long) = x
				// Open
				if(!state.contains(name) && flow[name]!! > 0) {
					val newState: HashSet<String> = state
					val newPressure: Long = pressure + (30 - i - 1) * flow[name]!!
					newState.add(name)
					val key: Pair<String, String> = name to newState.toList().sorted().toString()
					if(memo.contains(key))
						if(newPressure <= memo[key]!!)
							continue
					memo[key] = newPressure
					new.add(Triple(name, newState, newPressure))
					rt = max(rt, newPressure)
				}

				// Switch
				for(v in tunnels[name]!!) {
					val key: Pair<String, String> = v to state.toList().sorted().toString()
					if(memo.containsKey(key))
						if(pressure <= memo[key]!!)
							continue
					memo[key] = pressure
					new.add(Triple(v, state, pressure))
				}
			}
			m = new
		}
		return rt.toString()
	}

	override fun solve2(): String {
		return ""
	}
}