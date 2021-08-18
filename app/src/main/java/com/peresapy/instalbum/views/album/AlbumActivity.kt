package com.peresapy.instalbum.views.album

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.peresapy.instalbum.databinding.AlbumActivityBinding
import kotlinx.coroutines.flow.collect
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.di
import org.kodein.di.direct
import org.kodein.di.instance

class AlbumActivity : AppCompatActivity(), DIAware {

    override val di: DI by di()
    private lateinit var binding: AlbumActivityBinding
    private lateinit var adapter: AlbumRecyclerAdapter
    private val viewModel: AlbumViewModel by lazy {
        ViewModelProvider(this, direct.instance()).get(AlbumViewModel::class.java)
    }
    var albumId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AlbumActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        albumId = intent.getStringExtra("albumId")
        configureRecyclerView()
        if (albumId != null) {
            viewModel.loadAlbumImages(albumId!!)
        }
        observeViewModel()
    }

    private fun configureRecyclerView() {
        adapter = AlbumRecyclerAdapter().also {
            binding.recyclerAlbum.adapter = it
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                adapter.images = it.images
                binding.homeProgressBar.visibility = when (it.loading) {
                    true -> View.VISIBLE
                    false -> View.GONE
                }
            }
        }
    }

}