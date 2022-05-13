package com.example.idus.data.datasource

import com.example.idus.data.api.Service
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val service: Service) : DataSource {
}