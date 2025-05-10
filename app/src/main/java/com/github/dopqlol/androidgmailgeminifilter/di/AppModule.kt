package com.github.dopqlol.androidgmailgeminifilter.di

import com.github.dopqlol.androidgmailgeminifilter.data.repository.MailRepository
import com.github.dopqlol.androidgmailgeminifilter.data.repository.MockMailRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindMailRepository(
        mockMailRepositoryImpl: MockMailRepositoryImpl
    ): MailRepository
}
