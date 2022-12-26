package days

import Solution

class Day21 : Solution {
	override val day: UByte = 21u

	override fun solve1(): String { // Need Longs here, Int overflow happened :(
		fun obtain(v: String, monkeys: HashMap<String, String>): Long {
			require(monkeys.containsKey(v))
			val reqs: List<String> = monkeys[v]!!.split(" ")
			if(reqs.size == 1)
				return reqs[0].toLong()
			val (_a: String, _op: String, _b: String) = reqs
			val a: Long = obtain(_a, monkeys)
			val b: Long = obtain(_b, monkeys)
			val op: Char = _op[0]
			return when(op) {
				'*' -> a * b
				'/' -> a / b
				'-' -> a - b
				'+' -> a + b
				else -> throw IllegalStateException("Unknown operation: $op")
			}
		}

		val monkeys: HashMap<String, String> = HashMap()
		getInputSplit("\n").forEach {
			val split: List<String> = it.split(": ")
			monkeys[split[0]] = split[1]
		}
		return obtain("root", monkeys).toString()
	}

	override fun solve2(): String {
		fun obtain(v: String, monkeys: HashMap<String, String>, used: ArrayList<String>): Long {
			used.add(v)
			require(monkeys.containsKey(v))
			val reqs: List<String> = monkeys[v]!!.split(" ")
			if(reqs.size == 1)
				return reqs[0].toLong()
			val (_a: String, _op: String, _b: String) = reqs
			val a: Long = obtain(_a, monkeys, used)
			val b: Long = obtain(_b, monkeys, used)
			val op: Char = _op[0]
			return when(op) {
				'*' -> a * b
				'/' -> a / b
				'-' -> a - b
				'+' -> a + b
				else -> throw IllegalStateException("Unknown operation: $op")
			}
		}

		fun bruteforce(start: Long, step: Long, monkeys: HashMap<String, String>, variable: String, goal: Long): Long {
//			println("Bruteforcing with step $step")
			var i: Long = start
			while(true) {
				i += step
				monkeys["humn"] = i.toString()
				val res: Long = obtain(variable, monkeys, ArrayList())
				if(res <= goal)
					return i - step
			}
		}

		val monkeys: HashMap<String, String> = HashMap()
		getInputSplit("\n").forEach {
			val split: List<String> = it.split(": ")
			monkeys[split[0]] = split[1]
		}

		val isA: ArrayList<String> = ArrayList()
		val (vA: String, _: String, vB: String) = monkeys["root"]!!.split(" ")
		obtain(vA, monkeys, isA) // value is irrelevant
		val variable: String = if(isA.contains("humn")) vA else vB
		println("Dependent on humn: $variable")
		val goal: Long = obtain(if(variable == vA) vB else vA, monkeys, ArrayList())
		var currentValue: Long = 0
		// From the largest steps to the lowest: increasing precision
		// In each bruteforce we basically see which order of magnitude is the answer - all the way from 1 billion, to 1
		// In the end, we end up with the correct result, and it's **much** faster than just bruteforcing with a step of 1
		var currentStep: Long = 1_000_000_000_000L
		while(true) {
			currentValue = bruteforce(currentValue, currentStep, monkeys, variable, goal)
			if(currentStep == 1L)
				break
			currentStep /= 10
		}
		// We have the correct current value set from bruteforce
		require(obtain(variable, monkeys, ArrayList()) == goal)
		return monkeys["humn"]!!
	}
}