
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * reads from a CSV file of product information into an arraylist which is returned
 * The expected file format is 
 * ID,Name,Category,Supplier,QuantityInStock,ReorderLevel,DateAdded,DateUpdated
 * parameter to readProducts is the name of the file to read
 */
public class ProductFileReader {

    public static ArrayList<Product> readProducts(String filename) {
        ArrayList<Product> products = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(new File(filename))) {
            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine(); // Skip the header row
            }

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Product product = parseProduct(line);
                
                if (product != null) {
                    products.add(product);
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }

        return products;
    }

    private static Product parseProduct(String line) {
        try (Scanner lineScanner = new Scanner(line)) {
            lineScanner.useDelimiter(",");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

            if (lineScanner.hasNext()) {
                String id = lineScanner.next();
                String name = lineScanner.next();
                String category = lineScanner.next();
                String supplier = lineScanner.next();
                int quantityInStock = lineScanner.nextInt();
                int reorderLevel = lineScanner.nextInt();
                LocalDateTime dateAdded = LocalDateTime.parse(lineScanner.next());
                LocalDateTime dateUpdated = LocalDateTime.parse(lineScanner.next());

                Product product = new Product(id, name, category, supplier, quantityInStock, reorderLevel);
                product.setDateAdded(dateAdded);
                product.setDateUpdated(dateUpdated);

                return product;
            }
        } catch (Exception e) {
            System.err.println("Error parsing line: " + line + " - " + e.getMessage());
        }
        return null;
    }
}

