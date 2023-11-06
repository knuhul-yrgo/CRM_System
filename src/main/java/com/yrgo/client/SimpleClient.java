package com.yrgo.client;

import com.yrgo.domain.Customer;
import com.yrgo.services.customers.CustomerManagementService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SimpleClient {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext container = new
            ClassPathXmlApplicationContext("application.xml");

        CustomerManagementService customerManagementService =
            container.getBean(CustomerManagementService.class);

        List<Customer> allCustomers = customerManagementService.getAllCustomers();
        for (Customer c : allCustomers){
            System.out.println(c);
        }
    }
}
