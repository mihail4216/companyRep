package com.test.lifehack.logic.network

import com.test.lifehack.model.CompanyInfoModel
import com.test.lifehack.model.CompanyModel
import com.test.lifehack.model.ResponseCompanyInfo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiManager {

    @GET("/test_task/test.php")
    fun getListCompany(): Observable<ArrayList<CompanyModel>>

    @GET("/test_task/test.php")
    fun getInfoCompany(@Query("id") id: Int): Observable<ArrayList<CompanyInfoModel>>
}