package com.test.lifehack.ui.listcompany

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.test.lifehack.model.CompanyModel

@StateStrategyType(SkipStrategy::class)
interface ListCompanyView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showCompanyList(listCompany: ArrayList<CompanyModel>)

    @StateStrategyType(SingleStateStrategy::class)
    fun initUi()

    fun startInfoCompany(it: CompanyModel)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun hideProgress()
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showError()
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProgress()
}