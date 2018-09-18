package com.neiljaywarner.jsonplaceholderscaffold.di

import android.app.Application
import android.arch.persistence.room.Room
import com.neiljaywarner.jsonplaceholderscaffold.db.MyDatabase
import com.neiljaywarner.jsonplaceholderscaffold.db.PhotoDao
import com.neiljaywarner.jsonplaceholderscaffold.network.ServiceGenerator
import com.neiljaywarner.jsonplaceholderscaffold.network.WebServiceApi
import com.neiljaywarner.jsonplaceholderscaffold.ui.photos.PhotoRepository
import com.neiljaywarner.jsonplaceholderscaffold.ui.photos.PhotosViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import org.koin.experimental.builder.create

//todo: can have a ViewModelsModule, RepositoriesModule, etc
val myModule : Module = module {
    viewModel { PhotosViewModel(get()) }
    single<WebServiceApi> { ServiceGenerator.createDeferredService(WebServiceApi::class.java) }
    single<MyDatabase> { getRoomDatabase(androidApplication())}
    single<PhotoDao> { get<MyDatabase>().photoDao() }
    //single { PhotoRepository(get(), get()) }
    // below is the same as above, e.g. DI all parameters
    single { create<PhotoRepository>() }

}

fun getRoomDatabase(application: Application) =
        Room.databaseBuilder(application, MyDatabase::class.java, "mydatabase.db")
                .fallbackToDestructiveMigration()
                .build()

