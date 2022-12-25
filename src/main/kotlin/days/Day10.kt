package days

import Solution

class Day10 : Solution {
	override val day: UByte = 10u

	private class Instruction(private val inst: String, private val X: Int, private val takes: Int) {
		private var tick: Int = 0

		companion object {
			fun fromString(s: String): Instruction {
				val split: List<String> = s.split(" ")
				if(split.size == 1) return Instruction(split[0], 0, 1)
				return Instruction(split[0], split[1].toInt(), 2)
			}
		}

		fun nextTick(x: Int): Pair<Int, Boolean> {
			var x: Int = x
			var next: Boolean = false
			tick++;
			if(tick == takes) {
				x += X
				next = true
			}
			return x to next
		}

		override fun toString(): String {
			return "{"
		}
	}

	override fun solve1(): String {
		val instructions: ArrayList<Instruction> = ArrayList(getInputSplit("\n").map { Instruction.fromString(it) })
		var cycle: Int = 0
		var x: Int = 0
		var strength: Int = 0
		while(instructions.size > 1) {
			cycle++
			val (xn: Int, nxt: Boolean) = instructions[0].nextTick(x)
			x = xn
			if(nxt)
				instructions.removeAt(0)
			if(cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220) {
				strength += x * cycle
			}
		}
		return ""
//		return strength.toString()
	}

	override fun solve2(): String {
		println("Day 10 isn't implemented, as part 1 is not working for no reason")
		return ""
	}
}