package com.connect.systems.ng.wikipedia.providers

import com.connect.systems.ng.wikipedia.models.Urls
import com.connect.systems.ng.wikipedia.models.WikiResult
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import java.io.Reader
import java.lang.Exception

class ArticleDataProvider {

    init {
        // Create User Agent to avoid spamming wikilpedia service
        FuelManager.instance.baseHeaders = mapOf("User-Agent" to "Pluralsight Wikipedia App")
    }

    fun search(term: String, skip: Int, take: Int, responseHandler: (result: WikiResult) -> Unit?) {
        Urls.getSearchUrl(term, skip, take).httpGet()
            .responseObject(WikipediaDataDeserializer()) { _, response, result ->
                if (response.statusCode != 200) {
                    throw Exception("Unable to get articles")
                }
                // destructure the result from
                // the http request made
                /**
                 * @see "https://kotlinlang.org/docs/reference/multi-declarations.html"
                 */
                val(data, _) = result
                // Call the responseHandler with the result
                // that has been destructured
                responseHandler.invoke(data as @ParameterName(name = "result") WikiResult)
            }
    }

    fun getRandom(take: Int, responseHandler: (result: WikiResult) -> Unit?) {
        Urls.getRandomUrl(take).httpGet()
            .responseObject(WikipediaDataDeserializer()) { _, response, result ->
                if (response.statusCode != 200) {
                    throw Exception("Unable to get articles").cause!!
                }
                val(data, _) = result
                responseHandler.invoke(data as @ParameterName(name = "result") WikiResult)
            }
    }

    /**
     * Used to deserialize response from Fuel
     */
    class WikipediaDataDeserializer : ResponseDeserializable<WikiResult> {

        override fun deserialize(reader: Reader): WikiResult? = Gson().fromJson(reader, WikiResult::class.java)
    }
}