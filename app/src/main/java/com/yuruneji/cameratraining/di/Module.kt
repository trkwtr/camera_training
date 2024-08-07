package com.yuruneji.cameratraining.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yuruneji.cameratraining.common.Constants
import com.yuruneji.cameratraining.data.remote.AppService
import com.yuruneji.cameratraining.data.repository.AppRepositoryImpl
import com.yuruneji.cameratraining.domain.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author toru
 * @version 1.0
 */
@Module
@InstallIn(SingletonComponent::class)
object Module {

    // @Provides
    // @Singleton
    // fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
    //     Room.databaseBuilder(context, AppDatabase::class.java, name = "app_db").build()

    // @Provides
    // @Singleton
    // fun provideUserDao(db: AppDatabase) = db.userDao()

    // @Provides
    // @Singleton
    // fun provideAppSettingDao(db: AppDatabase) = db.appSettingDao()

    // @Provides
    // @Singleton
    // fun provideAppSettingRepository(
    //     appSettingDao: AppSettingDao,
    // ): AppSettingRepository {
    //     return AppSettingRepositoryImpl(appSettingDao)
    // }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(90, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideAppService(okHttpClient: OkHttpClient, moshi: Moshi): AppService =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                MoshiConverterFactory.create(moshi)
            ).build().create(AppService::class.java)

    @Provides
    @Singleton
    fun provideAppRepository(api: AppService): AppRepository = AppRepositoryImpl(api)


    // @Provides
    // @Singleton
    // fun provideFaceRectView(@ApplicationContext context: Context): FaceRectView =
    //     FaceRectView(context)


    // @Provides
    // @Singleton
    // fun provideDataProvider(@ApplicationContext context: Context): DataProvider {
    //     return DataProvider(context)
    // }
    //
    // @Provides
    // @Singleton
    // fun provideDataStoreWrapper(@ApplicationContext context: Context): DataStoreWrapper {
    //     return DataStoreWrapper(context, "settings")
    // }

}
