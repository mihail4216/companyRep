package com.test.lifehack.ui.company_info

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.test.lifehack.R
import com.test.lifehack.model.CompanyInfoModel
import com.test.lifehack.model.CompanyModel
import com.test.lifehack.ui.company_info.adapter.InfoCompanyAdapter
import com.test.lifehack.ui.company_info.adapter.InfoLocation
import com.test.lifehack.ui.company_info.adapter.InfoPhone
import com.test.lifehack.ui.company_info.adapter.InfoSite
import kotlinx.android.synthetic.main.company_info_activity.*

//class CompanyInfoActivity : AppCompatActivity(), InfoCompanyView {
        class CompanyInfoActivity : MvpAppCompatActivity(), InfoCompanyView {
    companion object {
        private const val EXTRA_COMPANY = "EXTRA_COMPANY"
        fun start(activity: Activity, it: CompanyModel) {
            val intent = Intent(activity, CompanyInfoActivity::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()
            }
            intent.putExtra(EXTRA_COMPANY, it)
            activity.startActivity(intent)
        }
    }

    @InjectPresenter
    lateinit var mPresenter: InfoCompanyPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.company_info_activity)
        var company = intent.getSerializableExtra(EXTRA_COMPANY) as CompanyModel?
        mPresenter.init(company)
    }

    override fun intiUi() {
        val adapter = InfoCompanyAdapter()
        adapter.onClickMaps = {
            mPresenter.onClickInfoLocation(it)
        }
        adapter.onClickPhone = {
            mPresenter.onClickInfoPhone(it)
        }
        adapter.onClickSite = {
            mPresenter.onClickInfoSite(it)

        }
        _listInfo.adapter = adapter
        _listInfo.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun startPhoneCall(phone: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phone")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    override fun startMaps(location: InfoLocation) {
        val geoLocation = Uri.parse("geo:${location.lat},${location.lon}")
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = geoLocation
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    override fun startBrowser(url: String) {
        val webpage: Uri = Uri.parse("http:$url")
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    override fun hideProgress() {
        _progressBar.visibility = View.GONE

    }

    override fun showError() {
        Snackbar.make(_nested, "Ошибка загрузки", Snackbar.LENGTH_INDEFINITE)
            .setAction("Повторить") {
                mPresenter.onClickTryAgain()
            }.show()
    }

    override fun showProgress() {
        _progressBar.visibility = View.VISIBLE
    }


    override fun setImage(companyInfoModel: String) {
        Glide.with(this).load(companyInfoModel).into(_imgCompany)
    }

    override fun setDescription(description: String) {
        _description.text = description
    }

    override fun setPhone(numberPhone: String) {
        (_listInfo.adapter as InfoCompanyAdapter).addItem(InfoPhone(numberPhone))
    }

    override fun setLocation(latitude: Float, longitude: Float) {
        (_listInfo.adapter as InfoCompanyAdapter).addItem(InfoLocation(latitude, longitude))

    }

    override fun setSite(site: String) {
        (_listInfo.adapter as InfoCompanyAdapter).addItem(InfoSite(site))
    }

    override fun setTitle(name: String) {
        title = name
    }


}