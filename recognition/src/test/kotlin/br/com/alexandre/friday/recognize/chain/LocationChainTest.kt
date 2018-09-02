package br.com.alexandre.friday.recognize.chain

import br.com.alexandre.friday.recognize.RecognizerRequest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class LocationChainTest {

    private lateinit var locationChain: LocationChain

    @Before
    fun setUp() {
        this.locationChain = LocationChain()
    }

    @Test
    fun shouldSayTheCurrentLatitudeAndLongitude() {
        var request: RecognizerRequest = RecognizerRequest();
        request.name = "Alexandre"
        request.latitude = -23.45465
        request.longitude = -46.65656

        val response: MutableCollection<String> = locationChain.execute(request)

        assertEquals(1, response.size)
        assertEquals("Você está na latitude -23.45465 e longitude -46.65656", response.firstOrNull())
    }
}