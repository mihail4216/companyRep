package com.test.lifehack.ui.company_info

import com.arellomobile.mvp.MvpView
import com.test.lifehack.model.CompanyInfoModel
import com.test.lifehack.ui.company_info.adapter.InfoLocation

interface InfoCompanyView : MvpView {
    fun setImage(companyInfoModel: String)
    fun setDescription(description: String)
    fun setTitle(name: String)
    fun setPhone(numberPhone: String)
    fun setLocation(latitude: Float, longitude: Float)
    fun setSite(site: String)
    fun intiUi()
    fun startPhoneCall(phone: String)
    fun startMaps(location: InfoLocation)
    fun startBrowser(url: String)
    fun hideProgress()
    fun showError()
    fun showProgress()
}