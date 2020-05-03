package com.test.lifehack.ui.company_info

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.test.lifehack.App
import com.test.lifehack.model.CompanyInfoModel
import com.test.lifehack.model.CompanyModel
import com.test.lifehack.ui.company_info.adapter.InfoLocation
import com.test.lifehack.ui.company_info.adapter.InfoPhone
import com.test.lifehack.ui.company_info.adapter.InfoSite
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@InjectViewState
class InfoCompanyPresenter : MvpPresenter<InfoCompanyView>() {
    private var disposables = CompositeDisposable()
    private lateinit var companyModel: CompanyModel

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.intiUi()
    }

    fun init(company: CompanyModel?) {
        viewState.showProgress()
        company?.apply {
            companyModel = company
            disposables.add(
                App.instance.API.getInfoCompany(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        viewState.hideProgress()
                        if (!it.isNullOrEmpty())
                            showInfo(it[0])
                        else
                            viewState.showError()

                    }, {
                        viewState.showError()
                        it.printStackTrace()
                    })
            )
        }
    }

    private fun showInfo(model: CompanyInfoModel?) {
        model?.apply {
            viewState.setImage(getImage())
            if (!description.isNullOrEmpty())
                viewState.setDescription(description)
            viewState.setTitle(name)
            if (!numberPhone.isNullOrEmpty())
                viewState.setPhone(numberPhone)
            if (latitude != 0f && longitude != 0f)
                viewState.setLocation(latitude, longitude)
            if (!site.isNullOrEmpty())
                viewState.setSite(site)


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    fun onClickInfoLocation(location: InfoLocation) {
        viewState.startMaps(location)
    }

    fun onClickInfoPhone(phone: InfoPhone) {
        viewState.startPhoneCall(phone.phone)
    }

    fun onClickInfoSite(site: InfoSite) {
        viewState.startBrowser(site.url)
    }

    fun onClickTryAgain() {
        init(companyModel)
    }
}