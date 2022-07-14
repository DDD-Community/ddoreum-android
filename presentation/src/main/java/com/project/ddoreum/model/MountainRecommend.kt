package com.project.ddoreum.model

data class MountainRecommend(
    val id: Int,
    val image: String,
    val comment: String
){
    companion object {
        val defaultList = listOf(
            MountainRecommend(20000004, "http://www.forest.go.kr/newkfsweb/cmm/fms/getImage.do?fileSn=1&atchFileId=FILE_000000000424375", "소양호에서 폭포 따라 정상까지"),
            MountainRecommend(20000006, "http://www.forest.go.kr/newkfsweb/cmm/fms/getImage.do?fileSn=1&atchFileId=FILE_000000000424055", "약초 많은 태백산의 지붕"),
            MountainRecommend(20001037, "http://www.forest.go.kr/newkfsweb/cmm/fms/getImage.do?fileSn=1&atchFileId=FILE_000000000424295", "여름철에는 최고의 가족 휴양지"),
        )
    }
}