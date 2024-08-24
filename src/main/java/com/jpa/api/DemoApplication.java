package com.jpa.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.jpa.api.entities.Product;
import com.jpa.api.entities.User;
import com.jpa.api.services.ProductService;
import com.jpa.api.services.UserService;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext app = SpringApplication.run(DemoApplication.class, args);

        UserService userSer = app.getBean(UserService.class);
        ProductService proSer = app.getBean(ProductService.class);

        //USERS
        User user = new User();
        user.setName("Shadab");
        user.setPass("2ee");
        user.setType("admin");

        User user2 = new User();
        user2.setName("Sean");
        user2.setPass("232");
        user2.setType("general");

        // Save users first in DB
        userSer.saveUser(user);
        userSer.saveUser(user2);

        //PRODUCTS
        Product pro1 = new Product();
        pro1.setProductName("Laptop");
        pro1.setProductPrice(10000.0);
        pro1.setUser(user);

        Product pro2 = new Product();
        pro2.setProductName("Watch");
        pro2.setProductPrice(10000.0);
        pro2.setUser(user2);

        // Save products
        proSer.saveProduct(pro1);
        proSer.saveProduct(pro2);

        
        // Update users with products and save again
        user.setProducts(List.of(pro1, pro2));
        user2.setProducts(List.of(pro2));

        userSer.saveUser(user);  // Update user with products
        userSer.saveUser(user2); // Update user2 with product

        userSer.getAllUser().forEach(users -> System.out.println(users));

        List<User> u1 = userSer.getBytypeAndpass("admin", "2eewd");
        System.out.println("By Custom : " + u1);

        List<User> uByPtrn = userSer.getUserByLike("z");
        System.out.println("By Custom : " + uByPtrn);
    }
}
