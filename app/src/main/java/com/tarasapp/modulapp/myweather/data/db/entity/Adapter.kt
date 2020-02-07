package com.tarasapp.modulapp.myweather.data.db.entity

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.View.OnClickListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.tarasapp.modulapp.myweather.R

class Adapter(private val interaction: Interaction? = null) :
    ListAdapter<Request, Adapter.Holder>(RequestDC()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_main, parent, false), interaction
    )

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(getItem(position))

    fun swapData(data: List<Request>) {
        submitList(data.toMutableList())
    }

    inner class Holder(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView), OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            if (adapterPosition == RecyclerView.NO_POSITION) return

            val clicked = getItem(adapterPosition)
        }

        fun bind(item: Request) = with(itemView) {

        }
    }

    interface Interaction {

    }

    private class RequestDC : DiffUtil.ItemCallback<Request>() {
        override fun areItemsTheSame(
            oldItem: Request,
            newItem: Request
        ): Boolean {
            TODO(
                "not implemented"
            )
        }

        override fun areContentsTheSame(
            oldItem: Request,
            newItem: Request
        ): Boolean {
            TODO(
                "not implemented"
            )
        }
    }
}