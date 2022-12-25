package days

const val alphabet: String = "abcdefghijklmnopqrstuvwxyz"
const val vowels: String = "aeiou"
const val vowelsY: String = "aeiouy"
const val digits: String = "0123456789"

val alphabetUpper: String = alphabet.uppercase()
val vowelsUpper: String = vowels.uppercase()
val vowelsUpperY: String = vowelsY.uppercase()

val alphabetBoth: String = alphabet + alphabetUpper

fun String.isNumber(): Boolean = this.all { it.isDigit() }