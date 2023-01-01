package com.bashirli.nextlvlart.view.appmodule

import android.content.Context
import androidx.room.Room
import com.bashirli.nextlvlart.R
import com.bashirli.nextlvlart.view.repo.ArtInterface
import com.bashirli.nextlvlart.view.repo.ArtRepo
import com.bashirli.nextlvlart.view.retrofit.RetrofitAPI
import com.bashirli.nextlvlart.view.room.RoomDAO
import com.bashirli.nextlvlart.view.room.RoomDB
import com.bashirli.nextlvlart.view.util.util.BASE_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

@Singleton
@Provides
fun injectRoomDatabase(@ApplicationContext context: Context) =
    Room.databaseBuilder(
        context,
        RoomDB::class.java,
        "DB"
    ).build()

    @Singleton
    @Provides
    fun injectRoomDao(database:RoomDB) = database.getDao()


    @Singleton
    @Provides
    fun injectRetrofitAPI():RetrofitAPI{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build().create(RetrofitAPI::class.java)
    }

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context)=
        Glide.with(context).setDefaultRequestOptions(RequestOptions().error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_background))

    @Singleton
    @Provides
    fun injectNormalRepo(dao:RoomDAO,api:RetrofitAPI)=ArtRepo(dao,api) as ArtInterface



}