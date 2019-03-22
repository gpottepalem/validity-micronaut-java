package com.giri.validity.customer;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A controller mapped to end-point: /customer.
 *
 * @author gpottepalem
 * Created on Mar 21, 2019
 */
@Controller("/customer")
public class CustomerController {

    private final CustomerProcessorService customerProcessorService;

    CustomerController(final CustomerProcessorService customerProcessorService) {
        this.customerProcessorService = customerProcessorService;
    }

    @Get("/")
    public Map<String, List<Customer>> list() {
        CustomerMatchResults results = null;
        try {
            results = customerProcessorService.processCustomerData("/normal.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, List<Customer>> customers = new HashMap<String, List<Customer>>();
        customers.put("potentialDuplicateCustomers", results.getPotentialDuplicateCustomers());
        customers.put("nonDuplicateCustomers", results.getNonDuplicates());

        return customers;
    }

}
