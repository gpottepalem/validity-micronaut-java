package com.giri.validity.customer;

import com.giri.validity.customer.CustomerMatchResults;
import com.giri.validity.customer.CustomerProcessorService;
import com.giri.validity.customer.MetaPhoneAlgorithm;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * A Junit test case for {@link CustomerProcessorService}
 *
 * @author gpottepalem
 * Created on Mar 21, 2019
 */
public class CustomerProcessorServiceTests {

    private CustomerProcessorService service = new CustomerProcessorService(new MetaPhoneAlgorithm());

    @Test
    public void normalDataFileLoadedShouldHaveExpectedNumberOfCustomers() {
        try {
            assertEquals(service.loadCustomers("/normal.csv").size(), 106);
        } catch (Exception e) {
            assertThat(e, null);
        }
    }

    @Test
    public void advancedDataFileLoadedShouldHaveExpectedNumberOfCustomers() {
        try {
            assertEquals(service.loadCustomers("/advanced.csv").size(), 109);
        } catch (Exception e) {
            assertThat(e, null);
        }
    }

    @Test
    public void existingCustomerDataFileProcessedShouldResultWithNoExceptionAndExpectedCustomers() {
        CustomerMatchResults results = null;
        try {
            results = service.processCustomerData("/normal.csv");
        } catch (Exception e) {
            assertThat(e, null);
        }
        assertEquals(results.getGroupedCustomers().size() > 0, true);
        assertEquals(results.getPotentialDuplicateCustomers().size() > 0, true);
        assertEquals(results.getNonDuplicates().size() > 0, true);
    }

    @Test
    public void nonExistingCustomerDataFileProcessedShouldResultWithNoExceptionAndZeroCustomers() {
        CustomerMatchResults results = null;
        try {
            results = service.processCustomerData("/not-found.csv");
        } catch (Exception e) {
            assertThat(e, null);
        }
        assertEquals(results.getGroupedCustomers().size(), 0);
    }
}
