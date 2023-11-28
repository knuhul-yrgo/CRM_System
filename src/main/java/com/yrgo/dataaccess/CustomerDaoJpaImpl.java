package com.yrgo.dataaccess;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.yrgo.domain.Call;
import com.yrgo.domain.Customer;

@Repository
@Transactional
public class CustomerDaoJpaImpl implements CustomerDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Customer newCustomer) {
        System.out.println("using jpa");
        em.persist(newCustomer);
    }

    @Override
    public Customer getById(String customerId) throws RecordNotFoundException {
        Customer customer = em.find(Customer.class, customerId);
        if (customer == null) {
            throw new RecordNotFoundException();
        }
        return customer;
    }

    @Override
    public List<Customer> getByName(String name) {
        return em.createQuery(
                "SELECT customer FROM Customer AS customer WHERE customer.companyName LIKE :name",
                Customer.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    @Override
    public void update(Customer customerToUpdate) throws RecordNotFoundException {
        if (em.find(Customer.class, customerToUpdate.getCustomerId()) != null) {
            em.merge(customerToUpdate);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void delete(Customer oldCustomer) throws RecordNotFoundException {
        Customer existingCustomer = getById(oldCustomer.getCustomerId());
        em.remove(existingCustomer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return em.createQuery("SELECT customer FROM Customer AS customer", Customer.class)
                .getResultList();
    }

    @Override
    public Customer getFullCustomerDetail(String customerId) throws RecordNotFoundException {
        try {
            return em.createQuery(
                    "SELECT customer FROM Customer AS customer LEFT JOIN FETCH customer.calls WHERE customer.customerId = :customerId",
                    Customer.class)
                    .setParameter("customerId", customerId)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void addCall(Call newCall, String customerId) throws RecordNotFoundException {
        Customer customer = getById(customerId);
        customer.getCalls().add(newCall);
    }

}