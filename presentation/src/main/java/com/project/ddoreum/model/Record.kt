package com.project.ddoreum.model

import androidx.annotation.DrawableRes
import com.project.ddoreum.R

data class Record(
    val id: Int,
    val userImage: String? = null,
    val userName: String,
    val date: String,
    val isCertified: Boolean,
    val mountainName: String,
    @DrawableRes
    val image: Int,
    val text: String,

    ) {
    companion object {
        val defaultList = listOf(
            Record(
                id = 0,
                userName = "날듀람쥐",
                date = "2022.05.28",
                isCertified = false,
                mountainName = "관악산",
                image = R.drawable.image_record,
                text = "1시간 18분 컷. 산이 한 번 정비가 되면서 계단이 많이 깔렸다. 원래 관악산은 돌 밟는 맛에 타는 건데... 기록은 잘 나왔지만 아쉬운 산행이었다. 다음엔 천마산으로 간다."
            ),
            Record(
                id = 1,
                userName = "날듀람쥐",
                date = "2022.05.28",
                isCertified = true,
                mountainName = "관악산",
                image = R.drawable.image_record,
                text = "1시간 18분 컷. 산이 한 번 정비가 되면서 계단이 많이 깔렸다. 원래 관악산은 돌 밟는 맛에 타는 건데... 기록은 잘 나왔지만 아쉬운 산행이었다. 다음엔 천마산으로 간다."
            ),
            Record(
                id = 3,
                userName = "날듀람쥐",
                date = "2022.05.28",
                isCertified = true,
                mountainName = "관악산",
                image = R.drawable.image_record,
                text = "1시간 18분 컷. 산이 한 번 정비가 되면서 계단이 많이 깔렸다. 원래 관악산은 돌 밟는 맛에 타는 건데... 기록은 잘 나왔지만 아쉬운 산행이었다. 다음엔 천마산으로 간다."
            ),
            Record(
                id = 4,
                userName = "날듀람쥐",
                date = "2022.05.28",
                isCertified = true,
                mountainName = "관악산",
                image = R.drawable.image_record,
                text = "1시간 18분 컷. 산이 한 번 정비가 되면서 계단이 많이 깔렸다. 원래 관악산은 돌 밟는 맛에 타는 건데... 기록은 잘 나왔지만 아쉬운 산행이었다. 다음엔 천마산으로 간다."
            ),
        )
    }
}