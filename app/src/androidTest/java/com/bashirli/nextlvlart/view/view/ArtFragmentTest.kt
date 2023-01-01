package com.bashirli.nextlvlart.view.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.bashirli.nextlvlart.view.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject
import com.bashirli.nextlvlart.R
import com.bashirli.nextlvlart.view.getOrAwaitValue
import com.bashirli.nextlvlart.view.model.Art
import com.bashirli.nextlvlart.view.mvvm.ArtMVVM
import com.bashirli.nextlvlart.view.repo.FakeRepoTest
import com.google.common.truth.Truth.assertThat


@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ArtFragmentTest {

@get:Rule
val instantTaskExecutorRule=InstantTaskExecutorRule()

    @get:Rule
    val hiltRule=HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun testNavFromArtToImage(){
        val navController=Mockito.mock(NavController::class.java)
        launchFragmentInHiltContainer<ArtFragment>(
            factory = fragmentFactory
        ){
            Navigation.setViewNavController(requireView(),navController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.imageView)).perform(click())

        Mockito.verify(navController).navigate(
            ArtFragmentDirections.actionArtFragmentToImagesFragment()
        )

    }



    @Test
    fun testSave(){
        val vm=ArtMVVM(FakeRepoTest())
        launchFragmentInHiltContainer<ArtFragment>(
            factory = fragmentFactory
        ){
            viewModel=vm

        }

        Espresso.onView(withId(R.id.editTextTextPersonName)).perform(replaceText("Mona"))
        Espresso.onView(withId(R.id.editTextTextPersonName2)).perform(replaceText("Lisa"))
        Espresso.onView(withId(R.id.button)).perform(click())

       assertThat( vm.artList.getOrAwaitValue()).contains(
          Art("Mona","Lisa","")
       )
    }


}