package com.dev.mahmoud_ashraf.github_repo.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.mahmoud_ashraf.github_repo.R
import com.dev.mahmoud_ashraf.github_repo.base.ViewModelFactory
import com.dev.mahmoud_ashraf.github_repo.databinding.ActivityMainBinding
import com.dev.mahmoud_ashraf.github_repo.domain.NetworkState
import com.dev.mahmoud_ashraf.github_repo.presentation.viewmodel.MainViewModel
import com.dev.mahmoud_ashraf.github_repo.utils.EndlessRecyclerViewScrollListener
import com.dev.mahmoud_ashraf.github_repo.utils.toast
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject
import com.dev.mahmoud_ashraf.github_repo.presentation.adapter.ReposAdapter as ReposAdapter1

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ReposAdapter1


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        initAdapter()
        observeReposList()
        fetchRepos()
    }

    private fun initAdapter() {
        adapter = ReposAdapter1(ArrayList()) {
            toast("clicked :)")
        }
        binding.reposRv.adapter = adapter
        binding.reposRv.addOnScrollListener(object : EndlessRecyclerViewScrollListener(binding.reposRv.layoutManager
                as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                if (viewModel.isFirstLoad)return
                fetchRepos(page)
            }
        })


    }



    private fun observeReposList() {
        viewModel.reposLiveData.observe(this, Observer { moviesLiveData ->

            adapter.setNetworkState(moviesLiveData.networkState)

            when (moviesLiveData.networkState) {
                NetworkState.LOADED -> {
                    if (viewModel.isFirstLoad) viewModel.isFirstLoad = false
                    if (!moviesLiveData.repos.isNullOrEmpty())
                    adapter.add(moviesLiveData.repos)
                }
                NetworkState.LOADING -> Unit

                else -> {
                    if (viewModel.isFirstLoad) {
                        viewModel.isFirstLoad = false
                        viewModel.fetchCachedRepos()
                       toast(""+ moviesLiveData.networkState.message)
                    }
                }
            }
        })

    }

    private fun fetchRepos(pageNumber: Int = 1) {
        viewModel.fetchRepos(pageNumber)
    }






}
