package ru.kata.spring.boot_security.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.*;
import ru.kata.spring.boot_security.demo.services.*;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Component
public class DBInitializer {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final SupplierService supplierService;
    private final RecipientService recipientService;
    private final LocationService locationService;
    private final StockMovementService stockMovementService;
    private final InventoryService inventoryService;

    @Autowired
    public DBInitializer(UserService userService, RoleService roleService,
                         PasswordEncoder passwordEncoder,
                         CategoryService categoryService, ProductService productService,
                         SupplierService supplierService, RecipientService recipientService,
                         LocationService locationService, StockMovementService stockMovementService,
                         InventoryService inventoryService) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.categoryService = categoryService;
        this.productService = productService;
        this.supplierService = supplierService;
        this.recipientService = recipientService;
        this.locationService = locationService;
        this.stockMovementService = stockMovementService;
        this.inventoryService = inventoryService;
    }

    @PostConstruct
    public void init() {
//        // Роли и пользователи
//        roleService.createRole("ADMIN");
//        roleService.createRole("USER");
//        Role admin = roleService.findByName("ADMIN");
//        Role user = roleService.findByName("USER");
//
//        if (!userService.existsByEmail("admin@mail.ru")) {
//            userService.add(new User("Admin", "Root", "admin@mail.ru", "admin", Set.of(admin, user)));
//        }
//
//        User u1 = userService.findByEmail("admin@mail.ru");
//
//        if (!userService.existsByEmail("user@mail.ru")) {
//            userService.add(new User("Andrei", "Root", "user@mail.ru", "user", Set.of(user)));
//        }
//
//        Category electronics = categoryService.save(new Category(null, "Электроника", "Устройства"));
//        Category books = categoryService.save(new Category(null, "Книги", "Печатная продукция"));
//        Category clothing = categoryService.save(new Category(null, "Одежда", "Мужская/Женская"));
//        Category tools = categoryService.save(new Category(null, "Инструменты", "Строительные инструменты"));
//
//        // Поставщики
//        List<Supplier> suppliers = List.of(
//                new Supplier(null, "Поставщик 1", "8-900-111-1111", "Москва"),
//                new Supplier(null, "Поставщик 2", "8-900-222-2222", "Санкт-Петербург"),
//                new Supplier(null, "Поставщик 3", "8-900-333-3333", "Екатеринбург")
//        ).stream().map(supplierService::save).toList();
//
//        // Получатели
//        List<Recipient> recipients = List.of(
//                new Recipient(null, "Получатель 1", "a@rec.com"),
//                new Recipient(null, "Получатель 2", "b@rec.com"),
//                new Recipient(null, "Получатель 3", "c@rec.com")
//        ).stream().map(recipientService::save).toList();
//
//        // Локации
//        List<Location> locations = List.of(
//                new Location(null, "A", "1", "Полка A1"),
//                new Location(null, "B", "2", "Полка B2"),
//                new Location(null, "C", "3", "Полка C3")
//        ).stream().map(locationService::save).toList();
//
//        // Товары (10+ штук)
//        List<Product> products = List.of(
//                new Product(null, "SKU-001", "Ноутбук", "Игровой", new BigDecimal("79999.99"), 15, LocalDate.now(), electronics, locations.get(0), suppliers.get(0)),
//                new Product(null, "SKU-002", "Смартфон", "Android", new BigDecimal("25999.99"), 30, LocalDate.now(), electronics, locations.get(0), suppliers.get(1)),
//                new Product(null, "SKU-003", "Книга Java", "Обучение", new BigDecimal("1499.00"), 25, LocalDate.now(), books, locations.get(1), suppliers.get(2)),
//                new Product(null, "SKU-004", "Книга Spring", "Обучение", new BigDecimal("1799.00"), 20, LocalDate.now(), books, locations.get(1), suppliers.get(0)),
//                new Product(null, "SKU-005", "Куртка", "Мужская", new BigDecimal("6999.00"), 10, LocalDate.now(), clothing, locations.get(2), suppliers.get(1)),
//                new Product(null, "SKU-006", "Штаны", "Женские", new BigDecimal("3999.00"), 18, LocalDate.now(), clothing, locations.get(2), suppliers.get(2)),
//                new Product(null, "SKU-007", "Отвертка", "Крестовая", new BigDecimal("299.00"), 100, LocalDate.now(), tools, locations.get(0), suppliers.get(0)),
//                new Product(null, "SKU-008", "Молоток", "Стальной", new BigDecimal("799.00"), 80, LocalDate.now(), tools, locations.get(1), suppliers.get(1)),
//                new Product(null, "SKU-009", "Паяльник", "40Вт", new BigDecimal("999.00"), 50, LocalDate.now(), tools, locations.get(1), suppliers.get(2)),
//                new Product(null, "SKU-010", "Наушники", "Беспроводные", new BigDecimal("4999.00"), 35, LocalDate.now(), electronics, locations.get(2), suppliers.get(1))
//        ).stream().map(productService::save).toList();
//
//        // Перемещения (15 шт)
//        for (int i = 0; i < products.size(); i++) {
//            stockMovementService.save(new StockMovement(null,
//                    products.get(i),
//                    (i + 1) * 2,
//                    i % 2 == 0 ? "INCOMING" : "OUTGOING",
//                    LocalDate.now().minusDays(i),
//                    u1,
//                    i % 2 == 0 ? suppliers.get(i % suppliers.size()) : null,
//                    i % 2 != 0 ? recipients.get(i % recipients.size()) : null
//            ));
//        }
  }
}
