package com.test.lifehack.ui.company_info.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.lifehack.R
import com.test.lifehack.ui.company_info.view.InfoCompanyViewHolder
import com.test.lifehack.ui.company_info.view.LocationInfoCompanyViewHolder
import com.test.lifehack.ui.company_info.view.PhoneInfoCompanyViewHolder
import com.test.lifehack.ui.company_info.view.SuiteInfoCompanyViewHolder

class InfoCompanyAdapter : RecyclerView.Adapter<InfoCompanyViewHolder>() {
    enum class Type {
        PHONE,
        LOCATION,
        SITE
    }

    private var listObj = arrayListOf<InfoObj>()

    var onClickPhone: (InfoPhone) -> Unit = {}
    var onClickMaps: (InfoLocation) -> Unit = {}
    var onClickSite: (InfoSite) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoCompanyViewHolder {
        return when (viewType) {
            Type.SITE.ordinal -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.suite_info_view, null)
                SuiteInfoCompanyViewHolder(view, onClickSite)
            }
            Type.PHONE.ordinal -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.phone_info_view, null)
                PhoneInfoCompanyViewHolder(view, onClickPhone)

            }
            else -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.location_info_view, null)
                LocationInfoCompanyViewHolder(view, onClickMaps)

            }
        }
    }

    override fun getItemCount(): Int {
        return listObj.size
    }

    override fun onBindViewHolder(holder: InfoCompanyViewHolder, position: Int) {
        holder.onBind(listObj[position])
    }

    override fun getItemViewType(position: Int): Int {
        return when (listObj[position]) {
            is InfoLocation -> {
                Type.LOCATION.ordinal
            }
            is InfoPhone -> {
                Type.PHONE.ordinal
            }
            else -> {
                Type.SITE.ordinal
            }
        }
    }

    fun addItem(info: InfoObj) {
        listObj.add(info)
        notifyItemChanged(listObj.size-1)
    }
}

abstract class InfoObj() {}

class InfoPhone(
    var phone: String
) : InfoObj() {

}

class InfoLocation(
    var lat: Float,
    var lon: Float
) : InfoObj() {

}

class InfoSite(
    var url: String
) : InfoObj()
