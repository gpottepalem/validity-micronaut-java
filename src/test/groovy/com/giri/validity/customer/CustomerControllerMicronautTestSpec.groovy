package com.giri.validity.customer

import groovy.json.JsonSlurper
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject

/**
 * Specification for {@link CustomerController}
 *
 * @author gpottepalem
 * Created on Mar 26, 2019
 */
@MicronautTest
class CustomerControllerMicronautTestSpec extends Specification {
    @Inject @Client('/') HttpClient httpClient

    void "test customer endpoint response using Micronaut test"() {
        when:
        def jsonResponse = httpClient.toBlocking().retrieve('/customer')
        Map response = new JsonSlurper().parseText(jsonResponse)

        then:
        response.size() == 2
        response['potentialDuplicateCustomers'].size() == 8
        response['nonDuplicateCustomers'].size() == 90
    }

}
