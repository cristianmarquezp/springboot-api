package com.example.demo.service;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;


@Component
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;


    private int customerIdCount = 1;
    private List<Customer> customerLists = new CopyOnWriteArrayList<>();

    public Customer addCustomer (Customer customer){
       /* customer.setCustomerid(customerIdCount);
        customerLists.add(customer);
        customerIdCount++;
        return  customer;*/
       return customerDAO.save(customer);


    }

    public List<Customer> getCustomers(){
        //return customerLists;

        return customerDAO.findAll();
    }

    public Customer getCustomer(int customerId){
        /*return customerLists
                    .stream()
                    .filter(c -> c.getCustomerid() == customerId)
                    .findFirst()
                    .get();*/

        //return customerDAO.findById(customerId).get();
        Optional<Customer> optionalCustomer = customerDAO.findById(customerId);
        if (!optionalCustomer.isPresent()){
            throw new CustomerNotFoundException("Customer its not available") ;
        }
        return optionalCustomer.get();
    }

    public Customer updateCustomer(int customerId, Customer customer){
       /* customerLists.stream()
                .forEach(c ->{
                    if(c.getCustomerid() == customerId){
                        c.setName(customer.getName());
                        c.setEmail(customer.getEmail());
                        c.setLastName(customer.getLastName());
                    }
                });
        return customerLists
                .stream()
                .filter(c -> c.getCustomerid() == customerId)
                .findFirst()
                .get();
*/
       customer.setCustomerid(customerId);
       return customerDAO.save(customer);
    }

    public void deleteCustomer(int customerId){
        /*customerLists.stream()
                .forEach(c -> {
                    if (c.getCustomerid() == customerId){
                        customerLists.remove(c);
                    }
                });*/
        customerDAO.deleteById(customerId);
    }
}
