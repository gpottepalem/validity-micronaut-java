package com.giri.validity

import com.giri.validity.customer.CustomerMatchResults
import com.giri.validity.customer.CustomerProcessorService
import com.giri.validity.customer.MetaPhoneAlgorithm
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

/**
 * Specification for {@link com.giri.validity.customer.CustomerProcessorService}.
 *
 * Equivalent to {@link com.giri.validity.customer.CustomerProcessorServiceTests}. Just to demonstrate how readable a test specification is and how
 * powerful & simple it is writing specification for data-driven tests.
 *
 * @author gpottepalem
 * Created on Mar 21, 2019
 */
class CustomerProcessorServiceSpec extends Specification {

    @Subject
    CustomerProcessorService service = new CustomerProcessorService(new MetaPhoneAlgorithm())

    @Unroll
    void "Existing customer data file:#testFile contains #expectedRecords records"() {
        expect:
        service.loadCustomers(testFile).size() == expectedRecords

        where:
        testFile        || expectedRecords
        '/normal.csv'   || 106
        '/advanced.csv' || 109
    }

    void "When customer data file is processed, results get populated with data"() {
        when: 'customer data file is processed'
        CustomerMatchResults results = service.processCustomerData('/normal.csv')

        then: 'no exception occurs'
        noExceptionThrown()

        and: 'results contains customers'
        results.groupedCustomers.size() > 1
    }

    void "Non-existing customer data file should result in no customers in the result"() {
        when: 'non existing file is taken for processing'
        CustomerMatchResults results = service.processCustomerData('/not-found.csv')

        then: 'no exception occurs'
        noExceptionThrown()

        and: 'result should have 0 customers'
        results.groupedCustomers.size() == 0
    }
}
