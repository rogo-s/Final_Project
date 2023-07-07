package com.rogo.final_project.viewmodel

import com.rogo.final_project.network.RestfulApi
import com.rogo.final_project.view.model.data.flight.GetFlightResponse
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Call

class HomeViewModelTest {
    lateinit var service : RestfulApi

    @Before
    fun setUp(){
        service  = mockk()
    }

    @Test
    fun testRetriveData(): Unit = runBlocking {
        val responseRetrive = mockk<Call<GetFlightResponse>>()

        every {
            runBlocking {
                service.getFlights()
            }
        } returns responseRetrive
        val result = service.getFlights()

        verify {
            runBlocking {
                service.getFlights()
            }
        }
        assertEquals(result, responseRetrive)
    }
}