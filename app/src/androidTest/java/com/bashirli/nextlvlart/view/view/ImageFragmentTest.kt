package com.bashirli.nextlvlart.view.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.bashirli.nextlvlart.view.launchFragmentInHiltContainer
import com.bashirli.nextlvlart.view.mvvm.ArtMVVM
import com.bashirli.nextlvlart.view.repo.FakeRepoTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import com.bashirli.nextlvlart.R
import com.bashirli.nextlvlart.view.adapter.ImageAdapter
import com.bashirli.nextlvlart.view.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import javax.inject.Inject

@HiltAndroidTest
@ExperimentalCoroutinesApi
@MediumTest
class ImageFragmentTest {

@get:Rule
val instantTaskExecutorRule=InstantTaskExecutorRule()

    @get:Rule
    val hiltTest=HiltAndroidRule(this)

    @Inject
    lateinit var factory: ArtFragmentFactory


    @Before
    fun setup(){
        hiltTest.inject()
    }


    @Test
    fun selectImageTest(){
        val navController=Mockito.mock(NavController::class.java)
        val selectedImageUrl="test.te"
        val testViewModel=ArtMVVM(FakeRepoTest())

        launchFragmentInHiltContainer<ImagesFragment>(
            factory = factory
        ){
            Navigation.setViewNavController(requireView(),navController)
            viewModel=testViewModel
            imageAdapter.images= listOf(selectedImageUrl)
        }

        Espresso.onView(withId(com.bashirli.nextlvlart.R.id.imageRecycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ImageAdapter.ImageHolder>(
                0,click()
            )
        )
        Mockito.verify(navController).popBackStack()

        assertThat(testViewModel.selectedImageURL.getOrAwaitValue()).isEqualTo(selectedImageUrl)


    }

}