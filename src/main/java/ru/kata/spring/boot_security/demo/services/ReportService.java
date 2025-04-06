package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Product;
import ru.kata.spring.boot_security.demo.models.StockMovement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {
    private final ProductService productService;
    private final StockMovementService movementService;
    private final InventoryService inventoryService;

    public ReportService(ProductService productService,
                         StockMovementService movementService,
                         InventoryService inventoryService) {
        this.productService = productService;
        this.movementService = movementService;
        this.inventoryService = inventoryService;
    }

    public List<String> getHeaders(String type) {
        return switch (type) {
            case "stock" -> List.of("Артикул", "Наименование", "Категория", "Количество", "Местоположение");
            case "incoming", "outgoing", "movement" -> List.of("Товар", "Тип", "Кол-во", "Дата");
            case "inventory" -> List.of("Зона", "Дата", "Комментарий");
            default -> List.of();
        };
    }

    public List<List<String>> getReport(String type, LocalDate from, LocalDate to, List<String> groupBy) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (type.equals("stock")) {
            List<Product> products = productService.findAll();

            if (groupBy != null && !groupBy.isEmpty()) {
                Comparator<Product> comparator = Comparator.comparing(p -> "");
                if (groupBy.contains("category")) {
                    comparator = comparator.thenComparing(p -> p.getCategory().getName());
                }
                if (groupBy.contains("location")) {
                    comparator = comparator.thenComparing(p -> p.getLocation().getZone() + "-" + p.getLocation().getShelf());
                }

                products = products.stream().sorted(comparator).collect(Collectors.toList());
            }

            return products.stream()
                    .map(p -> List.of(
                            p.getSku(),
                            p.getName(),
                            p.getCategory().getName(),
                            String.valueOf(p.getQuantity()),
                            p.getLocation().getZone() + "-" + p.getLocation().getShelf()
                    ))
                    .collect(Collectors.toList());
        } else if (type.equals("inventory")) {
            return inventoryService.findAll().stream()
                    .filter(i -> i.getInventoryDate() != null &&
                            dateInRange(i.getInventoryDate().atStartOfDay(), from, to))
                    .map(i -> List.of(
                            i.getLocation().getZone() + "-" + i.getLocation().getShelf(),
                            i.getInventoryDate().format(dateFormatter),
                            i.getComment()
                    ))
                    .collect(Collectors.toList());
        } else {
            return movementService.findAll().stream()
                    .filter(m -> m.getMovementDate() != null &&
                            dateInRange(m.getMovementDate().atStartOfDay(), from, to))
                    .filter(m -> {
                        String moveType = m.getMovementType();
                        return type.equals("movement")
                                || (type.equals("incoming") && "ПРИХОД".equalsIgnoreCase(moveType))
                                || (type.equals("outgoing") && "РАСХОД".equalsIgnoreCase(moveType));
                    })
                    .map(m -> List.of(
                            m.getProduct().getName(),
                            m.getMovementType(),
                            String.valueOf(m.getQuantity()),
                            m.getMovementDate().format(dateFormatter)
                    ))
                    .collect(Collectors.toList());
        }
    }

    private boolean dateInRange(LocalDateTime date, LocalDate from, LocalDate to) {
        LocalDate d = date.toLocalDate();
        return (from == null || !d.isBefore(from)) && (to == null || !d.isAfter(to));
    }
}
