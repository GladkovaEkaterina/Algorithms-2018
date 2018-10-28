package lesson1

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.io.File

class TaskTestsJava : AbstractTaskTests() {

    @Test
    @Tag("Easy")
    fun testSortTimes() {
        sortTimes { inputName, outputName -> JavaTasks.sortTimes(inputName, outputName) }
    }

    @Test
    @Tag("Normal")
    fun testSortAddresses() {
        sortAddresses { inputName, outputName -> JavaTasks.sortAddresses(inputName, outputName) }
    }

    @Test
    @Tag("Normal")
    fun testSortTemperatures() {
        sortTemperatures { inputName, outputName -> JavaTasks.sortTemperatures(inputName, outputName) }
    }

    @Test
    @Tag("Normal")
    fun testSortTemperatures2() {
        try {
            JavaTasks.sortTemperatures("input/temp_in2.txt", "temp.txt")
            assertFileContent("temp.txt",
                    """
                        -87.7
                        -3.0
                        64.4
                        64.5
                        65.3
                        432.9
                    """.trimIndent())
        } finally {
            File("temp.txt").delete()
        }
    }


    @Test
    @Tag("Normal")
    fun testSortSequence() {
        sortSequence { inputName, outputName -> JavaTasks.sortSequence(inputName, outputName) }
    }

    @Test
    @Tag("Normal")
    fun testSortSequence2() {
        try {
            JavaTasks.sortSequence("input/seq_in3.txt", "temp.txt")
            assertFileContent("temp.txt",
                    """
                        100
                        10000
                        1002
                        1002
                    """.trimIndent())
        } finally {
            File("temp.txt").delete()
        }
    }

    @Test
    @Tag("Easy")
    fun testMergeArrays() {
        val result = arrayOf(null, null, null, null, null, 1, 3, 9, 13, 18, 23)
        JavaTasks.mergeArrays<Int>(arrayOf(4, 9, 15, 20, 23), result)
        assertArrayEquals(arrayOf(1, 3, 4, 9, 9, 13, 15, 18, 20, 23, 23), result)

        run {
            val (first, second, expectedResult) = generateArrays(20000, 20000)
            JavaTasks.mergeArrays<Int>(first, second)
            assertArrayEquals(expectedResult, second)
        }

        run {
            val (first, second, expectedResult) = generateArrays(500000, 500000)
            JavaTasks.mergeArrays<Int>(first, second)
            assertArrayEquals(expectedResult, second)
        }
    }

    @Test
    @Tag("Easy")
    fun testMergeArrays2() {
        val result = arrayOf(null, null, null, null, null, 6, 7, 8, 9, 10, 11)
        JavaTasks.mergeArrays<Int>(arrayOf(1, 2, 3, 4, 5), result)
        assertArrayEquals(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), result)

        val result2 = arrayOf(null, null, null, null, null, 1, 2, 3, 4, 5, 6)
        JavaTasks.mergeArrays<Int>(arrayOf(7, 8, 9, 10, 11), result2)
        assertArrayEquals(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), result2)
    }
}