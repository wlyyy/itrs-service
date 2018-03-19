package org.abomb4.gdemo.utils

import java.util.*

/**
 * 中文数字工具类
 *
 * @author yangrl14628
 * @since 2018-03-07
 */
object ChineseNumberUtils {

    private val CHINESE_NUMBER: CharArray = charArrayOf('零', '一', '二', '三', '四', '五', '六', '七', '八', '九')
    private val CHINESE_UNIT: Array<String> = arrayOf("", "十", "百", "千", "万", "十", "百", "千", "亿", "十")
    private val CHINESE_SPECIAL_UNIT: Array<String> = arrayOf("万", "亿")

    /**
     * 阿拉伯数字转换成中文数字
     *
     * @param number 阿拉伯数字
     * @return 中文简体数字
     * @author yangrl14628
     */
    fun numericToChinese(number: Int): String {

        val length = number.length()
        val numberArray = number.toString().toCharArray().map { it.toInt() - '0'.toInt() }
        val chineseBulder = StringBuilder()

        var previousIsZero = false
        var lastNumberBit = length

        // 从最高位开始遍历
        for (i in 1..length) {
            // 当前位数字
            val num = numberArray.get(i - 1)

            // 当前位，以最右面的数字为1，向左递增的位置
            val bit = length - i + 1

            if (num == 0) {
                // "万“ ”亿“ 特殊处理，上四位都不为0，则添加，否则不加
                if (isSpecialUnit(bit) && lastNumberBit - bit < 4) {
                    chineseBulder.append(getSpecialUnitByBit(bit))
                    previousIsZero = false
                } else {
                    previousIsZero = true
                }
            } else {
                // 前一位是0 特殊处理 补“零”
                if (previousIsZero) {
                    chineseBulder.append(singleNumberToChinese(0))
                }
                chineseBulder
                        .append(singleNumberToChinese(num))
                        .append(getChineseNumberUnit(bit))
                previousIsZero = false
                lastNumberBit = bit
            }
        }

        // 0 特殊处理
        if (chineseBulder.length == 0) {
            chineseBulder.append(singleNumberToChinese(0))
        }
        // “一十” 特殊处理
        if (chineseBulder.startsWith(singleNumberToChinese(1) + getChineseNumberUnit(2))) {
            chineseBulder.deleteCharAt(0)
        }

        return chineseBulder.toString()
    }

    /**
     * 中文数字转换成数字
     *
     * @param chineseNumber 中文简体数字
     * @return int数字
     * @author yangrl14628
     */
    fun chineseToNumberic(chineseNumber: String): Int {
        // 1. 获取最大单位
        // 2. 逐个遍历“数字+单位”组合7
        // 3. ‘十’和‘零’特殊处理

        // 把‘十’开头的转换成‘一十’开头
        val dealedNumber =
                if (chineseNumber.startsWith(getChineseNumberUnit(2)))
                    singleNumberToChinese(1) + chineseNumber
                else chineseNumber

        val numArray = dealedNumber.toCharArray()
        // var totalNumber = 0 // 总数字
        val bitNumberArray = IntArray(6) { 0 }
        var tempNumber = 0 // 十百千算出来的数字
        var specialBitResolving = false
        var partNumber = 0 // 不算“万” “亿”这种的数字
        var previousNumber = 0
        val unitBuilder = LinkedList<Char>()

        fun resolveUnitBase(unitStr: LinkedList<Char>): Int {
            var unit = 1
            unitStr.forEach {
                val unitBit = getBitBySpecialUnit(it)
                val unitBase: Int = Math.pow(10.0, (unitBit - 1).toDouble()).toInt()
                unit *= unitBase
            }

            return unit
        }

        fun resolveUnitBase(bit: Int): Int {
            var unit = 1
            val unitBase: Int = Math.pow(10.0, bit.toDouble()).toInt()
            unit *= unitBase

            return unit
        }

        fun putIntoBitArray(bit: Int, value: Int) = bitNumberArray.set(Math.max(((bit - 1) / 4), 0), value)
        fun getFromBitArray(bit: Int) = bitNumberArray.get(Math.max(((bit - 1) / 4), 0))

        for (char in numArray) {
            specialBitResolving = false
            if (isSingleChineseNumber(char)) {
                // 这一位是数字，如果之前已经有数字和单位了则加到结果中

                // 这里只可能出现十 百 千
                if (unitBuilder.isNotEmpty()) {
                    // 上一数字 * 单位大小
                    val unit = resolveUnitBase(unitBuilder)

                    tempNumber += previousNumber * unit

                    unitBuilder.clear()
                }
                previousNumber = singleChineseToNumber(char)
            } else if (isSpecialUnit(getBitBySpecialUnit(char))) {
                // 特殊位(万 亿)

                if (unitBuilder.isNotEmpty()) {
                    // 上一数字 * 单位大小
                    val unit = resolveUnitBase(unitBuilder)
                    tempNumber += previousNumber * unit
                    unitBuilder.clear()
                } else {
                    tempNumber += previousNumber
                }

                unitBuilder.add(char)
                // val specialUnit = resolveUnitBase(unitBuilder)
                // tempNumber *= specialUnit
                val specialBit = getBitBySpecialUnit(char)
                // 判断比自己低的位有没有数据，有的话提到高位，例如万亿，把万提到万亿位
                for (lowBit in specialBit downTo 1 step 4) {
                    val lowValue = getFromBitArray(lowBit)
                    if (lowValue > 0) {
                        // 低位包含数据，提前i
                        putIntoBitArray(specialBit + lowBit, lowValue)
                        putIntoBitArray(lowBit, 0)
                    }
                }
                putIntoBitArray(specialBit, tempNumber)

                tempNumber = 0
                previousNumber = 0
                unitBuilder.clear()
                specialBitResolving = true
            } else {
                // 这一位是单位，或单位的一部分
                unitBuilder.add(char)
            }
        }

        if (previousNumber > 0) {
            if (unitBuilder.isEmpty()) {
                tempNumber += previousNumber
            } else {
                val unit = resolveUnitBase(unitBuilder)
                tempNumber += previousNumber * unit
            }
        }

        val totalNumber = bitNumberArray.reduceIndexed { index, acc, i ->
            acc + i * resolveUnitBase(index * 4)
        } + tempNumber

        return totalNumber
    }

