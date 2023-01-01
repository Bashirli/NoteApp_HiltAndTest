package com.bashirli.nextlvlart.view.appmodule

import android.content.Context
import androidx.room.Room
import com.bashirli.nextlvlart.view.room.RoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {
    @Provides
    @Named("db")
    fun injectInMemoryRoom(@ApplicationContext context: Context)
    =Room.inMemoryDatabaseBuilder(context,RoomDB::class.java)
        .allowMainThreadQueries().build()

}