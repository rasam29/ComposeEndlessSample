package com.aparat.androidinterview.network

import arrow.retrofit.adapter.either.EitherCallAdapterFactory
import com.aparat.androidinterview.BuildConfig
import com.aparat.androidinterview.features.detail.service.DetailApi
import com.aparat.androidinterview.features.home.movies.service.MovieApi
import com.aparat.androidinterview.features.home.tvShows.service.TVShowApi
import com.aparat.androidinterview.features.search.service.SearchApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(EitherCallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().setLevel(
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    )

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        //TODO work around an Issue to determine the type of content based on the type from incoming json from server
//        .add(
//            PolymorphicJsonAdapterFactory.of(Content::class.java, TYPE)
//                .withSubtype(Content.MovieResponse::class.java, TYPE_MOVIE)
//                .withSubtype(Content.TVShowResponse::class.java, TYPE_TV_SHOWS)
//        )
        .addLast(KotlinJsonAdapterFactory()).build()

    @Provides
    fun provideAuthInterceptor(): AuthInterceptor = AuthInterceptor()

    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create()

    @Provides
    fun provideTvShowApi(retrofit: Retrofit): TVShowApi = retrofit.create()

    @Provides
    fun provideSearchApi(retrofit: Retrofit): SearchApi = retrofit.create()

    @Provides
    fun provideDetailApi(retrofit: Retrofit): DetailApi = retrofit.create()
}
