package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private HttpSession session;

    private Long getCurrentUserId() {
        return (Long) session.getAttribute("userId");
    }
    
    public List<Product> getAllProducts() {
    	Long userId = getCurrentUserId();
        logger.info("Operation=READ, Entity=Products, Action=FetchAll, UserId={}", userId);
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
    	Long userId = getCurrentUserId();
        logger.info("Operation=SEARCH, Entity=Products, Action=FetchById, ProductId={}, UserId={}", id, userId);
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
    }
    
    public void addProduct(Product product) {
        if (productRepository.existsById(product.getId())) {
            throw new RuntimeException("Product already exists with ID: " + product.getId());
        }
        Long userId = getCurrentUserId();
        
        logger.info("Operation=WRITE, Entity=Products, Action=Add, ProductId={}, UserId={}", product.getId(), userId);
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with ID: " + id);
        }
        
        Long userId = getCurrentUserId();
        logger.info("Operation=WRITE, Entity=Products, Action=Delete, UserId={}", userId);
        productRepository.deleteById(id);
    }

    public void updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setExpirationDate(updatedProduct.getExpirationDate());
        
        Long userId = getCurrentUserId();
        logger.info("Operation=READ, Entity=Products, Action=Fetch, ProductId={}, UserId={}", existingProduct.getId(), userId);
        logger.info("Operation=WRITE, Entity=Products, Action=Update, UserId={}", userId);
        productRepository.save(existingProduct);
    }
    
    public List<Product> searchExpensiveProducts(double priceThreshold) {
    	Long userId = getCurrentUserId();
        logger.info("Operation=SEARCH, Entity=Products, Action=SearchExpensive, PriceThreshold={}, UserId={}", priceThreshold, userId);
        return productRepository.findAll().stream().filter(product -> product.getPrice() > priceThreshold).collect(Collectors.toList());
    }
}