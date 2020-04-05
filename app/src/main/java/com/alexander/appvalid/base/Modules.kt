package com.alexander.appvalid.base

import com.alexander.appvalid.activities.MainViewModel
import com.alexander.appvalid.adapters.TestAdapter
import com.alexander.appvalid.adapters.TestAdapterTracks
import com.alexander.appvalid.datasource.MusicDatabase
import com.alexander.appvalid.datasource.repository.ArtistsRepository
import com.alexander.appvalid.datasource.repository.TracksRepository
import com.alexander.appvalid.datasource.repository.cache.ArtistsLocalCache
import com.alexander.appvalid.datasource.repository.cache.TracksLocalCache
import com.alexander.appvalid.network.ServiceArtists
import com.alexander.appvalid.network.ServiceTracks
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import java.util.concurrent.Executors

val databaseModule: Module = module {
    single { MusicDatabase.getDatabase(AppValid.instance) }
}

val tracksDaoModule: Module = module {
    single { get<MusicDatabase>().trackDao() }
}

val tracksCacheModule: Module = module {
    single { TracksLocalCache(get(), Executors.newSingleThreadExecutor()) }
}

val tracksRepositoryModule: Module = module {
    single { TracksRepository(get(), get()) }
}

val tracksServiceModule: Module = module {
    single { ServiceTracks() }
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

val adapterTracks: Module = module {
    single { TestAdapterTracks() }
}

val viewModelMainModule: Module = module {
    viewModel { MainViewModel(get(), get()) }
}

val modules = listOf(
    databaseModule,
    artistDaoModule,
    artistCacheModule,
    artistRepositoryModule,
    artistServiceModule,
    tracksDaoModule,
    tracksCacheModule,
    tracksRepositoryModule,
    tracksServiceModule,
    adapterTest,
    adapterTracks,
    viewModelMainModule
)