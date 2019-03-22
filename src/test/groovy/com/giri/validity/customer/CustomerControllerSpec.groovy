package com.giri.validity.customer

import groovy.json.JsonSlurper
import io.micronaut.context.ApplicationContext
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

/**
 * Specification for {@link CustomerController}
 *
 * @author gpottepalem
 * Created on Mar 21, 2019
 */
class CustomerControllerSpec extends Specification {
    @Shared @AutoCleanup EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)
    @Shared @AutoCleanup HttpClient httpClient = HttpClient.create(embeddedServer.URL)

    void "test customer endpoint response"() {
        when:
        def jsonResponse = httpClient.toBlocking().retrieve('/customer')
        Map response = new JsonSlurper().parseText(jsonResponse)

        then:
        response.size() == 2
        response['potentialDuplicateCustomers'].size() == 8
        response['nonDuplicateCustomers'].size() == 90
    }

}
