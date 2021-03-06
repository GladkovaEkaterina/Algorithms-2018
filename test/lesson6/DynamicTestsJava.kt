package lesson6

import org.junit.jupiter.api.Tag
import kotlin.test.Test

class DynamicTestsJava : AbstractDynamicTests() {

    @Test
    @Tag("Normal")
    fun testLongestCommonSubSequence() {
        longestCommonSubSequence { first, second -> JavaDynamicTasks.longestCommonSubSequence(first, second) }
    }

    @Test
    @Tag("Normal")
    fun testLongestCommonSubSequence2() {
        longestCommonSubSequence2 { first, second -> JavaDynamicTasks.longestCommonSubSequence(first, second) }
    }

    @Test
    @Tag("Hard")
    fun testLongestIncreasingSubSequence() {
        longestIncreasingSubSequence { JavaDynamicTasks.longestIncreasingSubSequence(it) }
    }

    @Test
    @Tag("Hard")
    fun testLongestIncreasingSubSequence2() {
        longestIncreasingSubSequence2 { JavaDynamicTasks.longestIncreasingSubSequence(it) }
    }

    @Test
    @Tag("Hard")
    fun testShortestPathOnField() {
        shortestPathOnField { JavaDynamicTasks.shortestPathOnField(it) }
    }

    @Test
    @Tag("Hard")
    fun testShortestPathOnField2() {
        shortestPathOnField2 { JavaDynamicTasks.shortestPathOnField(it) }
    }
}