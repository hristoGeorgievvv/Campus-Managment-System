package nl.tudelft.oopp.demo.controllers;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;
import nl.tudelft.oopp.demo.entities.FoodMenu;
import nl.tudelft.oopp.demo.repositories.FoodMenuRepository;
import nl.tudelft.oopp.demo.repositories.FoodVendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@RestController
public class FoodMenuController implements CommandLineRunner {

    @Autowired
    FoodMenuRepository foodMenuRepository;

    @Autowired
    FoodVendorRepository foodVendorRepository;

    public FoodMenuController(FoodMenuRepository rep) {
        foodMenuRepository = rep;
    }

    private HashMap<String, List<FoodMenu>> hashMap = new HashMap<String, List<FoodMenu>>();

    /**
     * get all FoodMenus in the database.
     * @return a list of all FoodMenus
     */
    @GetMapping("All_FoodMenus")
    @ResponseBody
    public Object sendFoodMenus() {
        List<FoodMenu> list = foodMenuRepository.findAll();
        return list;
    }

    /**
     * get all FoodMenus based on vendorname.
     * @return a list of all FoodMenus
     */
    @GetMapping("menu/byVendor/{name}")
    @ResponseBody
    public Object sendFoodMenusByBuilding(@PathVariable(value = "name") String name) {
        List<FoodMenu> list = hashMap.get(name);
        if (list == null || list.isEmpty()) {
            return list;
        }
        return list;
    }

    @Override
    public void run(String... args) throws Exception {
        List<String> vendors = foodVendorRepository.getVendorNames();

        for (String vendorname : vendors) {
            hashMap.put(vendorname,
                    foodMenuRepository.findFoodMenuByFoodVendorVendorName(vendorname));
        }
    }
}
