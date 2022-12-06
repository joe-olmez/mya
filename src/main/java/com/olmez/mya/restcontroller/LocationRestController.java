package com.olmez.mya.restcontroller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olmez.mya.model.Location;
import com.olmez.mya.services.LocationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loc")
public class LocationRestController extends BaseRestController {

    private final LocationService locationService;

    // CREATE = POST
    @PostMapping("/add")
    public boolean addLocation(@RequestBody Location location) {
        return locationService.addLocation(location);
    }

    // READ = GET
    @GetMapping("/all")
    public List<Location> getLocations() {
        return locationService.getLocations();
    }

    // UPDATE = PUT
    @PutMapping("/update/{locId}")
    public Location updateLocation(@PathVariable("locId") long id, @RequestBody Location model) {
        return locationService.updateLocation(id, model);
    }

    // DELETE = DELETE
    @DeleteMapping("/delete/{locId}")
    public boolean deleteLocation(@PathVariable("locId") Long id) {
        return locationService.deleteLocation(id);
    }

    //
    @GetMapping("/{locId}")
    public Location getLocationById(@PathVariable("locId") long id) {
        return locationService.getLocationById(id);
    }

}
