package br.com.alexandre.friday.recognize

import java.io.Serializable

class RecognizerRequest:Serializable {
    var q: String? = null
    var name: String? = null
    var latitude: Double? = null
    var longitude: Double? = null
    var locality: String? = null
    var subLocality: String? = null
    var thoroughfare: String? = null
    var subThoroughfare: String? = null
}