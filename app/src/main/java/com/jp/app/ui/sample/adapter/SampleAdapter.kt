package com.jp.app.ui.sample.adapter

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.OnClick
import com.jp.app.R
import com.jp.app.R.id.card_view
import com.jp.app.R.id.sample_image
import com.jp.app.model.SampleView

class SampleAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var mList: MutableList<SampleView>

    private var mListener: SampleAdapterCallBack? = null

    interface SampleAdapterCallBack {
        fun sampleClicked(adapterPosition: Int)
    }

    fun SampleAdapter(samples: MutableList<SampleView>, callBack: SampleAdapterCallBack): ??? {
        mList = samples
        mListener = callBack
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
            holder.sample_title = sample.title
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



        fun loadImage(imagePath: String) {
            val uri = Uri.parse(imagePath)
            ImageHelper.loadImage(card_view, uri, sample_image, null)
        }

        @OnClick(R.id.card_view)
        internal fun onClick() {
            if (mListener != null) {
                mListener!!.sampleClicked(adapterPosition)
            }
        }
    }

}
