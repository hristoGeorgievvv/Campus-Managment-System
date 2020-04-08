package nl.tudelft.oopp.demo.controllers;

import java.util.List;
import javax.transaction.Transactional;
import nl.tudelft.oopp.demo.entities.FoodVendor;
import nl.tudelft.oopp.demo.repositories.FoodVendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@RestController
public class FoodVendorController {

    @Autowired
    FoodVendorRepository foodVendorRepository;

    public FoodVendorController(FoodVendorRepository rep) {
        foodVendorRepository = rep;
    }

    /**
     *Get mapping for the vendors in the repo.
     * @return list of vendors
     */
    @GetMapping("All_FoodVendors")
    @ResponseBody
    public Object sendFoodVendors() {
        List<FoodVendor> list = foodVendorRepository.findAll();
        return list;
    }
}