    /**
     * 数字长度。
     *
     * @return length of int number
     */
    private fun Int.length() =
            when (this) {
                0 -> 1
                else -> Math.log10(Math.abs(toDouble())).toInt() + 1
            }

    /**
     * 转换单个数字到中文数字
     * @author yangrl14628
     * @since 2018-03-07
     */
    private fun singleNumberToChinese(number: Int): Char {
        if (number >= 10) {
            throw RuntimeException("Number $number not in 0 to 9")
        }

        return CHINESE_NUMBER.get(number)
    }

    /**
     * 转换单个中文数字到数字，支持0-9
     *
     * @author yangrl14628
     * @since 2018-03-08
     */
    private fun singleChineseToNumber(char: Char): Int {
        val index = CHINESE_NUMBER.indexOf(char)
        if (index < 0) {
            throw RuntimeException("Chinese number $char not in 0 to 9")
        }

        return index
    }

    /**
     * 获取数字单位
     *
     * @param bit 数字位，以最右面的数字为1，向左递增的位置
     * @return 数字单位，个十百千万
     */
    private fun getChineseNumberUnit(bit: Int): String {
        if (bit >= CHINESE_UNIT.size) {
            throw RuntimeException("Bit $bit not in 个 to 十亿")
        }
        return CHINESE_UNIT.get(bit - 1)
    }

    /**
     * 判断一个单个数字是否为汉字数字
     */
    private fun isSingleChineseNumber(character: Char): Boolean = CHINESE_NUMBER.contains(character)

    /**
     * 判断是否为特殊单位，目前只有 “万”、“亿” 这两个
     *
     * @param bit 数字位，以最右面的数字为1，向左递增的位置
     * @return 是否为“万”或者“亿”
     */
    private fun isSpecialUnit(bit: Int): Boolean = bit - 1 > 0 && (bit - 1) % 4 == 0


    /**
     * 获取特殊单位名，目前只有 “万”、“亿” 这两个
     *
     * @param bit 数字位，以最右面的数字为1，向左递增的位置
     * @return “万”或者“亿”
     */
    private fun getSpecialUnitByBit(bit: Int): String = getChineseNumberUnit(bit)


    /**
     * 获取特殊单位的位置，目前只有 “万”、“亿” 这两个
     *
     * @param unit ‘万’或‘亿’
     * @return bit
     */
    private fun getBitBySpecialUnit(unit: Char): Int = CHINESE_UNIT.indexOf(unit.toString()) + 1
}