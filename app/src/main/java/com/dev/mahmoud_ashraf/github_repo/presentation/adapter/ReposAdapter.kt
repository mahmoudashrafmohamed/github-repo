package com.dev.mahmoud_ashraf.github_repo.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.mahmoud_ashraf.github_repo.R
import com.dev.mahmoud_ashraf.github_repo.data.model.RepoModel
import com.dev.mahmoud_ashraf.github_repo.domain.NetworkState
import com.dev.mahmoud_ashraf.github_repo.presentation.viewholder.NetworkStateViewHolder
import com.dev.mahmoud_ashraf.github_repo.presentation.viewholder.RepoViewHolder

/**
 * Created by dev.mahmoud_ashraf on 10/15/2019.
 */
class ReposAdapter(private var reposList: ArrayList<RepoModel>,
                   private val itemClickedCallback: (RepoModel?) -> Unit)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var networkState: NetworkState? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.repo_list_item ->  RepoViewHolder.create(parent, itemClickedCallback)
            R.layout.item_network_state -> NetworkStateViewHolder.create(parent)
            else -> throw IllegalArgumentException("unknown view type")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.repo_list_item ->    (holder as RepoViewHolder).bindTo(reposList[position])
            R.layout.item_network_state -> (holder as NetworkStateViewHolder).bindTo(networkState)
        }
    }

    override fun getItemCount() = reposList.size + if (hasExtraRow()) 1 else 0


    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.item_network_state
        } else {
            R.layout.repo_list_item
        }
    }


    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState != NetworkState.LOADED
    }

    fun setNetworkState(newNetworkState: NetworkState?) {
        if (reposList != null) {
           if (reposList!!.size >= 0) {
                val previousState = this.networkState
                val hadExtraRow = hasExtraRow()
                this.networkState = newNetworkState
                val hasExtraRow = hasExtraRow()
                if (hadExtraRow != hasExtraRow) {
                    if (hadExtraRow) {
                        notifyItemRemoved(itemCount)
                    } else {
                        notifyItemInserted(itemCount)
                    }
                } else if (hasExtraRow && previousState !== newNetworkState) {
                    notifyItemChanged(itemCount - 1)
                }
            }
        }
    }

    fun getRepos() = reposList

    fun add(items: List<RepoModel>?) {
        items?.let {
            reposList.addAll(it)
            notifyDataSetChanged()
        }
    }

    fun clear() {
        reposList.clear()
        notifyDataSetChanged()
    }
}