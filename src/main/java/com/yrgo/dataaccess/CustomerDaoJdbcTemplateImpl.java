package com.yrgo.dataaccess;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yrgo.domain.Call;
import com.yrgo.domain.Customer;

public class CustomerDaoJdbcTemplateImpl implements CustomerDao {
    private static final String DELETE_SQL = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID=?";
    private static final String UPDATE_SQL = "UPDATE CUSTOMER SET COMPANY_NAME=?, EMAIL=?, TELEPHONE=?, NOTES=? WHERE CUSTOMER_ID=?";
    private static final String INSERT_SQL = "INSERT INTO ACTION (COMPANY_NAME, EMAIL, TELEPHONE, NOTES) VALUES (?,?,?,?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID=?";
    private static final String SELECT_BY_NAME_SQL = "SELECT * FROM CUSTOMER WHERE COMPANY_NAME LIKE ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM CUSTOMER";
    private static final String SELECT_FULL_DETAIL_SQL = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID=?";
    private static final String ADD_CALL_SQL = "INSERT INTO TBL_CALL (TIME_AND_DATE, NOTES) VALUES (?,?)";

    private JdbcTemplate template;

    public CustomerDaoJdbcTemplateImpl(JdbcTemplate template) {
        this.template = template;
    }

    private void createTables() {
        try {
            this.template.update(
                    "CREATE TABLE CUSTOMER (CUSTOMER_ID VARCHAR(20), COMPANY_NAME VARCHAR(75), EMAIL VARCHAR(75), TELEPHONE CHAR(10), NOTES VARCHAR(255))");
        } catch (org.springframework.jdbc.BadSqlGrammarException e) {
            System.out.println("Assuming the Customer table exists");
        }
    }

    @Override
    public void create(Customer newCustomer) {
        template.update(
                INSERT_SQL,
                newCustomer.getCompanyName(),
                newCustomer.getEmail(),
                newCustomer.getTelephone(),
                newCustomer.getNotes());
    }

    @Override
    public Customer getById(String customerId) throws RecordNotFoundException {
        return template.queryForObject(
                SELECT_BY_ID_SQL,
                new BeanPropertyRowMapper<>(Customer.class),
                customerId);

    }

    @Override
    public List<Customer> getByName(String name) {
        return template.query(
                SELECT_BY_NAME_SQL,
                new BeanPropertyRowMapper<>(Customer.class),
                "%" + name + "%");
    }

    @Override
    public void update(Customer customerToUpdate) throws RecordNotFoundException {
        template.update(
                UPDATE_SQL,
                customerToUpdate.getCompanyName(),
                customerToUpdate.getEmail(),
                customerToUpdate.getTelephone(),
                customerToUpdate.getNotes(),
                customerToUpdate.getCustomerId());

    }

    @Override
    public void delete(Customer oldCustomer) throws RecordNotFoundException {
        template.update(DELETE_SQL, oldCustomer.getCustomerId());

    }

    @Override
    public List<Customer> getAllCustomers() {
        return template.query(SELECT_ALL_SQL, new BeanPropertyRowMapper<>(Customer.class));
    }

    @Override
    public Customer getFullCustomerDetail(String customerId) throws RecordNotFoundException {
        Customer customer = template.queryForObject(
                SELECT_BY_ID_SQL,
                new BeanPropertyRowMapper<>(Customer.class),
                customerId);

        return customer;
    }

    @Override
    public void addCall(Call newCall, String customerId) throws RecordNotFoundException {
        template.update(
                ADD_CALL_SQL,
                newCall.getTimeAndDate(),
                newCall.getNotes());
    }
}
