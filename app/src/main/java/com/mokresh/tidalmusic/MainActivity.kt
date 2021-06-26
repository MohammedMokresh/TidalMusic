package com.mokresh.tidalmusic

import android.os.Bundle
import androidx.paging.LoadState
import com.mokresh.tidalmusic.artist.view.ArtistsAdapter
import com.mokresh.tidalmusic.artist.data.ArtistsViewModel
import com.mokresh.tidalmusic.base.BaseActivity
import com.mokresh.tidalmusic.base.PagingLoadStateAdapter
import com.mokresh.tidalmusic.databinding.ActivityMainBinding
import com.mokresh.tidalmusic.utils.DebouncingQueryTextListener
import kotlinx.coroutines.flow.collectLatest


class MainActivity : BaseActivity<ActivityMainBinding,
        ArtistsViewModel>(R.layout.activity_main, ArtistsViewModel::class) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


}