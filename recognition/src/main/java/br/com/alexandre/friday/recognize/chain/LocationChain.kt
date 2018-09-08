package br.com.alexandre.friday.recognize.chain

import br.com.alexandre.friday.recognize.RecognizerChain
import br.com.alexandre.friday.recognize.RecognizerRequest
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class LocationChain: RecognizerChain() {
    override fun execute(request: RecognizerRequest?): MutableCollection<String> {
        fun getDecimalFormat(): DecimalFormat {
            val decimalFormatSymbols = DecimalFormatSymbols()
            decimalFormatSymbols.decimalSeparator = '.'

            val decimalFormat = DecimalFormat()
            decimalFormat.decimalFormatSymbols = decimalFormatSymbols
            decimalFormat.maximumFractionDigits = 5
            return decimalFormat
        }

        val decimalFormat = getDecimalFormat()

        return Arrays.asList(String.format("Você está na latitude %s e longitude %s", decimalFormat.format(request?.latitude), decimalFormat.format(request?.longitude)))
    }

    override fun apply(request: RecognizerRequest?): Boolean {
        return request?.q!!.contains("onde") &&
                request.q!!.contains("eu") &&
                request.q!!.contains("estou")
    }
}