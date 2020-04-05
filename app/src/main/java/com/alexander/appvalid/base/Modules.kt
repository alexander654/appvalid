package com.alexander.appvalid.base

import com.alexander.appvalid.activities.MainViewModel
import com.alexander.appvalid.adapters.TestAdapter
import com.alexander.appvalid.datasource.MusicDatabase
import com.alexander.appvalid.datasource.repository.ArtistsRepository
import com.alexander.appvalid.datasource.repository.cache.ArtistsLocalCache
import com.alexander.appvalid.network.ServiceArtists
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import java.util.concurrent.Executors

val databaseModule: Module = module {
    single { MusicDatabase.getDatabase(AppValid.instance) }
}

val artistDaoModule: Module = module {
    single { get<MusicDatabase>().artistDao() }
}

val artistCacheModule: Module = module {
    single { ArtistsLocalCache(get(), Executors.newSingleThreadExecutor()) }
}

val artistRepositoryModule: Module = module {
    single { ArtistsRepository(get(), get()) }
}

val artistServiceModule: Module = module {
    single { ServiceArtists() }
}

val adapterTest: Module = module {
    single { TestAdapter() }
}

val viewModelMainModule: Module = module {
    viewModel { MainViewModel(get()) }
}

val modules = listOf(
    databaseModule,
    artistDaoModule,
    artistCacheModule,
    artistRepositoryModule,
    artistServiceModule,
    adapterTest,
    viewModelMainModule
)