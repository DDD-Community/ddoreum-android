package com.project.ddoreum.data.service

import com.project.ddoreum.data.model.ResMountainDetailInfo
import com.project.ddoreum.data.model.ResMountainInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MountainService {

    @GET("/v1/mountains")
    suspend fun getAllMountainsInfo(): Response<ArrayList<ResMountainInfo>>

    @GET("v1/mountain/{name}")
    suspend fun getMountainDetailInfo(
        @Path("name") name: String
    ): Response<ResMountainDetailInfo>
}