package com.rafaprates.exploringjpa;

import com.github.javafaker.Faker;
import com.rafaprates.exploringjpa.entity.Product;
import com.rafaprates.exploringjpa.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public class Runner implements CommandLineRunner {

    private final ProductService productService;

    public Runner(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();

        for (int i = 0; i < 100; i++) {
            Product p = new Product();
            p.setName(faker.commerce().productName());
            p.setPrice(
                    Double.parseDouble(faker.commerce().price().replace(",", "."))
            );
            p.setCategory(faker.commerce().department());
            p.setLastUpdatedAt(faker.date().birthday());
            productService.save(p);
        }
    }
}
