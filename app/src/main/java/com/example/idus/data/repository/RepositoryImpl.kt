package com.example.idus.data.repository

import com.example.idus.data.datasource.DataSource
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val remote: DataSource) : Repository {

}