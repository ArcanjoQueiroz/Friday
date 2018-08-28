package br.com.alexandre.friday.recognize.chain

import br.com.alexandre.friday.recognize.RecognizerChain
import br.com.alexandre.friday.recognize.RecognizerRequest
import java.util.*

class GoodbyeChain: RecognizerChain() {
    override fun execute(request: RecognizerRequest?): MutableCollection<String> {
        var response = if (request?.name == null) {
            String.format("Adeus! Que você tenha uma ótima %s!", request?.name, partOfTheDay())
        } else {
            String.format("Adeus, %s! Que você tenha uma ótima %s!", request.name, partOfTheDay())
        }
        return Arrays.asList(response)
    }

    override fun apply(request: RecognizerRequest?): Boolean {
        return request?.q!!.contains("adeus") || request.q!!.contains("tchau")
    }

    private fun partOfTheDay(): String {
        when (PartOfTheDay[Calendar.getInstance()]) {
            GoodbyeChain.PartOfTheDay.MORNING -> return "manhã"
            GoodbyeChain.PartOfTheDay.AFTERNOON -> return "tarde"
            else -> return "noite"
        }
    }

    enum class PartOfTheDay {
        MORNING,
        AFTERNOON,
        EVENING,
        NIGHT;

        companion object {

            operator fun get(now: Calendar): PartOfTheDay {
                val hours = now.get(Calendar.HOUR_OF_DAY)
                if (hours in 5..11) {
                    return PartOfTheDay.MORNING
                } else if (hours in 12..16) {
                    return PartOfTheDay.AFTERNOON
                } else if (hours in 17..20) {
                    return PartOfTheDay.EVENING
                }
                return PartOfTheDay.NIGHT
            }
        }
    }
}