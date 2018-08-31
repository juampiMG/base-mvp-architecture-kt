package com.jp.app.ui.sample.adapter

import android.net.Uri
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.OnClick
import com.jp.app.R
import com.jp.app.model.SampleView
import com.jp.app.utils.ImageHelper

class SampleAdapter constructor(samples: MutableList<SampleView>, callBack: SampleAdapterCallBack) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mList: MutableList<SampleView> = samples

    private var mListener: SampleAdapterCallBack? = callBack

    interface SampleAdapterCallBack {
        fun sampleClicked(adapterPosition: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.sample_component, parent, false)
        return ItemViewHolder(v)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val sample = mList[position]
        drawSample(holder as ItemViewHolder, sample)
    }

    private fun drawSample(holder: ItemViewHolder, sample: SampleView) {
        if (!sample.title.isNullOrBlank()) {
            holder.textView.text = sample.title
        }

        if (!sample.urlLogo.isNullOrBlank()) {
            holder.loadImage(sample.urlLogo!!)
        }
    }


    fun addSamples(newsamples: List<SampleView>) {
        mList.addAll(newsamples)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textView: AppCompatTextView = itemView.findViewById(R.id.sample_title)

        var imageView: AppCompatImageView = itemView.findViewById(R.id.sample_image)


        fun loadImage(imagePath: String) {
            val uri = Uri.parse(imagePath)
            ImageHelper.loadImage(itemView.context, uri, imageView)
        }

        @OnClick(R.id.card_view)
        internal fun onClick() {
            if (mListener != null) {
                mListener!!.sampleClicked(adapterPosition)
            }
        }
    }

}
