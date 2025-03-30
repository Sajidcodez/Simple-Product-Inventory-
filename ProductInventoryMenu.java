import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductInventoryMenu {

    public static void main(String[] args) {
        
        ProductInventory inventory = new ProductInventory();

        /* IN THE CONSOLE after you select an option, it might seem like nothing happened, but
         you have to scroll up in the console terminal to see the result */
        
        System.out.println("Reading products from Products.csv...");
        ArrayList<Product> initialList = ProductFileReader.readProducts("Products.csv");
        
 
        for (Product p : initialList) {
            inventory.addProduct(p);
        }
        System.out.println(initialList.size() + " products loaded.\n"); 


        Scanner input = new Scanner(System.in);
        String choice = "";

        while (!choice.equalsIgnoreCase("Q")) {
            printMenu();
            System.out.print("Enter your choice: ");
            choice = input.nextLine();

            switch (choice.toUpperCase()) {
                case "A":
                    addProductOption(inventory, input);
                    break;
                case "R":
                    removeProductOption(inventory, input);
                    break;
                case "U":
                    updateQuantityOption(inventory, input);
                    break;
                case "N":
                    printProductsSortedByName(inventory);
                    break;
                case "C":
                    printProductsInCategory(inventory, input);
                    break;
                case "O":
                    printProductsToReorder(inventory);
                    break;
                case "S":
                    printProductsSortedBySupplier(inventory);
                    break;
                case "Q":
                    // exit loop, then final text
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.\n");
                    break;
            }
        }

        System.out.println("\nPrinting reports");
        ProductFileWriter.writeProducts(inventory.getAllProducts(),"Products.csv");
        ProductFileWriter.writeProducts(inventory.getAllProductsSortedByName(),"allproducts.txt");
        ProductFileWriter.writeProducts(inventory.getAllProductsSortedBySupplier(),"productsbysupplier.txt");
        System.out.println("Exiting program.");
        input.close();
    }

    private static void printMenu() {
        System.out.println("\nProduct Inventory Menu:");
        System.out.println("A. Add Product");
        System.out.println("R. Remove Product");
        System.out.println("U. Update Quantity in Stock");
        System.out.println("N. Print Products Sorted by Name");
        System.out.println("C. Print Products in a Category");
        System.out.println("O. Print Products to Reorder");
        System.out.println("S. Print Products Sorted by Supplier");
        System.out.println("Q. Quit");
    }

    private static void addProductOption(ProductInventory inventory, Scanner input) {
        System.out.print("Enter product ID: ");
        String id = input.nextLine();

        System.out.print("Enter product name: ");
        String name = input.nextLine();

        System.out.print("Enter category: ");
        String category = input.nextLine();

        System.out.print("Enter supplier: ");
        String supplier = input.nextLine();

        System.out.print("Enter quantity in stock: ");
        int qty = Integer.parseInt(input.nextLine());

        System.out.print("Enter reorder level: ");
        int reorder = Integer.parseInt(input.nextLine());

        Product newProduct = new Product(id, name, category, supplier, qty, reorder);
        inventory.addProduct(newProduct);

        System.out.println("Product added successfully.");
    }

    private static void removeProductOption(ProductInventory inventory, Scanner input) {
        System.out.print("Enter product ID to remove: ");
        String id = input.nextLine();

        boolean removed = inventory.removeProduct(id);
        if (removed) {
            System.out.println("Product " + id + " removed successfully.");
        } else {
            System.out.println("Product " + id + " not found.");
        }
    }

    private static void updateQuantityOption(ProductInventory inventory, Scanner input) {
        System.out.print("Enter product ID to update quantity: ");
        String id = input.nextLine();

        System.out.print("Enter new quantity: ");
        int newQty = Integer.parseInt(input.nextLine());

        boolean success = inventory.updateQuantity(id, newQty);
        if (success == true) {
            System.out.println("Quantity updated successfully.");
        } else {
            System.out.println("Product " + id + " not found.");
        }
    }

    private static void printProductsSortedByName(ProductInventory inventory) {
        ArrayList<Product> sortedByName = inventory.getAllProductsSortedByName();
        System.out.println("\nProducts Sorted by Name:\n");
        for (Product p : sortedByName) {
            System.out.println(p);
        }
    }

    private static void printProductsInCategory(ProductInventory inventory, Scanner input) {
        System.out.print("Enter category: ");
        String category = input.nextLine();

        ArrayList<Product> categoryProducts = inventory.getAllProductsInCategory(category);
        System.out.println("\nProducts in Category: " + category + "\n");
        for (Product p : categoryProducts) {
            System.out.println(p);
        }
    }

    private static void printProductsToReorder(ProductInventory inventory) {
        ArrayList<Product> reorderList = inventory.getReorderList();
        if (reorderList.isEmpty()) {
            System.out.println("\nNo products need reordering.\n");
        } else {
            System.out.println("\nProducts to Reorder:\n");
            for (Product p : reorderList) {
                System.out.println(p);
            }
        }
    }

    private static void printProductsSortedBySupplier(ProductInventory inventory) {
        ArrayList<Product> sortedBySupplier = inventory.getAllProductsSortedBySupplier();
        System.out.println("\nProducts Sorted by Supplier:\n");
        for (Product p : sortedBySupplier) {
            System.out.println(p);
        }
    }
}
