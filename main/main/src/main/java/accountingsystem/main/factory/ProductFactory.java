package accountingsystem.main.factory;

import accountingsystem.main.model.Manufacturer;
import accountingsystem.main.model.Product;

public class ProductFactory {
    public static Product createProduct(
            String description,
            String name,
            Long price,
            Manufacturer manufacturer){

        Product product = new Product();
        product.setDescription(description);
        product.setName(name);
        product.setPrice(price);
        product.setManufacturer(manufacturer);
        return product;
    }
}
