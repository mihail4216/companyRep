package com.test.lifehack.ui.listcompany.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.lifehack.ui.listcompany.view.CompanyViewHolder
import com.test.lifehack.R
import com.test.lifehack.model.CompanyModel

class CompanyAdapter : RecyclerView.Adapter<CompanyViewHolder>() {
    private val arrayListCompany = arrayListOf<CompanyModel>()
    private var onClickCompanyListener: (CompanyModel) -> Unit = {}

    fun setOnClickCompanyListener(onClick: (CompanyModel) -> Unit) {
        onClickCompanyListener = onClick
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CompanyViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.view_item_company, p0, false)
        return CompanyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayListCompany.size
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.onBind(arrayListCompany[position])
        holder.itemView.setOnClickListener { onClickCompanyListener(arrayListCompany[position]) }
    }

    fun addCompany(commitModel: CompanyModel) {
        arrayListCompany.add(commitModel)
        notifyItemChanged(arrayListCompany.size - 1)
    }

    fun addAllCompany(commits: ArrayList<CompanyModel>) {
        arrayListCompany.addAll(commits)
        notifyDataSetChanged()
    }
}