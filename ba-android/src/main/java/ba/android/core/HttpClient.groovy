package ba.android.core

import android.net.Uri
import android.net.http.AndroidHttpClient
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.json.internal.Charsets
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpUriRequest
import org.apache.http.entity.StringEntity

/**
 */
abstract class HttpClient {

    final Uri rootUri
    final String EMPTY = ''
    final String BA_CLIENT = 'ba-client'
    final String CONTENT_TYPE_JSON = 'application/json'

    HttpClient(Uri root) {
        rootUri = root
    }

    def httpGet(String uri = EMPTY) {
        return executeMethodAndReturnJson(new HttpGet(Uri.withAppendedPath(rootUri, uri).toString()))
    }

    def httpPost(String uri, Map params = [:]) {
        return executeMethodAndReturnJson(getJsonHttpPostRequestFrom(uri, params))
    }

    private executeMethodAndReturnJson(HttpUriRequest request) {
        return new JsonSlurper()
                .parse(AndroidHttpClient
                .newInstance(BA_CLIENT)
                .execute(request)
                .entity
                .content)
    }

    private HttpPost getJsonHttpPostRequestFrom(String uri, Map params) {
        HttpPost post = new HttpPost(rootUri.buildUpon().appendPath(uri).build().toString())
        StringEntity entity = new StringEntity(JsonOutput.toJson(params), Charsets.UTF_8.displayName())

        entity.setContentType(CONTENT_TYPE_JSON)
        post.setEntity(entity)

        return post
    }

}