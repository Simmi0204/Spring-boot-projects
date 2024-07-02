package com.amigoscode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {
    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    record NewCustomerRequest(String name,
                              String email,
                              Integer age){}
    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);

    }
    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }

    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id,
                               @Validated @RequestBody NewCustomerRequest request){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();
            existingCustomer.setName(request.name());
            existingCustomer.setEmail(request.email());
            existingCustomer.setAge(request.age());

            customerRepository.save(existingCustomer);
        } else {
            // Handle the case where the customer with the given id is not found
            // You may throw an exception or return an appropriate HTTP response
            // For simplicity, I'll throw an exception here
            throw new RuntimeException("Customer with id " + id + " not found");
        }
    }



    /*
      @GetMapping("/greet")
     public GreetResponse greet() {
         GreetResponse response = new GreetResponse(
                 "Hello",
                 List.of("Java", "Golang", "JavaScript"),
                 new Person("Alex", 28, 38000)
         );
         return response;
     }
     record Person(String name, int age, double savings) {
     }
     //Record represents an immutable data structure that holds
     //a single string field named 'greet'. It automatically generates getter methods, constructors, equals(), hashCode()
     //& toString() methods
     record GreetResponse(
             String greet,
             List<String> favProgrammingLanguages,
             Person person
     ) { }
    */
}
