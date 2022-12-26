import days.*
import kotlin.time.Duration
import kotlin.time.Duration.Companion.nanoseconds

fun main() {
	val days: ArrayList<Solution> = ArrayList()

//	days.add(Day1())
//	days.add(Day2())
//	days.add(Day3())
//	days.add(Day4())
//	days.add(Day5())
//	days.add(Day6())
//	days.add(Day7())
//	days.add(Day8())
//	days.add(Day9())
//	days.add(Day10())
//	days.add(Day11())
//	Day 12
//	days.add(Day13())
//	days.add(Day14())
//	days.add(Day15())
// 	days.add(Day16())
//	Day 17
//	days.add(Day18())
//	Day 19
//	Day 20
	days.add(Day21())

	var durationAll: Duration = (0L).nanoseconds
	for(day in days) {
		downloadInput(day.day)
		println("Solutions for day ${day.day}:")

		val bf2: Long = System.nanoTime()
		val part2: String = day.solve2()
		val af2: Long = System.nanoTime()
		val took2: Duration = (af2 - bf2).nanoseconds
		durationAll = durationAll.plus(took2)
		println("\tPart 2 - $part2 [$took2]")
		if(part2.isNotEmpty() && day == days.last())
			submitSolution(day.day, 2, part2)

		val bf1: Long = System.nanoTime()
		val part1: String = day.solve1()
		val af1: Long = System.nanoTime()
		val took1: Duration = (af1 - bf1).nanoseconds
		durationAll = durationAll.plus(took1)
		println("\tPart 1 - $part1 [$took1]")
		if(part1.isNotEmpty() && part2.isEmpty() && day == days.last())
			submitSolution(day.day, 1, part1)

		println()
	}
	println("Took $durationAll")
}