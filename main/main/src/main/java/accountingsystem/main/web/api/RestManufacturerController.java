package accountingsystem.main.web.api;


import accountingsystem.main.model.Manufacturer;
import accountingsystem.main.model.Product;
import accountingsystem.main.service.ManufacturerService;
import accountingsystem.main.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturer")
public class RestManufacturerController {
    private final ManufacturerService manufacturerService;
    public RestManufacturerController(ManufacturerService manufacturerService){
        this.manufacturerService=manufacturerService;
    }
    @GetMapping("/getManufacturerById/{manufacturerId}")
    public ResponseEntity<Manufacturer> getManufacturerById(@PathVariable Long manufacturerId){
        Manufacturer result=this.manufacturerService.findById(manufacturerId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAllManufacturers")
    public ResponseEntity<List<Manufacturer>> getAllManufacturers(){
        List<Manufacturer> manufacturers=this.manufacturerService.findAll();
        return ResponseEntity.ok(manufacturers);
    }
    @PostMapping("/addManufacturer")
    public ResponseEntity addTurnover(
            @RequestParam String name,
            @RequestParam String date){

        Manufacturer newManufacturer=this.manufacturerService.save(name,date);
        if(newManufacturer == null){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            //change return status code if needed
        }
        return new ResponseEntity<>(HttpStatus.OK);


    }
    @DeleteMapping("/deleteManufacturer")
    public ResponseEntity<Long> deleteManufacturer(@RequestParam Long Id){
        Manufacturer deletedManufacturer=this.manufacturerService.deleteById(Id);
        if(deletedManufacturer == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Id, HttpStatus.OK);
    }
}
