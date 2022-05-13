package com.example.idus.di

import com.example.idus.data.datasource.DataSource
import com.example.idus.data.datasource.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {

    @Binds
    abstract fun bindRemoteDataSource(dataSource: RemoteDataSource): DataSource
}