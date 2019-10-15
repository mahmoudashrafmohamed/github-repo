package com.dev.mahmoud_ashraf.github_repo.presentation.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.mahmoud_ashraf.github_repo.R
import com.dev.mahmoud_ashraf.github_repo.domain.NetworkState
import com.dev.mahmoud_ashraf.github_repo.domain.Status
import kotlinx.android.synthetic.main.item_network_state.view.*

/**
 * Created by dev.mahmoud_ashraf on 10/15/2019.
 */

class NetworkStateViewHolder( view: View) :
    RecyclerView.ViewHolder(view) {


    fun bindTo(networkState: NetworkState?) {
        //error message
        itemView.error_message_tv.visibility = if (networkState?.message != null) View.VISIBLE else View.GONE

        if (networkState?.message != null)
            itemView.error_message_tv.text = networkState.message

        //loading and retry
        itemView.retry_loading_btn.visibility =
            if (networkState?.status == Status.FAILED) View.VISIBLE else View.GONE
        itemView.loading_progress_bar.visibility =
            if (networkState?.status == Status.RUNNING) View.VISIBLE else View.GONE
    }

    companion object {
        fun create(parent: ViewGroup): NetworkStateViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_network_state, parent, false)
            return NetworkStateViewHolder(view)
        }
    }

}