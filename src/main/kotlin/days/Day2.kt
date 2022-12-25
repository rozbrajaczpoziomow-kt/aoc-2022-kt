package days

import Solution

class Day2 : Solution {
	enum class RPSResult {
		WIN, TIE, LOSE;

		fun toPoints(): Int {
			return if(this == WIN) 6 else if(this == TIE) 3 else if(this == LOSE) 0 else throw IllegalStateException() // Ternary expression v2...
		}

		companion object {
			fun fromChar(c: Char): RPSResult {
				return if(c == 'X') LOSE else if(c == 'Y') TIE else WIN
			}
		}
	}

	override val day: Int = 2;

	private fun result(opponent: Char, you: Char): RPSResult {
		val u = "ABC"["XYZ".indexOf(you)]
		// A - Rock
		// B - Paper
		// C - Scissors
		// so, A beats C, B beats A, C beats B
		if(u == opponent) // _ vs _
			return RPSResult.TIE

		if(u == 'A' && opponent == 'C') // Rock vs Scissors
			return RPSResult.WIN

		if(u == 'B' && opponent == 'A') // Paper vs Rock
			return RPSResult.WIN

		if(u == 'C' && opponent == 'B') // Scissors vs Paper
			return RPSResult.WIN

		return RPSResult.LOSE // Anything else is a loss
	}

	override fun solve1(): String {
		var sum = 0
		for(line in getInputSplit("\n")) {
			val split: List<String> = line.split(" ")
			val opponent: Char = split[0][0]
			val you: Char = split[1][0]
			val res: RPSResult = result(opponent, you)
			sum += res.toPoints()
			sum += if(you == 'X') 1 else if(you == 'Y') 2 else 3
		}
		return sum.toString()
	}

	override fun solve2(): String {
		var sum = 0
		for(line in getInputSplit("\n")) {
			val split: List<String> = line.split(" ")
			// A - Rock
			// B - Paper
			// C - Scissors
			val opponent: Char = split[0][0]
			val result: RPSResult = RPSResult.fromChar(split[1][0])
			var own = ' '

			if(result == RPSResult.TIE) {
				if(opponent == 'A')
					own = 'X'
				else if(opponent == 'B')
					own = 'Y'
				else if(opponent == 'C')
					own = 'Z'
			} else if(result == RPSResult.WIN) {
				if(opponent == 'A')
					own = 'Y'
				else if(opponent == 'B')
					own = 'Z'
				else if(opponent == 'C')
					own = 'X'
			} else {
				if(opponent == 'A')
					own = 'Z'
				else if(opponent == 'B')
					own = 'X'
				else
					own = 'Y'
			}

			sum += result.toPoints()
			sum += if(own == 'X') 1 else if(own == 'Y') 2 else 3
		}
		return sum.toString()
	}
}