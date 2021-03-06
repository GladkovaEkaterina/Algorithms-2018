package lesson2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Tag
import kotlin.test.Test

class AlgorithmsTestsJava : AbstractAlgorithmsTests() {
    @Test
    @Tag("Easy")
    fun testOptimizeBuyAndSell() {
        optimizeBuyAndSell { JavaAlgorithms.optimizeBuyAndSell(it) }
    }

    @Test
    @Tag("Easy")
    fun testJosephTask() {
        josephTask { menNumber, choiceInterval -> JavaAlgorithms.josephTask(menNumber, choiceInterval) }
    }

    @Test
    @Tag("Normal")
    fun testLongestCommonSubstring() {
        longestCommonSubstring { first, second -> JavaAlgorithms.longestCommonSubstring(first, second) }
    }

    @Test
    @Tag("Normal")
    fun testLongestCommonSubstring2() {
        assertEquals("ривет, меня зовут ", JavaAlgorithms.longestCommonSubstring("Привет, меня зовут Катя", "привет, меня зовут катя"))
        assertEquals("яблоко ", JavaAlgorithms.longestCommonSubstring("яблоко киви_ананас арбуз", "яблоко мандарин ананас "))
    }

    @Test
    @Tag("Easy")
    fun testCalcPrimesNumber() {
        calcPrimesNumber { JavaAlgorithms.calcPrimesNumber(it) }
    }

    @Test
    @Tag("Hard")
    fun testBaldaSearcher() {
        baldaSearcher { inputName, words -> JavaAlgorithms.baldaSearcher(inputName, words) }
    }

    @Test
    @Tag("Hard")
    fun testBaldaSearcher2() {
        assertEquals(setOf("DOOR", "CAT"), JavaAlgorithms.baldaSearcher("input/balda_in2.txt", setOf("DOOR", "CAT", "SWITCH")))
    }
}


