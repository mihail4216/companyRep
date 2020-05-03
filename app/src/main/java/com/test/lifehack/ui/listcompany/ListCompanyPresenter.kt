package com.test.lifehack.ui.listcompany

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.test.lifehack.App
import com.test.lifehack.model.CompanyModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@InjectViewState
class ListCompanyPresenter:MvpPresenter<ListCompanyView>() {

    private var disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initUi()
        loadCompanyList()
    }

    private fun loadCompanyList(){
        viewState.showProgress()
        disposables.add(App.instance.API.getListCompany()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.hideProgress()
                viewState.showCompanyList(it)
            },{
                viewState.hideProgress()
                viewState.showError()
                it.printStackTrace()
            }))
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    fun onClickCompany(it: CompanyModel) {
        viewState.startInfoCompany(it)
    }

    fun onClickTryAgain() {
        loadCompanyList()
    }

}