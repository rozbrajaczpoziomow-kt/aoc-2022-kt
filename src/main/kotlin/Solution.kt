import java.io.File
import kotlin.text.Charsets.UTF_8

interface Solution {
	val day: Int
	fun getInputSplit(on: String): List<String> {
		return getInput().split(on)
	}

	fun getInput(): String {
		return getInputFile().readText(UTF_8).dropLastWhile { it == '\n' }
	}

	fun getInputFile(): File {
		return File("inputs/$day.in")
	}

	fun solve1(): String
	fun solve2(): String
}