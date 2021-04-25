package accountingsystem.main.web.api;

import accountingsystem.main.dto.SoldProductsCountDto;
import accountingsystem.main.model.*;
import accountingsystem.main.repository.UserRepository;
import accountingsystem.main.service.ManufacturerService;
import accountingsystem.main.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class RestProductController {
    private final ProductService productService;
    private final ManufacturerService manufacturerService;
    private final UserRepository userRepository;
    public RestProductController(ProductService productService, ManufacturerService manufacturerService, UserRepository userRepository){
        this.productService=productService;
        this.manufacturerService=manufacturerService;
        this.userRepository = userRepository;
    }
    @GetMapping("/getProductById/{productId}")
    public ResponseEntity<Product> getProductById(@RequestParam Long productId){
        Product result=this.productService.findById(productId);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products=this.productService.findAll();
        return ResponseEntity.ok(products);
    }
    @PostMapping("/addProduct")
    public ResponseEntity addProduct( @RequestParam String description,
                                       @RequestParam String name,
                                       @RequestParam Long manufacturerId,
                                       @RequestParam Long price,
                                       @RequestParam String date,
                                       @RequestParam String expirationDate){

        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm");
        LocalDateTime newDate=LocalDateTime.parse(date,formatter);
        LocalDateTime newExpirationDate=LocalDateTime.parse(expirationDate,formatter);
        Manufacturer newManufacturer=this.manufacturerService.findById(manufacturerId);
        if(newManufacturer!=null){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
        Product newProduct=this.productService.save(description,name,newManufacturer,price,newDate,newExpirationDate);
        if(newProduct == null){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            //change return status code if needed
        }
        return new ResponseEntity<>(HttpStatus.OK);


    }
    @DeleteMapping("/deleteProduct")
    public ResponseEntity<Long> deleteProduct(@RequestParam Long Id){
        Product deletedProduct=this.productService.deleteById(Id);
        if(deletedProduct == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Id, HttpStatus.OK);
    }
    @GetMapping("/getAllProductsForCompany")
    public ResponseEntity<List<Product>> getAllProductsForCompany(Principal principal){
        String username = principal.getName();
        User user = this.userRepository.findByUsername(username).get();

        Company company = user.getCompanies().stream().findFirst().get();

        List<Product> products= company.getProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getAllSoldProductsCount")
    public ResponseEntity<SoldProductsCountDto> getAllSoldProductsCount(Principal principal){
        String username = principal.getName();
        User user = this.userRepository.findByUsername(username).get();

        Company company = user.getCompanies().stream().findFirst().get();
        return ResponseEntity.ok(new SoldProductsCountDto(company.getSoldProducts()));
    }

}