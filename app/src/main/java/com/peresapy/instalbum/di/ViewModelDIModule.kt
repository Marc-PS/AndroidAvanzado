package com.peresapy.instalbum.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.peresapy.instalbum.views.album.AlbumViewModel
import com.peresapy.instalbum.views.top.TopViewModel
import org.kodein.di.*
import org.kodein.type.erased

object ViewModelDIModule : DIBaseModule("ViewModelDIModule") {

    override val builder: DI.Builder.() -> Unit = {
        bind<ViewModelProvider.Factory>() with singleton {
            DIViewModelFactory(di)
        }

        bind<TopViewModel>() with provider { TopViewModel(instance(), instance()) }

        bind<AlbumViewModel>() with provider { AlbumViewModel(instance()) }
    }

    class DIViewModelFactory(private val di: DI) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return di.direct.Instance(erased(modelClass))
        }
    }
}