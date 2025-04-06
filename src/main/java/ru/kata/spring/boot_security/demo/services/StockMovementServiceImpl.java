package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Product;
import ru.kata.spring.boot_security.demo.models.StockMovement;
import ru.kata.spring.boot_security.demo.repositories.ProductRepository;
import ru.kata.spring.boot_security.demo.repositories.StockMovementRepository;

import java.util.List;

@Service
public class StockMovementServiceImpl implements StockMovementService {

    private final StockMovementRepository repository;
    private final ProductRepository productRepository;

    public StockMovementServiceImpl(StockMovementRepository repository, ProductRepository productRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
    }

    @Override
    public List<StockMovement> findAll() {
        return repository.findAll();
    }

    @Override
    public StockMovement save(StockMovement movement) {
        Product product = movement.getProduct();
        int quantity = movement.getQuantity();

        if ("ПРИХОД".equalsIgnoreCase(movement.getMovementType())) {
            product.setQuantity(product.getQuantity() + quantity);
        } else if ("РАСХОД".equalsIgnoreCase(movement.getMovementType())) {
            if (product.getQuantity() < quantity) {
                throw new IllegalArgumentException("Недостаточно товара на складе для выполнения отгрузки.");
            }
            product.setQuantity(product.getQuantity() - quantity);
        }

        productRepository.save(product);
        return repository.save(movement);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
