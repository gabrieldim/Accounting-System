package accountingsystem.main.factory;

import accountingsystem.main.model.Manufacturer;
import accountingsystem.main.model.Product;

public class ProductFactory {
    public static Product createProduct(
            String description,
            String name,
            Long profit,
            Manufacturer manufacturer){

        Product product = new Product();
        product.setDescription(description);
        product.setName(name);
        product.setProfit(profit);
        product.setManufacturer(manufacturer);
        return product;
    }
}
