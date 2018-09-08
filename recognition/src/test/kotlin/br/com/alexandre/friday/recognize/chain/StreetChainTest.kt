package br.com.alexandre.friday.recognize.chain

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class StreetChainTest {

    private lateinit var streetChain: StreetChain

    @Before
    fun setUp() {
        this.streetChain = StreetChain()
    }

    @Test
    fun shouldSayTheCurrentStreetAndNumber() {
        var request: RecognizerRequest = RecognizerRequest();
        request.name = "Alexandre"
        request.subLocality = "Vila Olímpia"
        request.locality = "São Paulo"
        request.thoroughfare = "Rua Casa do Ator"
        request.subThoroughfare = "1452"

        val response: MutableCollection<String> = streetChain.execute(request)

        assertEquals(1, response.size)
        assertEquals("Você está na Rua Casa do Ator, número 1452", response.firstOrNull())
    }

    @Test
    fun shouldSayOnlyTheCurrentStreet() {
        var request: RecognizerRequest = RecognizerRequest();
        request.name = "Alexandre"
        request.subLocality = "Santa Cruz"
        request.locality = "São Paulo"
        request.thoroughfare = "Rua Loefgren"

        val response: MutableCollection<String> = streetChain.execute(request)

        assertEquals(1, response.size)
        assertEquals("Você está na Rua Loefgren", response.firstOrNull())
    }
}