package com.test.lifehack.ui.company_info.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.test.lifehack.ui.company_info.adapter.InfoLocation
import com.test.lifehack.ui.company_info.adapter.InfoObj
import com.test.lifehack.ui.company_info.adapter.InfoPhone
import com.test.lifehack.ui.company_info.adapter.InfoSite
import kotlinx.android.synthetic.main.phone_info_view.view.*
import kotlinx.android.synthetic.main.suite_info_view.view.*

abstract class InfoCompanyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(model: InfoObj)
}

class PhoneInfoCompanyViewHolder(
    itemView: View,
    var onClickPhone: (InfoPhone) -> Unit
) : InfoCompanyViewHolder(itemView) {
    override fun onBind(model: InfoObj) {
        itemView._phone.text = (model as InfoPhone).phone
        itemView.setOnClickListener { onClickPhone(model) }
    }
}


class LocationInfoCompanyViewHolder(
    itemView: View,
    var onClickMaps: (InfoLocation) -> Unit
) : InfoCompanyViewHolder(itemView) {
    override fun onBind(model: InfoObj) {
        itemView.setOnClickListener { onClickMaps(model as InfoLocation) }
    }
}

class SuiteInfoCompanyViewHolder(
    itemView: View,
    var onClickSite: (InfoSite) -> Unit
) : InfoCompanyViewHolder(itemView) {
    override fun onBind(model: InfoObj) {
        itemView._site.text = (model as InfoSite).url
        itemView.setOnClickListener { onClickSite(model) }
    }
}