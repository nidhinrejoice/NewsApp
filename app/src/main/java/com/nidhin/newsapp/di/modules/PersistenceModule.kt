package com.nidhin.newsapp.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.nidhin.newsapp.persistance.SharedPrefsHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * This is a Dagger provider module
 */
@InstallIn(SingletonComponent::class)
@Module
class PersistenceModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            SharedPrefsHelper.PREF_NAME,
            Context.MODE_PRIVATE
        )
    }

//    @Singleton
//    @Provides
//    fun provideDb(
//        @ApplicationContext context: Context,
//        @Named("databaseName") dbName: String
//    ): AppDatabase {
//        return Room.databaseBuilder(
//            context.applicationContext,
//            AppDatabase::class.java,
//            dbName
//        ).fallbackToDestructiveMigration().build()
//    }
//
//    @Provides
//    @Named("databaseName")
//    fun provideDatabaseName(): String = "ngoairline.db"
//
//    @Singleton
//    @Provides
//    fun provideInventoryRepository(inventoryRepository: InventoryRepoImpl): InventoryRepository {
//        return inventoryRepository
//    }
//
//    @Provides
//    @Singleton
//    fun provideDatabaseManager(appDatabase: AppDatabase
//    ): DatabaseManager {
//        return DatabaseManager(appDatabase.inventoryDao(), appDatabase.operationDao())
//    }
//
//    @Singleton
//    @Provides
//    fun provideManifestService(manifestService: ManifestServiceImpl): ManifestService {
//        return manifestService
//    }


}