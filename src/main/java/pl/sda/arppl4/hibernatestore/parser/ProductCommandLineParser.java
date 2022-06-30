package pl.sda.arppl4.hibernatestore.parser;

import pl.sda.arppl4.hibernatestore.dao.ProduktDao;
import pl.sda.arppl4.hibernatestore.model.Product;
import pl.sda.arppl4.hibernatestore.model.ProductUnit;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProductCommandLineParser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final Scanner scanner;
    private final ProduktDao dao;

    public ProductCommandLineParser(Scanner scanner, ProduktDao dao) {
        this.scanner = scanner;
        this.dao = dao;
    }

    public void parse() {
        String command;
        do {
            System.out.println("Command: add/remove/show/list/update");
            command = scanner.next();
            if (command.equals("add")) {
                handleAddCommand();
            } else if (command.equals("remove")) {
                handleRemoveCommand();
            } else if (command.equals("show")) {
                handleReturnProductCommand();
            } else if (command.equals("list")) {
                handleListCommand();
            } else if (command.equals("update")) {
                handleUpdateCommand();
            }
            System.out.println("If you want end programm type 'quit'");
        } while (!command.equals("quit"));
    }

    private void handleUpdateCommand() {
        System.out.println("What product do you need? (id)");
        Long idProduct = scanner.nextLong();
        Optional<Product> productOptional = dao.returnProduct(idProduct);
        System.out.println("What you want to update? name/price/producent/expiry(date)/quantity/unit");
        String text = scanner.next();
        Product product = productOptional.get();
        if (text.equals("name")) {
            System.out.println("Type name: ");
            String name = scanner.next();
            product.setName(name);
        } else if (text.equals("price")) {
            System.out.println("Type price: ");
            Double price = scanner.nextDouble();
            product.setPrice(price);
        } else if (text.equals("producent")) {
            System.out.println("Type producent: ");
            String producent = scanner.next();
            product.setName(producent);
        } else if (text.equals("expiry")) {
            System.out.println("Type expiry date: ");
            LocalDate expiryDate = loadExpiryDateFromUser();
            product.setExpiryDate(expiryDate);
        } else if (text.equals("quantity")) {
            System.out.println("Type quantity: ");
            Double quantity = scanner.nextDouble();
            product.setQuantity(quantity);
        } else if (text.equals("unit")) {
            System.out.println("Type unit: ");
            ProductUnit productUnit = loadProductUnitFromUSer();
            product.setUnit(productUnit);
        }
        dao.updateProduct(product);
    }

    private void handleReturnProductCommand() {
        System.out.println("What product do you want to see? (You have to get ID of product)");
        Long idProduct = scanner.nextLong();
        List<Product> productList = dao.returnProductsList();
        for (Product product : productList) {
            if (product.getId() == idProduct) {
                System.out.println(product);
            }
        }
    }

    private void handleListCommand() {
        List<Product> productList = dao.returnProductsList();
        for (Product product : productList) {
            System.out.println(product);
        }
        System.out.println();
    }

    private void handleAddCommand() {
        System.out.println("Type name: ");
        String name = scanner.next();
        System.out.println("Type producent: ");
        String producent = scanner.next();
        LocalDate expiryDate = loadExpiryDateFromUser();
        Double price = handlePrice();
        Double quantity = handleQuantity();
        ProductUnit productUnit = loadProductUnitFromUSer();
        Product product = new Product(null, name, price, producent, expiryDate, quantity, productUnit);
        dao.addProduct(product);
    }

    private Double handleQuantity() {
        Double quantity = null;
        do {
            try {
                System.out.println("Type quantity: ");
                quantity = scanner.nextDouble();
            } catch (InputMismatchException ime) {
                System.err.println("Type numebers!");
            }
        } while (quantity != null);
        return quantity;
    }

    private Double handlePrice() {
        Double price = null;
        do {
            try {
                System.out.println("Type price: ");
                price = scanner.nextDouble();
            } catch (InputMismatchException ime) {
                System.err.println("Type numebers!");
            }

        } while (price == null);
        return price;
    }

    private void handleRemoveCommand() {
        System.out.println("Enter the ID of the product what you want to remove");
        Long id = scanner.nextLong();
        Optional<Product> optionalProduct = dao.returnProduct(id);
        if (optionalProduct.isPresent()) {
            dao.removeProduct(optionalProduct.get());
        }
    }

    private ProductUnit loadProductUnitFromUSer() {
        ProductUnit productUnit = null;
        do {
            try {
                System.out.println("Type unit: ");
                String unitString = scanner.next();
                productUnit = ProductUnit.valueOf(unitString.toUpperCase());
            } catch (IllegalArgumentException iae) {
                System.err.println("Wrong unit, please type unit (unit/gram/milliliter)");
            }
        } while (productUnit == null);
        return productUnit;
    }

    private LocalDate loadExpiryDateFromUser() {
        LocalDate expiryDate = null;
        do {
            try {
                System.out.println("Type expiry date: ");
                String expiryDateString = scanner.next();

                expiryDate = LocalDate.parse(expiryDateString, FORMATTER);

                LocalDate today = LocalDate.now();
                if (expiryDate.isBefore(today)) {
                    throw new IllegalArgumentException("Date is before today");
                }
            } catch (IllegalArgumentException | DateTimeException iae) {
                expiryDate = null;
                System.err.println("Wrong date, please type date in format: yyyy-MM-dd");
            }
        } while (expiryDate == null);
        return expiryDate;
    }
}
