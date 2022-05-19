package com.test.com.repository

import com.test.com.network.RemoteDataSource
import javax.inject.Inject

class Repository @Inject constructor(
    remoteDataSource: RemoteDataSource
) {
    val remote = remoteDataSource
}