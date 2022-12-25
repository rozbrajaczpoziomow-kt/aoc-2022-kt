import days.*

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
 	days.add(Day16())

	for(day in days) {
		downloadInput(day.day)
		println("Solutions for day ${day.day}:")

		val part2: String = day.solve2()
		println("\tPart 2 - $part2")
		if(part2.isNotEmpty() && day == days.last())
			submitSolution(day.day, 2, part2)

		val part1: String = day.solve1()
		println("\tPart 1 - $part1")
		if(part1.isNotEmpty() && part2.isEmpty() && day == days.last())
			submitSolution(day.day, 1, part1)

		println()
	}
}