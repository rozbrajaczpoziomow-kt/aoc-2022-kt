package days

import Solution

class Day7 : Solution {
	interface FSObject {
		val name: String
	}

	class Directory(override val name: String) : FSObject {
		companion object {
			fun fromString(str: String): Directory {
				val split: List<String> = str.split(" ")
				assert(split[0] == "dir")
				return Directory(split[1])
			}
		}

		private val subobjects: HashMap<String, FSObject> = HashMap()

		fun getSubobject(name: String): FSObject? {
			return subobjects[name]
		}

		fun addSubobject(name: String, obj: FSObject) {
			subobjects[name] = obj
		}

		fun getSubobjectNames(): List<String> {
			return subobjects.keys.toList()
		}

		fun getAllSubfiles(): ArrayList<File> {
			val ret: ArrayList<File> = ArrayList()
			val toScan: ArrayList<Directory> = ArrayList()
			toScan.add(this)
			while(toScan.size > 0) {
				val d: Directory = toScan.removeAt(0)
				for(name in d.getSubobjectNames()) {
					val obj: FSObject = d.getSubobject(name) as FSObject
					if(obj is Directory)
						toScan.add(obj)
					else
						ret.add(obj as File)
				}
			}
			return ret
		}

		fun getAllSubdirectories(): ArrayList<Directory> {
			val ret: ArrayList<Directory> = ArrayList()
			val toScan: ArrayList<Directory> = ArrayList()
			toScan.add(this)
			while(toScan.size > 0) {
				val d: Directory = toScan.removeAt(0)
				for(name in d.getSubobjectNames()) {
					val obj: FSObject = d.getSubobject(name) as FSObject
					if(obj is Directory) {
						toScan.add(obj)
						ret.add(obj)
					}
				}
			}
			return ret
		}
	}

	class File(val size: Int, override val name: String) : FSObject {
		companion object {
			fun fromString(str: String): File {
				val split: List<String> = str.split(" ")
				val size: Int = split[0].toInt()
				val name: String = split[1]
				return File(size, name)
			}
		}
	}

	override val day: UByte = 7u

	override fun solve1(): String {
		var dir: String = "/"
		val slash: Directory = Directory("/")

		for(_cmd in getInputSplit("$ ").drop(2)) {
			val split: List<String> = _cmd.split("\n").dropLastWhile { it.isEmpty() || it.isBlank() }
			val cmd = split[0]
			val result = split.drop(1)
			if(cmd.startsWith("cd ")) {
				val cdTo = cmd.split(" ")[1]
				if(cdTo == "..")
					dir = dir.dropLastWhile { it != '/' }.dropLast(1)
				else
					dir += "/$cdTo"
				if(!dir.startsWith("/"))
					dir = "/$dir"
				if(dir.startsWith("//"))
					dir = dir.drop(1)
			} else if(cmd == "ls") {
				val path: List<String> = dir.drop(1).split("/") // ["abc", "def", "ghi"]
				var dir: Directory = slash
				for(name in path) {
					if(name.isEmpty() || name.isBlank())
						continue
					if(dir.getSubobject(name) == null)
						dir.addSubobject(name, Directory(name))
					dir = dir.getSubobject(name) as Directory
				}
				for(file in result) {
					val obj: FSObject = if(file[0].isDigit()) File.fromString(file) else Directory.fromString(file)
					dir.addSubobject(obj.name, obj)
//					println("Added ${obj.name} to ${dir.name}")
				}
			}

//			println("[@$dir] $cmd -> $result")
			assert(slash.getSubobjectNames().none { it == "" })
			// if(not ...) exit()
		}
		return slash.getAllSubdirectories().map { dir -> dir.getAllSubfiles().sumOf { it.size } }.filter { it <= 100000 }.sum().toString()
	}

	override fun solve2(): String {
		var dir: String = "/"
		val slash: Directory = Directory("/")

		for(_cmd in getInputSplit("$ ").drop(2)) {
			val split: List<String> = _cmd.split("\n").dropLastWhile { it.isEmpty() || it.isBlank() }
			val cmd = split[0]
			val result = split.drop(1)
			if(cmd.startsWith("cd ")) {
				val cdTo = cmd.split(" ")[1]
				if(cdTo == "..")
					dir = dir.dropLastWhile { it != '/' }.dropLast(1)
				else
					dir += "/$cdTo"
				if(!dir.startsWith("/"))
					dir = "/$dir"
				if(dir.startsWith("//"))
					dir = dir.drop(1)
			} else if(cmd == "ls") {
				val path: List<String> = dir.drop(1).split("/")
				var dir: Directory = slash
				for(name in path) {
					if(name.isEmpty() || name.isBlank())
						continue
					if(dir.getSubobject(name) == null)
						dir.addSubobject(name, Directory(name))
					dir = dir.getSubobject(name) as Directory
				}
				for(file in result) {
					val obj: FSObject = if(file[0].isDigit()) File.fromString(file) else Directory.fromString(file)
					dir.addSubobject(obj.name, obj)
//					println("Added ${obj.name} to ${dir.name}")
				}
			}

//			println("[@$dir] $cmd -> $result")
			assert(slash.getSubobjectNames().none { it == "" })
		}
		val totalSpace: Int = 70000000 // Total space on device
		val neededSpace: Int = 30000000 // Needed space for update
		val takenSpace: Int = slash.getAllSubfiles().sumOf { it.size } // Space taken up by files
		val freeSpace: Int = totalSpace - takenSpace // Free space
		val deleteSpace: Int = neededSpace - freeSpace // Space that we need to delete to be able to install the update
		return slash.getAllSubdirectories().map { dir -> dir.getAllSubfiles().sumOf { it.size } }.filter { it >= deleteSpace }.min().toString()
	}
}