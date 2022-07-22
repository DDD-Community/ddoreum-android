package com.project.ddoreum.common

import kotlin.math.floor

object SearchKeywordUtil {

    fun match(keyword: String, name: String): Boolean {
        var score = 0
        var index = 0
        while (index < name.length) {
            val char = name[index]
            if (keyword[score] == char || keyword[score] == getChosung(char)) {
                score++
            } else {
                index -= score
                score = 0
            }

            if (score == keyword.length) {
                return true
            }
            index++
        }
        return false
    }

    private fun getChosung(char: Char): Char {
        return if (char.isKorean()) {
            chosungList[(floor((char - 44032).code.toDouble()) / 588).toInt()]
        } else {
            char
        }
    }

    private fun Char.isKorean(): Boolean {
        return 'ㄱ'.code <= code && code <= '힣'.code
    }

    private val chosungList = listOf(
        'ㄱ',
        'ㄲ',
        'ㄴ',
        'ㄷ',
        'ㄸ',
        'ㄹ',
        'ㅁ',
        'ㅂ',
        'ㅃ',
        'ㅅ',
        'ㅆ',
        'ㅇ',
        'ㅈ',
        'ㅉ',
        'ㅊ',
        'ㅋ',
        'ㅌ',
        'ㅍ',
        'ㅎ'
    )
}