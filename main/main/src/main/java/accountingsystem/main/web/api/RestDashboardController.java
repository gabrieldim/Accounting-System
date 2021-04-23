package accountingsystem.main.web.api;

import accountingsystem.main.model.Manufacturer;
import accountingsystem.main.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

class TestObject {
    private Long earnings;

    public TestObject() {
    }

    public TestObject(Long earnings) {
        this.earnings = earnings;
    }

    public Long getEarnings() {
        return earnings;
    }

    public void setEarnings(Long earnings) {
        this.earnings = earnings;
    }
}


@RestController
@RequestMapping(path = "/api/dashboard")
public class RestDashboardController {
    @GetMapping("/earnings")
    public ResponseEntity<TestObject> getEarnings() {
        TestObject testObject = new TestObject();
        testObject.setEarnings(100000l);
        return ResponseEntity.ok(testObject);
    }
}
