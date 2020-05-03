package com.test.lifehack.ui.listcompany

import com.arellomobile.mvp.MvpView
import com.test.lifehack.model.CompanyModel

interface ListCompanyView : MvpView {
    fun showCompanyList(listCompany: ArrayList<CompanyModel>)
    fun initUi()
        fun startInfoCompany(it: CompanyModel)
        fun hideProgress()
        fun showError()
        fun showProgress()
}