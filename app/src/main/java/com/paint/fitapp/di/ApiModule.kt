package com.paint.fitapp.di

import android.arch.persistence.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.paint.fitapp.App.Companion.ctx
import com.paint.fitapp.data.Api.Api
import com.paint.fitapp.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
class ApiModule {

    val appDatabase: AppDatabase

        @Provides
        @Singleton
        get() = Room.databaseBuilder(ctx,
            AppDatabase::class.java, "cdek_db")
            .allowMainThreadQueries()
            .build()

    @Provides
    @Singleton
    internal fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    //region Retrofit
    @Provides
    @Singleton
    internal fun provideRetrofit(converterFactory: Converter.Factory,
                                 callAdapterFactory: CallAdapter.Factory, client: OkHttpClient
    ): Retrofit {
        // здесь URL нельзя стянуть из префов, поэтому стоит заглушка.  Конечный url будет определяться в презентере
        return Retrofit.Builder().baseUrl("http://some.url")
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .client(client)
            .build()
    }
    //endregion

    // region GsonBuilder
    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }
    //endregion

    //region ConverterFactory
    @Provides
    @Singleton
    internal fun provideGsonConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }
    //endregion

    //region RxJava2CallAdapterFactory
    @Provides
    @Singleton
    internal fun provideRxJavaAdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }
    //endregion

    //region OkHttpClient
    @Provides
    @Singleton
    internal fun provideHttpClient(httpInterceptor: HttpLoggingInterceptor, certs: Array<TrustManager>,
                                   hostnameVerifier: HostnameVerifier
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)

        var ctx: SSLContext? = null
        try {
            ctx = SSLContext.getInstance("TLS")
            ctx!!.init(null, certs, SecureRandom())
        } catch (ex: java.security.GeneralSecurityException) {
        }

        try {
            val sslSocketFactory = ctx!!.socketFactory
            val trustManager = certs[0] as X509TrustManager
            builder.hostnameVerifier(hostnameVerifier)
            builder.sslSocketFactory(sslSocketFactory, trustManager)

        } catch (e: Exception) {
            e.message
        }

        builder.addInterceptor(httpInterceptor)
        builder.addInterceptor(NetworkConnectionInterceptor())

        return builder.build()
    }

    @Provides
    @Singleton
    internal fun provideTrustManager(trustManager: X509TrustManager): Array<TrustManager> {
        return arrayOf(trustManager)
    }

    @Provides
    @Singleton
    internal fun provideX509TrustManager(): X509TrustManager {
        return object : X509TrustManager {

            override fun getAcceptedIssuers(): Array<X509Certificate>? {
                return null
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<X509Certificate>,
                                            authType: String) {
            }

            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<X509Certificate>,
                                            authType: String) {
            }
        }
    }

    @Provides
    @Singleton
    internal fun provideHostnameVerifier(): HostnameVerifier {
        return HostnameVerifier { hostname, session ->
            if (!hostname.equals("www.asdasdad.com", ignoreCase = true)) {
                true
            } else {
                false
            }
        }
    }

    @Provides
    @Singleton
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
//        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    }
    //endregion

}