package com.test.com.fragments.detailedview

import android.app.Application
import com.test.com.base.BaseViewModel
import com.test.com.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailedViewViewModel @Inject constructor(
    application: Application,
    val repository: Repository
) : BaseViewModel(application) {
}