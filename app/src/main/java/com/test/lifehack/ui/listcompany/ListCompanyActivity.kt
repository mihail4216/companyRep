package com.test.lifehack.ui.listcompany

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.snackbar.Snackbar
import com.test.lifehack.R
import com.test.lifehack.model.CompanyModel
import com.test.lifehack.ui.company_info.CompanyInfoActivity
import com.test.lifehack.ui.listcompany.adapter.CompanyAdapter
import kotlinx.android.synthetic.main.company_list_activity.*

class ListCompanyActivity : MvpAppCompatActivity(), ListCompanyView {
    @InjectPresenter
    lateinit var mPresenter: ListCompanyPresenter

    override fun showCompanyList(listCompany: ArrayList<CompanyModel>) {
        (_listCompany.adapter as CompanyAdapter).addAllCompany(listCompany)
//        Toast.makeText(this, listCompany.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.company_list_activity)
    }

    override fun hideProgress() {
        _progressBar.visibility = View.GONE

    }

    override fun showError() {
        Snackbar.make(main, "Ошибка загрузки", Snackbar.LENGTH_INDEFINITE)
            .setAction("Повторить") {
                mPresenter.onClickTryAgain()
            }.show()
    }

    override fun showProgress() {
        _progressBar.visibility = View.VISIBLE
    }


    override fun initUi() {
        val adapter = CompanyAdapter()
        adapter.setOnClickCompanyListener {
            mPresenter.onClickCompany(it)
        }
        _listCompany.adapter = adapter
        _listCompany.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    override fun startInfoCompany(it: CompanyModel) {
        CompanyInfoActivity.start(this, it)
    }

}
