package com.alexander.appvalid.models

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class TracksRepoResult(
    val data: LiveData<PagedList<Track>>,
    val errors: LiveData<String>
)