package com.yrgo.services.customers;

import java.util.List;

import com.yrgo.dataaccess.CustomerDao;
import com.yrgo.domain.Call;
import com.yrgo.domain.Customer;

public class CustomerManagementServiceProductionImpl implements CustomerManagementService {

    CustomerDao customerDao;

    CustomerManagementServiceProductionImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public void newCustomer(Customer newCustomer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newCustomer'");
    }

    @Override
    public void updateCustomer(Customer changedCustomer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCustomer'");
    }

    @Override
    public void deleteCustomer(Customer oldCustomer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCustomer'");
    }

    @Override
    public Customer findCustomerById(String customerId) throws CustomerNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findCustomerById'");
    }

    @Override
    public List<Customer> findCustomersByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findCustomersByName'");
    }

    @Override
    public List<Customer> getAllCustomers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCustomers'");
    }

    @Override
    public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFullCustomerDetail'");
    }

    @Override
    public void recordCall(String customerId, Call callDetails) throws CustomerNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recordCall'");
    }

}
