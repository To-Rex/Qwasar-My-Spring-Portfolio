package com.example.My.Spring.Portfolio.controller;

import com.example.My.Spring.Portfolio.model.Product;
import com.example.My.Spring.Portfolio.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //authorization for admin and user roles only
    @PostMapping("/register")
    public ResponseEntity createProduct(@RequestBody Product product) {
        ObjectMapper mapper = new ObjectMapper();
        if (this.productRepository.existsByEmail(product.getEmail())) {
            return ResponseEntity.badRequest().body(mapper.createObjectNode().put("message", "User already exists"));

        }
        if (product.getName().isEmpty()) {
            return ResponseEntity.badRequest().body(mapper.createObjectNode().put("message", "Please enter your name"));
        }

        if (product.getSurname().isEmpty()) {
            return ResponseEntity.badRequest().body(mapper.createObjectNode().put("message", "Please enter your surname"));
        }

        if (product.getAge() < 18) {
            return ResponseEntity.badRequest().body(mapper.createObjectNode().put("message", "You must be 18 years or older"));
        }

        if (product.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body(mapper.createObjectNode().put("message", "Please enter your email"));
        }

        if (product.getPhone().isEmpty()) {
            return ResponseEntity.badRequest().body(mapper.createObjectNode().put("message", "Please enter your phone number"));
        }

        if (product.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body(mapper.createObjectNode().put("message", "Please enter your password"));
        }

        if (product.getAddress().isEmpty()) {
            return ResponseEntity.badRequest().body(mapper.createObjectNode().put("message", "Please enter your address"));
        }

        if (product.getRole().isEmpty()) {
            return ResponseEntity.badRequest().body(mapper.createObjectNode().put("message", "Please enter your role"));
        }

        this.productRepository.save(product);
        return ResponseEntity.ok(mapper.createObjectNode().put("message", "User created successfully"));
    }

    //authorization for admin and user roles only
    @GetMapping("/login")
    public ResponseEntity login(@RequestParam String email, @RequestParam String password) {
        ObjectMapper mapper = new ObjectMapper();
        if (!this.productRepository.existsByEmail(email)) {
            return ResponseEntity.badRequest().body(mapper.createObjectNode().put("message", "User does not exist"));
        }
        Product product = this.productRepository.findByEmail(email);
        if (!product.getPassword().equals(password)) {
            return ResponseEntity.badRequest().body(mapper.createObjectNode().put("message", "Incorrect password"));
        }
        //return user data email name surname age phone address role phone
        return ResponseEntity.ok(mapper.createObjectNode()
                .put("email", product.getEmail())
                .put("name", product.getName())
                .put("surname", product.getSurname())
                .put("age", product.getAge())
                .put("phone", product.getPhone())
                .put("address", product.getAddress())
                .put("role", product.getRole())
                .put("locked", product.getLocked())
                .put("active", product.getActive()));
    }

    //authorization for admin get all users
    @GetMapping("/users")
    public ResponseEntity getAllUsers() {
        ObjectMapper mapper = new ObjectMapper();
        return ResponseEntity.ok(mapper.valueToTree(this.productRepository.findAll()));
    }

    //authorization for admin get user by id
    @GetMapping("/users/{id}")
    public ResponseEntity getUserById(@PathVariable String id) {
        ObjectMapper mapper = new ObjectMapper();
        if (!this.productRepository.existsById(id)) {
            return ResponseEntity.badRequest().body(mapper.createObjectNode().put("message", "User does not exist"));
        }
        Product product = this.productRepository.findById(id).get();
        return ResponseEntity.ok(mapper.createObjectNode()
                .put("email", product.getEmail())
                .put("name", product.getName())
                .put("surname", product.getSurname())
                .put("age", product.getAge())
                .put("phone", product.getPhone())
                .put("address", product.getAddress())
                .put("role", product.getRole())
                .put("locked", product.getLocked())
                .put("active", product.getActive()));
    }

    //update user role
    @PutMapping("/users/{id}")
    public ResponseEntity updateUserRole(@PathVariable String id, @RequestParam String role) {
        ObjectMapper mapper = new ObjectMapper();
        if (!this.productRepository.existsById(id)) {
            return ResponseEntity.badRequest().body(mapper.createObjectNode().put("message", "User does not exist"));
        }
        Product product = this.productRepository.findById(id).get();
        product.setRole(role);
        this.productRepository.save(product);
        return ResponseEntity.ok(mapper.createObjectNode().put("message", "User role updated successfully"));
    }

    //update user locked
    @PutMapping("/users/locked/{id}")
    public ResponseEntity updateUserLocked(@PathVariable String id, @RequestParam boolean locked) {
        ObjectMapper mapper = new ObjectMapper();
        if (!this.productRepository.existsById(id)) {
            return ResponseEntity.badRequest().body(mapper.createObjectNode().put("message", "User does not exist"));
        }
        Product product = this.productRepository.findById(id).get();
        product.setLocked(locked);
        this.productRepository.save(product);
        return ResponseEntity.ok(mapper.createObjectNode().put("message", "User locked updated successfully"));
    }

    //update user active
    @PutMapping("/users/active/{id}")
    public ResponseEntity updateUserActive(@PathVariable String id, @RequestParam boolean active) {
        ObjectMapper mapper = new ObjectMapper();
        if (!this.productRepository.existsById(id)) {
            return ResponseEntity.badRequest().body(mapper.createObjectNode().put("message", "User does not exist"));
        }
        Product product = this.productRepository.findById(id).get();
        product.setActive(active);
        this.productRepository.save(product);
        return ResponseEntity.ok(mapper.createObjectNode().put("message", "User active updated successfully"));
    }

    //delete user
    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        ObjectMapper mapper = new ObjectMapper();
        if (!this.productRepository.existsById(id)) {
            return ResponseEntity.badRequest().body(mapper.createObjectNode().put("message", "User does not exist"));
        }
        this.productRepository.deleteById(id);
        return ResponseEntity.ok(mapper.createObjectNode().put("message", "User deleted successfully"));
    }

    //admin add user balance
    @PutMapping("/users/balance/{id}")
    public ResponseEntity updateUserBalance(@PathVariable String id, @RequestParam double balance) {
        ObjectMapper mapper = new ObjectMapper();
        if (!this.productRepository.existsById(id)) {
            return ResponseEntity.badRequest().body(mapper.createObjectNode().put("message", "User does not exist"));
        }
        Product product = this.productRepository.findById(id).get();
        product.setBalance((int) (product.getBalance() + balance));
        this.productRepository.save(product);
        return ResponseEntity.ok(mapper.createObjectNode().put("message", "User balance updated successfully"));
    }

    //admin add worksCcv add works
    @PutMapping("/users/works/{id}")
    public ResponseEntity updateUserWorks(@PathVariable String id, @RequestParam String works) {
        ObjectMapper mapper = new ObjectMapper();
        if (!this.productRepository.existsById(id)) {
            return ResponseEntity.badRequest().body(mapper.createObjectNode().put("message", "User does not exist"));
        }
        Product product = this.productRepository.findById(id).get();
        product.setWorksCcv(works);
        this.productRepository.save(product);
        return ResponseEntity.ok(mapper.createObjectNode().put("message", "User works updated successfully"));
    }

}