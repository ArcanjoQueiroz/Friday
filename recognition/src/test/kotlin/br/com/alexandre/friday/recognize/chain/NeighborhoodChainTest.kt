package br.com.alexandre.friday.recognize.chain

import br.com.alexandre.friday.recognize.RecognizerRequest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class NeighborhoodChainTest {

    private lateinit var neighborhoodChain: NeighborhoodChain

    @Before
    fun setUp() {
        this.neighborhoodChain = NeighborhoodChain()
    }

    @Test
    fun shouldSayTheCurrentNeighborhood() {
        var request: RecognizerRequest = RecognizerRequest();
        request.name = "Alexandre"
        request.subLocality = "Vila Olímpia"
        request.locality = "São Paulo"
        request.thoroughfare = "Rua Casa do Ator"
        request.subThoroughfare = "1452"

        val response: MutableCollection<String> = neighborhoodChain.execute(request)

        assertEquals(1, response.size)
        assertEquals("Você está no bairro Vila Olímpia", response.firstOrNull())

    }
}