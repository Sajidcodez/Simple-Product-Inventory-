
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * writes an arraylist of product objects to a CSV file, in the following format
 * ID,Name,Category,Supplier,QuantityInStock,ReorderLevel,DateAdded,DateUpdated
 * parameters are the arraylist of products and the filename to write to
 */
public  class ProductFileWriter {
    
    public static void writeProducts(ArrayList<Product> products, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            // Write CSV header
            writer.write("ID,Name,Category,Supplier,QuantityInStock,ReorderLevel,DateAdded,DateUpdatd\n");
            
            // Write product data
            for (Product product : products) {
                writer.write(product.getId() + "," +
                             product.getName() + "," +
                             product.getCategory() + "," +
                             product.getSupplier() + "," +
                             product.getQuantityInStock() + "," +
                             product.getReorderLevel() + "," +
                			 product.getDateAdded() + "," +
                             product.getDateUpdated() + "\n");
            }
            
            System.out.println("Products successfully written to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
