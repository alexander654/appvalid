package com.alexander.appvalid.models

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class ArtistsRepoResult(
    val data: LiveData<PagedList<Artist>>,
    val errors: LiveData<String>
)