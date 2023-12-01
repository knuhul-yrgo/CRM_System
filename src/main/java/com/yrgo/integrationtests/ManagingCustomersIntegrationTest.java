package com.yrgo.integrationtests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.yrgo.domain.Customer;
import com.yrgo.services.customers.CustomerManagementService;

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

}
