package days

import Solution
import kotlin.math.abs

class Day25 : Solution {
	override val day: UByte = 25u

	override fun solve1(): String {
		val mapping: HashMap<Char, Int> = HashMap()
		mapping['2'] = 2
		mapping['1'] = 1
		mapping['0'] = 0
		mapping['-'] = -1
		mapping['='] = -2

		fun decode(snafu: String): Int {
			var n: Int = 0
			snafu.forEach { n *= 5; n += mapping[it]!! }
			return n
		}

		fun encode(num: Int, res: ArrayList<Char>) {
			if(num == 0)
				return

			when(num % 5) {
				0 -> { res.add('0'); encode(num / 5, res) }
				1 -> { res.add('1'); encode(num / 5, res) }
				2 -> { res.add('2'); encode(num / 5, res) }
				3 -> { res.add('='); encode((num + 2) / 5, res) }
				4 -> { res.add('-'); encode((num + 1) / 5, res) }
			}
		}

		val encoded: ArrayList<Char> = ArrayList()
		val sum: Int = getInputSplit("\n").sumOf { decode(it) }
		encode(sum, encoded)
		return encoded.reversed().joinToString("")
	}

	override fun solve2(): String {
		return ""
	}
}