package com.test.lifehack.ui.listcompany.view

import android.graphics.Bitmap
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.test.lifehack.logic.DisplayHelper
import com.test.lifehack.model.CompanyModel
import kotlinx.android.synthetic.main.view_item_company.view.*
import kotlin.math.ceil

class CompanyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun onBind(companyModel: CompanyModel) {
        Glide
            .with(itemView.context)
            .asBitmap()
            .load(companyModel.getImage())
            .addListener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return true

                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    resource?.apply {
                        val w = DisplayHelper.widthPixels / 2- DisplayHelper.dpToPx(8)
                        itemView._imgCompany.layoutParams.width = w
                        itemView._imgCompany.layoutParams.height =
                            ceil(height.toDouble() / (width.toDouble() / w)).toInt()

                    }
                    return false
                }

            })
            .into(itemView._imgCompany)
        itemView._titleCompany.text = companyModel.name

//        itemView._imgCompany.layoutParams.height
    }
}