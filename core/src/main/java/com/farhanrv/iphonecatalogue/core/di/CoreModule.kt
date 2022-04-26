package com.farhanrv.iphonecatalogue.core.di

import androidx.room.Room
import com.farhanrv.iphonecatalogue.core.data.IphoneRepository
import com.farhanrv.iphonecatalogue.core.data.source.local.LocalDataSource
import com.farhanrv.iphonecatalogue.core.data.source.local.room.IphoneDatabase
import com.farhanrv.iphonecatalogue.core.data.source.remote.RemoteDataSource
import com.farhanrv.iphonecatalogue.core.data.source.remote.network.ApiService
import com.farhanrv.iphonecatalogue.core.domain.repository.IIphoneRepository
import com.farhanrv.iphonecatalogue.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<IphoneDatabase>().iphoneDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("farhan".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            IphoneDatabase::class.java,
            "IphoneList.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "iphone-list-api.herokuapp.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/M7eir9qCtRzt4w22FgQrxM9bMpn1tezOOtzblURVegE=")
            .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://iphone-list-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single { LocalDataSource(get()) }
    factory { AppExecutors() }
    single<IIphoneRepository> { IphoneRepository(get(), get(), get()) }
}