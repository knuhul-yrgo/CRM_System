package com.yrgo.integrationtests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.fail;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.yrgo.domain.Customer;
import com.yrgo.services.customers.CustomerManagementService;
import com.yrgo.services.customers.CustomerNotFoundException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({ "/other-tiers.xml", "/datasource-test.xml" })
@Transactional
public class ManagingCustomersIntegrationTest {

    private CustomerManagementService customers;

    @Autowired
    public ManagingCustomersIntegrationTest(CustomerManagementService customers) {
        this.customers = customers;
    }

    @Test
    public void testCreatingNewCustomer() {
        // act
        customers.newCustomer(new Customer("12345", "McDonalds", "Evil company"));
        customers.newCustomer(new Customer("67890", "Adlibris", "Good company"));

        // assert
        int customersInDb = customers.getAllCustomers().size();
        assertEquals(2, customersInDb, "There should be two customers in the database!");
    }

    @Test
    public void testFindingCustomerByIsbn() {

        // arrange
        String customerId = "12345";
        Customer testCustomer = new Customer(customerId, "McDonalds", "Evil company");
        customers.newCustomer(testCustomer);
 
        //act
        Customer foundCustomer = null;
        try {
            foundCustomer= customers.findCustomerById(customerId);
            assertEquals( testCustomer, foundCustomer);
        } catch (CustomerNotFoundException e) {
            fail("No customer was found");
        }
    }

}
