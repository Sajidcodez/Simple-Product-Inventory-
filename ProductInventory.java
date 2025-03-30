import java.util.*;


public class ProductInventory {
	
	private HashMap<String, Product> productsMap;
	
	public ProductInventory() {
		productsMap = new HashMap<>();
	}
	
	public void addProduct(Product x) {
		productsMap.put(x.getId(), x);
	}
	
	public boolean removeProduct(String ID) {
		if (productsMap.containsKey(ID)) {
			productsMap.remove(ID);
			return true ;
		} else {
			return false ; 
		}
	}
	
	public boolean updateQuantity(String id, int newQuantity) {
		Product x = productsMap.get(id);
		if (x != null ) {
			x.setQuantityInStock(newQuantity);
			x.setDateUpdated(java.time.LocalDateTime.now());
			return true; 
		} else {
			return false ;
		}
	}
	
	public ArrayList<Product> getAllProducts() {
	    ArrayList<Product> productList = new ArrayList<>();
	    for (Product x : productsMap.values()) {
	        productList.add(x);
	    }
	    return productList;
	}
	
	public ArrayList<Product> getAllProductsSortedByName() {
        ArrayList<Product> list = getAllProducts();
        Collections.sort(list, new Comparator<Product>() {
        @Override
        public int compare(Product product1, Product product2) {
            return product1.getName().compareToIgnoreCase(product2.getName());
            }
        });
        return list;
    } 
	
	// Returns an ArrayList of products sorted by supplier
	public ArrayList<Product> getAllProductsSortedBySupplier(){
		ArrayList<Product> list = getAllProducts();
		Collections.sort(list, new Comparator<Product>() {
				@Override
				public int compare(Product product1, Product product2) {
					return product1.getSupplier().compareToIgnoreCase(product2.getSupplier());
				}
		}			
				);
		return list ;
	}
	
	//  Returns all products in the given category and doesnt care about case sensitivity so user
	// doesnt have to worry bout captial or lowercase
	public ArrayList<Product> getAllProductsInCategory(String category){
		ArrayList<Product> outcome = new ArrayList<>();
		for (Product x: productsMap.values()) {
			if (x.getCategory().equalsIgnoreCase(category)) {
				outcome.add(x);
			}
		}
		return outcome ;
	}
	
	
	// Returns an arrayList of all products that need to be reordered
    // for example: quantity in stock < reorder level
    public ArrayList<Product> getReorderList() {
        ArrayList<Product> outcome = new ArrayList<>();
        for (Product x : productsMap.values()) {
            if (x.getQuantityInStock() < x.getReorderLevel()) {
                outcome.add(x);
          }
        }
        return outcome;
    }
}
	
	


