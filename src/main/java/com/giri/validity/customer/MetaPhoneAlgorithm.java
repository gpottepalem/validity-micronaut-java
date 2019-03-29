package com.giri.validity.customer;

import org.apache.commons.codec.language.Metaphone;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A concrete implementation of {@link Algorithm} using {@Metaphone}
 *
 * @author gpottepalem
 * Created on Mar 21, 2019
 */
@Singleton
public class MetaPhoneAlgorithm implements Algorithm {

    @Inject private Metaphone metaphone;

    @Override
    public String computePhoneticPhrase(Customer customer) {
        customer.setPhoneticPhrase(
            metaphone.metaphone(customer.getFirstName()) + metaphone.metaphone(customer.getLastName())
        );
        return customer.getPhoneticPhrase();
    }

    @Override
    public CustomerMatchResults processCustomers(List<Customer> customers) {
        CustomerMatchResults results = new CustomerMatchResults();
        if(customers == null) {
            return results;
        }

        String phoneticPhrase = null;
        Map<String, List<Customer>> groupedCustomers = results.getGroupedCustomers();
        for(Customer customer : customers) {
            phoneticPhrase = computePhoneticPhrase(customer);
            List<Customer> customersInThisGroup = groupedCustomers.get(phoneticPhrase);
            if (customersInThisGroup == null) {
                customersInThisGroup = new ArrayList<>();
                customersInThisGroup.add(customer);
                groupedCustomers.put(phoneticPhrase, customersInThisGroup);
            } else {
                customersInThisGroup.add(customer);
            }
        }

        return results;
    }
}
