package com.project.ddoreum.data.service

import com.project.ddoreum.data.model.ResMountainInfo
import retrofit2.Response
import retrofit2.http.GET

interface MountainService {

    @GET("/v1/mountains")
    suspend fun getAllMountainsInfo(): Response<ArrayList<ResMountainInfo>>

}