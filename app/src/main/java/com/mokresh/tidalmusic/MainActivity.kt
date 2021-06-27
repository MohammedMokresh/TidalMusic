package com.mokresh.tidalmusic

import com.mokresh.tidalmusic.artist.data.ArtistsViewModel
import com.mokresh.tidalmusic.base.BaseActivity
import com.mokresh.tidalmusic.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding,
        ArtistsViewModel>(R.layout.activity_main, ArtistsViewModel::class)
