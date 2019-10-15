package com.dev.mahmoud_ashraf.github_repo.presentation.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dev.mahmoud_ashraf.github_repo.R
import com.dev.mahmoud_ashraf.github_repo.data.model.RepoModel
import com.dev.mahmoud_ashraf.github_repo.databinding.RepoListItemBinding

/**
 * Created by dev.mahmoud_ashraf on 10/15/2019.
 */

class RepoViewHolder(private val binding: RepoListItemBinding, private val itemClickedCallback: (RepoModel?) -> Unit)
    : RecyclerView.ViewHolder(binding.root) {

    var repo : RepoModel ?= null
    init {
        binding.root.setOnClickListener {
            itemClickedCallback.invoke(repo)
        }
    }

    fun bindTo( repo : RepoModel?) {
        this.repo = repo
        binding.repo = repo
        binding.executePendingBindings()
    }

    companion object {

        fun create(parent: ViewGroup, itemClickedCallback: (RepoModel?) -> Unit): RepoViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding: RepoListItemBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.repo_list_item, parent, false)
            return RepoViewHolder(binding, itemClickedCallback)
        }
    }

}