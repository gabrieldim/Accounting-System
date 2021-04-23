package accountingsystem.main.web.api;

import accountingsystem.main.model.*;
import accountingsystem.main.repository.UserRepository;
import accountingsystem.main.resource.request.AddCompanyRequest;
import accountingsystem.main.service.CompanyService;
import accountingsystem.main.service.ProductService;
import accountingsystem.main.service.UserService;
import accountingsystem.main.service.WorkServicesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/company")
public class RestCompanyController {
    private final CompanyService companyService;
    private final WorkServicesService workServicesService;
    private final ProductService productService;
    private final UserRepository userRepository;
    public RestCompanyController(CompanyService companyService, WorkServicesService workServicesService, ProductService productService, UserRepository userRepository){
        this.companyService=companyService;
        this.workServicesService=workServicesService;
        this.productService=productService;
        this.userRepository=userRepository;
    }

    @GetMapping("/getCompanyById/{companyId}")
    public ResponseEntity<Company> getTurnoverById(@PathVariable Long companyId){
        Company result=this.companyService.findById(companyId).get();
        return ResponseEntity.ok(result);
    }
    @GetMapping("/getAllCompanies")
    public ResponseEntity<List<Company>> getAllTurnovers(){
        List<Company> companies=this.companyService.findAll();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/getCompaniesByUser/{userId}")
    public ResponseEntity<List<Company>> getCompaniesByUser(Long userId){
        User user=this.userRepository.findById(userId).orElse(null);
        if(user==null){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
        List<Company> companies=this.companyService.findByUser(user);
        return ResponseEntity.ok(companies);
    }
    @PostMapping("/addCompany")
    public ResponseEntity addCompany(@RequestBody AddCompanyRequest addCompanyRequest){

        List<WorkService> workServices= addCompanyRequest.getWorkServiceIds().stream().
                map(x->this.workServicesService.findById(x)).
                collect(Collectors.toList());
        List<Product> products=addCompanyRequest.getProductsIds().stream().
                map(x->this.productService.findById(x)).
                collect(Collectors.toList());
        User user=this.userRepository.findById(addCompanyRequest.getUserId()).orElse(null);
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm");
        LocalDateTime newIncorporationDate=LocalDateTime.parse(addCompanyRequest.getIncorporationDate(),formatter);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
        Company newCompany=this.companyService.save(
                addCompanyRequest.getName(),
                addCompanyRequest.getFounder(),
                products,
                workServices,
                addCompanyRequest.getAddress(),
                newIncorporationDate,
                addCompanyRequest.getTaxNumber(),
                addCompanyRequest.getRegisteredNumber(),
                user
                                                    );
        if(newCompany == null){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            //change return status code if needed
        }
        return new ResponseEntity<>(HttpStatus.OK);


    }
    @DeleteMapping("/deleteCompany")
    public ResponseEntity<Long> deleteCompany(@RequestParam Long Id){
        Company deletedCompany=this.companyService.deleteById(Id);
        if(deletedCompany == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Id, HttpStatus.OK);
    }
}
