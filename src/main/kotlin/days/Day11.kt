package days

import Solution

class Day11 : Solution {
	private class Throw(val to: Long, val item: Long)

	private class Monkey(val id: Long, val items: ArrayList<Long>, val op: (Long) -> Long, val test: Long, val ifYes: Long, val ifNo: Long) {
		var activity: Long = 0
		var maxMod: Long = Long.MAX_VALUE
		companion object Static {
			fun fromString(fields: List<String>, divideBy3: Boolean): Monkey {
//Monkey 0:
//  Starting items: 98, 97, 98, 55, 56, 72
//  Operation: new = old * 13
//  Test: divisible by 11
//    If true: throw to monkey 4
//    If false: throw to monkey 7
				val monkeyId: Long = fields[0].dropLastWhile { !it.isDigit() }.dropWhile { !it.isDigit() }.toLong()
				val items: List<Long> = fields[1].dropWhile { !it.isDigit() }.split(", ").map { it.toLong() }
				val (op: String, n: String) = fields[2].dropWhile { it != '*' && it != '+' }.split(" ")
				val test: Long = fields[3].dropWhile { !it.isDigit() }.toLong()
				val yes: Long = fields[4].dropWhile { !it.isDigit() }.toLong()
				val no: Long = fields[5].dropWhile { !it.isDigit() }.toLong()
				val operation: (Long) -> Long = {
					val newOp: Long
					if(op == "*") {
						if(n == "old") newOp = it * it
						else newOp = it * n.toLong()
					} else newOp = it + n.toLong()

					if(divideBy3) newOp / 3
					else newOp
				}
				return Monkey(monkeyId, ArrayList(items), operation, test, yes, no)
			}
		}

		fun addItem(item: Long) {
			items.add(item % maxMod)
		}

		fun process(): List<Throw> {
			val ret: List<Throw> = items.map {
				activity++
				val newItem: Long = op(it) % maxMod
				if(newItem % test == 0L) Throw(ifYes, newItem)
				else Throw(ifNo, newItem)
			}.toList()
			items.clear()
			return ret
		}
	}


	override val day: UByte = 11u

	override fun solve1(): String {
		val monkeys: HashMap<Long, Monkey> = HashMap()
		getInputSplit("\n\n").forEach { val monke: Monkey = Monkey.fromString(it.split("\n"), true); monkeys[monke.id] = monke }

		// Mod optimization
		var maxMod: Long = 1
		monkeys.values.forEach { maxMod *= it.test }
		monkeys.values.forEach { it.maxMod = maxMod }
		println(maxMod)

		repeat(20) {
			for(monkeyId in monkeys.keys.sorted())
				for(thr in monkeys[monkeyId]!!.process())
					monkeys[thr.to]!!.addItem(thr.item)
//			println("On iteration $it")
		}
		val mostActivity: List<Long> = monkeys.values.map { it.activity }.sortedDescending()
		return (mostActivity[0] * mostActivity[1]).toString()
	}

	override fun solve2(): String {
		val monkeys: HashMap<Long, Monkey> = HashMap()
		getInputSplit("\n\n").forEach { val monke: Monkey = Monkey.fromString(it.split("\n"), false); monkeys[monke.id] = monke }

		// Mod optimization
		var maxMod: Long = 1
		monkeys.values.forEach { maxMod *= it.test }
		monkeys.values.forEach { it.maxMod = maxMod * 4 }
		println(maxMod)

		repeat(10000) {
			for(monkeyId in monkeys.keys.sorted())
				for(thr in monkeys[monkeyId]!!.process())
					monkeys[thr.to]!!.addItem(thr.item)
			if(it % 10 == 0)
				println("On iteration $it")
		}
		val mostActivity: List<Long> = monkeys.values.map { it.activity }.sortedDescending()
		return (mostActivity[0] * mostActivity[1]).toString()
	}
}