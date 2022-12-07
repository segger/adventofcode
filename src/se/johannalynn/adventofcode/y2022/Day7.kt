package se.johannalynn.adventofcode.y2022

import se.johannalynn.adventofcode.y2022.Day.start
import java.util.*

object Day7 {

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = start(7, false)

        //star1(scanner)
        star2(scanner)
    }

    private fun star1(scanner: Scanner) {
        val folders = mutableListOf<Folder>()
        val goToRoot = scanner.nextLine()
        val rootFolder = Folder("/")
        folders.add(rootFolder)
        var currentFolder = rootFolder
        while(scanner.hasNextLine()) {
            val line = scanner.nextLine()
            // println(line)
            if (line.startsWith("$")) {
                val command = line.split(" ")
                when(command[1]) {
                    "ls" -> {}
                    "cd" -> {
                        if (command[2] == "/") {
                            currentFolder = rootFolder
                        } else if (command[2] == "..") {
                            currentFolder = currentFolder.parentFolder!!
                        } else {
                            val dir = currentFolder.subFolders[command[2]]
                            dir!!.parentFolder = currentFolder
                            currentFolder = dir
                        }
                    }
                }
            } else {
                val file = line.split(" ")
                if ("dir" == file[0]) {
                    val folder = Folder(file[1])
                    folders.add(folder)
                    currentFolder.addFolder(file[1], folder)
                } else {
                    currentFolder.addFile(file[1], file[0].toInt())
                }
            }
        }
        // println(rootFolder.getTotalSize())

        val sum = folders.map { it.getTotalSize() }.filter { it < 100000 }.sum()
        println(sum)
    }

    private fun star2(scanner: Scanner) {

        val folders = mutableListOf<Folder>()
        val goToRoot = scanner.nextLine()
        val rootFolder = Folder("/")
        folders.add(rootFolder)
        var currentFolder = rootFolder
        while(scanner.hasNextLine()) {
            val line = scanner.nextLine()
            // println(line)
            if (line.startsWith("$")) {
                val command = line.split(" ")
                when(command[1]) {
                    "ls" -> {}
                    "cd" -> {
                        if (command[2] == "/") {
                            currentFolder = rootFolder
                        } else if (command[2] == "..") {
                            currentFolder = currentFolder.parentFolder!!
                        } else {
                            val dir = currentFolder.subFolders[command[2]]
                            dir!!.parentFolder = currentFolder
                            currentFolder = dir
                        }
                    }
                }
            } else {
                val file = line.split(" ")
                if ("dir" == file[0]) {
                    val folder = Folder(file[1])
                    folders.add(folder)
                    currentFolder.addFolder(file[1], folder)
                } else {
                    currentFolder.addFile(file[1], file[0].toInt())
                }
            }
        }

        val diskspace = 70000000
        val spaceNeeded = 30000000
        val usedSpace = rootFolder.getTotalSize()
        val freeSpace = diskspace - usedSpace
        val minDeleteSpace = spaceNeeded - freeSpace

        val directory = folders.map { it.getTotalSize() }.filter { it > minDeleteSpace }.minOrNull()
        println(directory)
    }

    class Folder(val name: String) {
        var parentFolder: Folder? = null
        val subFolders = mutableMapOf<String, Folder>()
        val files = mutableMapOf<String, Int>()

        fun addFile(name: String, size: Int) {
            files[name] = size
        }

        fun addFolder(name: String, folder: Folder) {
            subFolders[name] = folder
        }

        fun getTotalSize(): Int {
            return subFolders.map { it.value.getTotalSize() }.sum() + files.values.sum()
        }

        override fun toString(): String {
            return "$name \n ${subFolders.map { it.value.toString() }} \n ${files.map { it.toString() }}"
        }
    }
}