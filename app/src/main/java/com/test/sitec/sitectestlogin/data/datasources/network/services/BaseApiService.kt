package com.test.sitec.sitectestlogin.data.datasources.network.services

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.GsonBuilder
import com.test.sitec.sitectestlogin.common.API_BASE_URL
import com.test.sitec.sitectestlogin.common.API_PASSWORD
import com.test.sitec.sitectestlogin.common.API_USER_NAME
import com.test.sitec.sitectestlogin.common.App
import com.test.sitec.sitectestlogin.common.utils.AlertUtils
import com.test.sitec.sitectestlogin.data.datasources.network.interfaces.BaseApiInterface
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


class BaseApiService {

    private var apiInterface: BaseApiInterface? = null
    private val httpInterceptor = HttpLoggingInterceptor()
    private var retrofit = buildClient()

    private fun buildClient(): Retrofit? {
        httpInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        if (retrofit == null) {
            val client: OkHttpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .ignoreAllSSLErrors()
                .addInterceptor(httpInterceptor)
                .addInterceptor(BasicAuthInterceptor(API_USER_NAME, API_PASSWORD))
                .build()

            val gson = GsonBuilder()
                .setLenient()
                .create()

            retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit
    }

    fun getBaseApiService(): BaseApiInterface {
        if (apiInterface == null) {
            apiInterface =
                retrofit!!.create(BaseApiInterface::class.java)
        }
        return apiInterface!!
    }


    inner class BasicAuthInterceptor(user: String?, password: String?) :
        Interceptor {
        private val credentials: String

        init {
            credentials = Credentials.basic(user!!, password!!)
        }

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request: Request = chain.request()
            val authenticatedRequest = request.newBuilder()
                .header("Authorization", credentials).build()
            return chain.proceed(authenticatedRequest)
        }
    }
}

fun OkHttpClient.Builder.ignoreAllSSLErrors(): OkHttpClient.Builder {
    val naiveTrustManager = object : X509TrustManager {
        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) = Unit
        override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) = Unit
    }

    val insecureSocketFactory = SSLContext.getInstance("TLSv1.2").apply {
        val trustAllCerts = arrayOf<TrustManager>(naiveTrustManager)
        init(null, trustAllCerts, SecureRandom())
    }.socketFactory

    sslSocketFactory(insecureSocketFactory, naiveTrustManager)
    hostnameVerifier(HostnameVerifier { _, _ -> true })
    return this
}