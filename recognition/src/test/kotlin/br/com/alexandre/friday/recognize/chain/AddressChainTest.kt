package br.com.alexandre.friday.recognize.chain

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AddressChainTest {
    private lateinit var addresChain: AddressChain

    @Before
    fun setUp() {
        this.addresChain = AddressChain()
    }

    @Test
    fun shouldSayTheCompleteAddress() {
        var request: RecognizerRequest = RecognizerRequest();
        request.name = "Alexandre"
        request.subLocality = "Vila Olímpia"
        request.locality = "São Paulo"
        request.thoroughfare = "Rua Casa do Ator"
        request.subThoroughfare = "1452"

        val response: MutableCollection<String> = addresChain.execute(request)

        assertEquals(1, response.size)
        assertEquals("Você está no bairro Vila Olímpia da cidade de São Paulo, endereço Rua Casa do Ator, número 1452", response.firstOrNull())
    }
}