package com.bashirli.nextlvlart.view.mvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bashirli.nextlvlart.view.repo.FakeRepo
import com.bashirli.nextlvlart.view.MainCoroutineRule
import com.bashirli.nextlvlart.view.getOrAwaitValue
import com.bashirli.nextlvlart.view.util.Status
import com.google.common.truth.Truth.assertThat

import org.junit.Before
import org.junit.Rule
import org.junit.Test


class ArtViewModelTest {

    @get: Rule
    var instantTaskExecutorRule=InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule= MainCoroutineRule()



    private lateinit var viewModel:ArtMVVM

    @Before
    fun setup(){
        viewModel= ArtMVVM(FakeRepo())
    }

    @Test
    fun `insert art without name returns error`(){
        viewModel.makeArt("","test")
        val value = viewModel.insertMSG.getOrAwaitValue()
        assertThat(value.status).isEqualTo(Status.ERROR)


    }

    @Test
    fun `insert art without surname returns error`(){
        viewModel.makeArt("test","")
        val value = viewModel.insertMSG.getOrAwaitValue()
        assertThat(value.status).isEqualTo(Status.ERROR)


    }




}