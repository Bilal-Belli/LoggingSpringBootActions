package com.example.demo;

import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication /*implements CommandLineRunner*/  {

	@Autowired
    private ProductService productService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
/*
    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Display products");
            System.out.println("2. Fetch product by ID");
            System.out.println("3. Add new product");
            System.out.println("4. Delete product by ID");
            System.out.println("5. Update product info");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> displayProducts();
                case 2 -> fetchProductById(scanner);
                case 3 -> addProduct(scanner);
                case 4 -> deleteProductById(scanner);
                case 5 -> updateProduct(scanner);
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void displayProducts() {
        productService.getAllProducts().forEach(System.out::println);
    }

    private void fetchProductById(Scanner scanner) {
        System.out.print("Enter product ID: ");
        Long id = scanner.nextLong();
        try {
            System.out.println(productService.getProductById(id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addProduct(Scanner scanner) {
        System.out.print("Enter product ID: ");
        Long id = scanner.nextLong();
        System.out.print("Enter product name: ");
        String name = scanner.next();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter expiration date (YYYY-MM-DD): ");
        LocalDate expirationDate = LocalDate.parse(scanner.next());

        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setExpirationDate(expirationDate);

        try {
            productService.addProduct(product);
            System.out.println("Product added successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteProductById(Scanner scanner) {
        System.out.print("Enter product ID: ");
        Long id = scanner.nextLong();
        try {
            productService.deleteProduct(id);
            System.out.println("Product deleted successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateProduct(Scanner scanner) {
        System.out.print("Enter product ID: ");
        Long id = scanner.nextLong();
        System.out.print("Enter new name: ");
        String name = scanner.next();
        System.out.print("Enter new price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter new expiration date (YYYY-MM-DD): ");
        LocalDate expirationDate = LocalDate.parse(scanner.next());

        Product updatedProduct = new Product();
        updatedProduct.setName(name);
        updatedProduct.setPrice(price);
        updatedProduct.setExpirationDate(expirationDate);

        try {
            productService.updateProduct(id, updatedProduct);
            System.out.println("Product updated successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    */
}