package com.test.lifehack.ui.company_info

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.test.lifehack.model.CompanyInfoModel
import com.test.lifehack.ui.company_info.adapter.InfoLocation

@StateStrategyType(SkipStrategy::class)
interface InfoCompanyView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setImage(companyInfoModel: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setDescription(description: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setTitle(name: String)

    fun setPhone(numberPhone: String)
    fun setLocation(latitude: Float, longitude: Float)
    fun setSite(site: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun intiUi()

    fun startPhoneCall(phone: String)
    fun startMaps(location: InfoLocation)
    fun startBrowser(url: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun hideProgress()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showError()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProgress()
}