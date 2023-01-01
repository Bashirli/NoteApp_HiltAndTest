package com.bashirli.nextlvlart.view.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.bashirli.nextlvlart.view.getOrAwaitValue
import com.bashirli.nextlvlart.view.model.Art
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class RoomDaoTest {
@get:Rule
var instantTaskExecutorRule=InstantTaskExecutorRule()

    @get:Rule
    var hiltRule=HiltAndroidRule(this)

    @Inject
    @Named("db")
    lateinit var roomDB: RoomDB

    private lateinit var roomDAO: RoomDAO


    @Before
    fun setup(){
        hiltRule.inject()
        roomDAO=roomDB.getDao()
    }


    @After
    fun stopdb(){
        roomDB.close()
    }

    @Test fun insertArt()= runBlocking{
        var art=Art("","s","null",1)
        roomDAO.insertArt(art)
        val list=roomDAO.observeArts().getOrAwaitValue()
        assertThat(list).contains(art)

    }
    @Test fun deleteArt()= runBlocking{
        var art=Art("","s","null",1)
        roomDAO.insertArt(art)
        roomDAO.deleteArt(art)
        val list=roomDAO.observeArts().getOrAwaitValue()
        assertThat(list).doesNotContain(art)

    }
}