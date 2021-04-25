package accountingsystem.main.web;

import accountingsystem.main.factory.ProductFactory;
import accountingsystem.main.model.Company;
import accountingsystem.main.model.Manufacturer;
import accountingsystem.main.model.Product;
import accountingsystem.main.model.User;
import accountingsystem.main.repository.UserRepository;
import accountingsystem.main.service.CompanyService;
import accountingsystem.main.service.ManufacturerService;
import accountingsystem.main.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class ProductController {
    private final ProductService productService;
    private final ManufacturerService manufacturerService;
    private final UserRepository userRepository;
    private final CompanyService companyService;
    public ProductController(ProductService productService, ManufacturerService manufacturerService, UserRepository userRepository, CompanyService companyService) {
        this.productService = productService;
        this.manufacturerService = manufacturerService;
        this.userRepository = userRepository;
        this.companyService = companyService;
    }

    @GetMapping("/product")
    public String listAllProducts(Model model){
        model.addAttribute("products",productService.findAll());
        return "list-products";
    }

    @PostMapping("/product/add")
    public String addProduct(
            @RequestParam String description,
            @RequestParam String name,
            @RequestParam String price,
            @RequestParam String manufacturerId,
            Principal principal){
        String username = principal.getName();
        User user = this.userRepository.findByUsername(username).get();

        Company company = user.getCompanies().stream().findFirst().get();

        Manufacturer manufacturer = this.manufacturerService.findById(
                Long.valueOf(manufacturerId));

        Product product = ProductFactory.createProduct(
                description,
                name,
                Long.valueOf(price),
                manufacturer);
        this.productService.save(product);
        company.getProducts().add(product);
        this.companyService.save(company);
        return "redirect:/dashboard";
     }

    @GetMapping("/product/add")
    public String getAddProductPage(){
        return "insert-product";
    }

}
