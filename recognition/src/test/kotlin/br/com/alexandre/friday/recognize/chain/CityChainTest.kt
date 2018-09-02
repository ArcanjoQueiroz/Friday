package br.com.alexandre.friday.recognize.chain

import br.com.alexandre.friday.recognize.RecognizerRequest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CityChainTest {

    private lateinit var cityChain: CityChain

    @Before
    fun setUp() {
        this.cityChain = CityChain()
    }

    @Test
    fun shouldSayTheCurrentCity() {
        var request: RecognizerRequest = RecognizerRequest();
        request.name = "Alexandre"
        request.subLocality = "Vila Olímpia"
        request.locality = "São Paulo"
        request.thoroughfare = "Rua Casa do Ator"
        request.subThoroughfare = "1452"

        val response: MutableCollection<String> = cityChain.execute(request)

        assertEquals(1, response.size)
        assertEquals("Você está na cidade de São Paulo", response.firstOrNull())
    }
}