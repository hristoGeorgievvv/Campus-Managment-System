package nl.tudelft.oopp.demo.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import nl.tudelft.oopp.demo.entities.ClassRoom;
import nl.tudelft.oopp.demo.repositories.BuildingReposity;
import nl.tudelft.oopp.demo.repositories.ClassRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ClassroomController implements CommandLineRunner {


    private HashMap<String, List<ClassRoom>> hashMap = new HashMap<String, List<ClassRoom>>();

    @Autowired
    ClassRoomRepository rep;

    @Autowired
    BuildingReposity buildingReposity;


    /**.
     * Constructor used in testing
     * @param rep ClassRoomRepository
     * @param buildingReposity BuildingRepository
     */
    public ClassroomController(ClassRoomRepository rep, BuildingReposity buildingReposity) {
        this.rep = rep;
        this.buildingReposity = buildingReposity;
    }

    public HashMap<String, List<ClassRoom>> getHashMap() {
        return this.hashMap;
    }

    /**
     * GET Endpoint to retrieve all classrooms.
     *
     * @return list of all {@link ClassRoom} in the repository, or "No classrooms Found".
     */

    @GetMapping("classroom/all")
    @ResponseBody
    public Object sendClassrooms() {
        List<ClassRoom>  classList = rep.findAll();
        if (classList.isEmpty()) {
            return "No Classrooms Found!";
        }
        return classList;
    }



    /**
     * GET Endpoint to retrieve all classRooms per Building.
     *
     * @return list of all {@link ClassRoom}
     by building in the repository, or "No classrooms Found".
     */

    @GetMapping("classrooms/byBuilding/{name}")
    @ResponseBody
    public Object sendClassroomsByBuildingId(@PathVariable(value = "name") String name) {

        List<ClassRoom> result = hashMap.get(name);
        if (result == null || result.isEmpty()) {
            return "No Classrooms Found!";
        }
        return result;
    }

    /**
     * returns a list of all classrooms.
     * @return a list of all existing classrooms in the database
     */
    public List<ClassRoom> getAllClassrooms() {
        List<ClassRoom>  classList = rep.findAll();
        return classList;
    }

    @Override
    public void run(String... args) throws Exception {
        List<String> buildings = buildingReposity.getBuildingNames();
        List<ClassRoom> classList = rep.findAll();

        for (String building: buildings) {
            hashMap.put(building,
                    rep.findClassRoomByBuildingBuildingName(building));
        }
    }
}
