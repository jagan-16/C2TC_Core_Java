package com.tns.onlineshopping.application;

import com.tns.onlineshopping.entities.*;
import com.tns.onlineshopping.services.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OnlineShopping {

    static Scanner sc = new Scanner(System.in);

    static ProductService productService = new ProductService();
    static AdminService adminService = new AdminService(productService);
    static CustomerService customerService = new CustomerService();
    static OrderService orderService = new OrderService();

    // ------------------ Safe Input Helpers ------------------

    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int val = sc.nextInt();
                sc.nextLine();
                return val;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double val = sc.nextDouble();
                sc.nextLine();
                return val;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    static String readString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    // ======================== MAIN MENU ========================

    public static void main(String[] args) {

        try {

            while (true) {

                System.out.println("\n1. Admin Menu");
                System.out.println("2. Customer Menu");
                System.out.println("3. Exit");
                int choice = readInt("Choose an option: ");

                switch (choice) {
                    case 1 -> adminLogin();
                    case 2 -> customerMenu();
                    case 3 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            }

        } finally {
            sc.close();
        }
    }

    // ======================== ADMIN LOGIN ========================

    static void adminLogin() {

        if (adminService.getAdminList().isEmpty()) {
            System.out.println("No admins registered yet.");
            System.out.println("Please create the first admin to continue.");

            try {
                int adminId     = readInt("Enter Admin ID: ");
                String userName = readString("Enter Admin Username: ");
                String email    = readString("Enter Admin Email: ");
                Admin admin     = new Admin(adminId, userName, email);
                adminService.addAdmin(admin);
                System.out.println("Admin created successfully! Please login now.");
            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
                return;
            }
        }

        int adminId = readInt("Enter Admin ID to login: ");

        try {
            adminService.validateAdmin(adminId);
            System.out.println("Login successful. Welcome, Admin!");
            adminMenu(adminId);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ======================== ADMIN MENU ========================

    static void adminMenu(int loggedInAdminId) {

        while (true) {

            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. View Products");
            System.out.println("4. Create Admin");
            System.out.println("5. View Admins");
            System.out.println("6. Update Order Status");
            System.out.println("7. View Orders");
            System.out.println("8. Return");
            int choice = readInt("Choose an option: ");

            switch (choice) {

                case 1 -> {
                    try {
                        int productId   = readInt("Enter Product ID: ");
                        String name     = readString("Enter Product Name: ");
                        double price    = readDouble("Enter Product Price: ");
                        int stock       = readInt("Enter Stock Quantity: ");
                        Product product = new Product(productId, name, price, stock);
                        productService.addProduct(product);
                        System.out.println("Product added successfully!");
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                case 2 -> {
                    try {
                        int productId = readInt("Enter Product ID to remove: ");
                        productService.getProductById(productId);
                        productService.removeProduct(productId);
                        System.out.println("Product removed successfully!");
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                case 3 -> {
                    try {
                        List<Product> products = productService.getProductList();
                        if (products.isEmpty()) {
                            System.out.println("No products available.");
                        } else {
                            System.out.println("Products:");
                            for (Product p : products) {
                                System.out.println("Product [productId=" + p.getId()
                                        + ", name=" + p.getName()
                                        + ", price=" + p.getPrice()
                                        + ", stockQuantity=" + p.getStockQuantity() + "]");
                            }
                        }
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                case 4 -> {
                    try {
                        int adminId     = readInt("Enter Admin ID: ");
                        String userName = readString("Enter Admin Username: ");
                        String email    = readString("Enter Admin Email: ");
                        Admin admin     = new Admin(adminId, userName, email);
                        adminService.addAdmin(admin);
                        System.out.println("Admin created successfully!");
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                case 5 -> {
                    try {
                        List<Admin> admins = adminService.getAdminList();
                        if (admins.isEmpty()) {
                            System.out.println("No admins found.");
                        } else {
                            System.out.println("Admins:");
                            for (Admin a : admins) {
                                System.out.println("Admin ID: " + a.getUserId()
                                        + ", Username: " + a.getUserName()
                                        + ", Email: " + a.getEmail());
                            }
                        }
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                case 6 -> {
                    try {
                        int orderId   = readInt("Enter Order ID: ");
                        String status = readString("Enter new status (Completed/Delivered/Cancelled): ");
                        orderService.updateOrderStatus(orderId, status);
                        System.out.println("Order status updated successfully!");
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                case 7 -> {
                    try {
                        List<Order> orders = orderService.getAllOrders();
                        if (orders.isEmpty()) {
                            System.out.println("No orders found.");
                        } else {
                            System.out.println("Orders:");
                            for (Order o : orders) {
                                System.out.println("Order ID: " + o.getOrderId()
                                        + ", Customer: " + o.getCustomer().getUserName()
                                        + ", Status: " + o.getStatus());
                                for (OrderItem item : o.getItems()) {
                                    System.out.println("  Product: " + item.getProduct().getName()
                                            + ", Quantity: " + item.getQuantity());
                                }
                            }
                        }
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                case 8 -> {
                    System.out.println("Exiting Admin...");
                    return;
                }

                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    // ======================== CUSTOMER MENU ========================

    static void customerMenu() {

        while (true) {

            System.out.println("\nCustomer Menu:");
            System.out.println("1. Create Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Place Order");
            System.out.println("4. View Orders");
            System.out.println("5. View Products");
            System.out.println("6. Return");
            int choice = readInt("Choose an option: ");

            switch (choice) {

                case 1 -> {
                    try {
                        int userId      = readInt("Enter User ID: ");
                        String userName = readString("Enter Username: ");
                        String email    = readString("Enter Email: ");
                        String address  = readString("Enter Address: ");
                        customerService.createCustomer(userId, userName, email, address);
                        System.out.println("Customer created successfully!");
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                case 2 -> {
                    try {
                        List<Customer> customers = customerService.getAllCustomers();
                        if (customers.isEmpty()) {
                            System.out.println("No customers found.");
                        } else {
                            System.out.println("Customers:");
                            for (Customer c : customers) {
                                System.out.println("User ID: " + c.getUserId()
                                        + ", Username: " + c.getUserName()
                                        + ", Email: " + c.getEmail()
                                        + ", Address: " + c.getAddress());
                            }
                        }
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                case 3 -> {
                    try {
                        int customerId = readInt("Enter Customer ID: ");
                        Customer customer = customerService.getCustomerById(customerId);

                        if (customer == null) {
                            System.out.println("Customer not found.");
                            break;
                        }

                        List<OrderItem> items = new ArrayList<>();
                        boolean cancelled = false;

                        System.out.println("(Enter -1 to place order | Enter -2 to cancel order)");

                        while (true) {
                            int productId = readInt("Enter Product ID to add to order: ");

                            if (productId == -1) break;

                            if (productId == -2) {
                                cancelled = true;
                                System.out.println("Order cancelled. Returning to menu.");
                                break;
                            }

                            try {
                                Product product = productService.getProductById(productId);
                                int qty = readInt("Enter quantity: ");
                                items.add(new OrderItem(product, qty));
                            } catch (RuntimeException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }

                        if (!cancelled) {
                            if (items.isEmpty()) {
                                System.out.println("No items added. Order cancelled.");
                            } else {
                                orderService.placeOrder(customer, items);
                                System.out.println("Order placed successfully!");
                            }
                        }

                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                case 4 -> {
                    try {
                        int customerId = readInt("Enter Customer ID: ");
                        List<Order> orders = orderService.getOrdersByCustomer(customerId);

                        if (orders.isEmpty()) {
                            System.out.println("No orders found for this customer.");
                        } else {
                            System.out.println("Orders:");
                            for (Order o : orders) {
                                System.out.println("Order ID: " + o.getOrderId()
                                        + ", Status: " + o.getStatus());
                                for (OrderItem item : o.getItems()) {
                                    System.out.println("  Product: " + item.getProduct().getName()
                                            + ", Quantity: " + item.getQuantity());
                                }
                            }
                        }
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                case 5 -> {
                    try {
                        List<Product> products = productService.getProductList();
                        if (products.isEmpty()) {
                            System.out.println("No products available.");
                        } else {
                            System.out.println("Products:");
                            for (Product p : products) {
                                System.out.println("Product [productId=" + p.getId()
                                        + ", name=" + p.getName()
                                        + ", price=" + p.getPrice()
                                        + ", stockQuantity=" + p.getStockQuantity() + "]");
                            }
                        }
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                case 6 -> {
                    System.out.println("Exiting Customer Menu...");
                    return;
                }

                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
}