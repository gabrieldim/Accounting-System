package accountingsystem.main.web.api;


import accountingsystem.main.model.Manufacturer;
import accountingsystem.main.model.Product;
import accountingsystem.main.model.WorkService;
import accountingsystem.main.service.WorkServicesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/workServices")
public class RestWorkServicesController {
      private final WorkServicesService workServicesService;
    public RestWorkServicesController(WorkServicesService workServicesService) {
        this.workServicesService=workServicesService;
    }

    @GetMapping("/getWorkServiceById/{workServiceId}")
    public ResponseEntity<WorkService> getWorkServicesById(@RequestParam Long workServiceId){
        WorkService result=this.workServicesService.findById(workServiceId);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/getAllWorkServices")
    public ResponseEntity<List<WorkService>> getAllWorkServices(){
        List<WorkService> workServices=this.workServicesService.findAll();
        return ResponseEntity.ok(workServices);
    }
    @PostMapping("/addWorkService")
    public ResponseEntity addWorkService( @RequestParam String name,
                                        @RequestParam String description,
                                         @RequestParam Long price){


        WorkService workService=this.workServicesService.save(name,description,price);
        if(workService == null){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            //change return status code if needed
        }
        return new ResponseEntity<>(HttpStatus.OK);


    }
    @DeleteMapping("/deleteWorkService")
    public ResponseEntity<Long> deleteWorkService(@RequestParam Long Id){
        WorkService workService=this.workServicesService.deleteById(Id);
        if(workService== null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Id, HttpStatus.OK);
    }
}
