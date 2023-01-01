package com.bashirli.nextlvlart.view.view

import com.bashirli.nextlvlart.R
import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.bashirli.nextlvlart.view.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject


@MediumTest
@HiltAndroidTest
class MainFragmentTest {



    @get:Rule
    val hiltRule=HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory



    @Before
    fun setup(){
        hiltRule.inject()

    }

    @Test
    fun testMainFragmentToArtFragment(){
        val navController=Mockito.mock(NavController::class.java)
        launchFragmentInHiltContainer<MainFragment>(
            factory = fragmentFactory
        ){
            Navigation.setViewNavController(requireView(),navController)
        }
        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(click())
        Mockito.verify(navController).navigate(
            MainFragmentDirections.actionMainFragmentToArtFragment()
        )


    }




}