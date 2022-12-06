package com.olmez.mya.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olmez.mya.model.Location;
import com.olmez.mya.repositories.LocationRepository;
import com.olmez.mya.services.LocationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locRepository;

    @Override
    @Transactional
    public List<Location> getLocations() {
        return locRepository.findAll();
    }

    @Override
    @Transactional
    public boolean addLocation(Location newLocation) {
        if (newLocation == null) {
            return false;
        }
        newLocation = locRepository.save(newLocation);
        return newLocation.getId() != null;
    }

    @Override
    @Transactional
    public Location getLocationById(Long locId) {
        if (locId == null) {
            return null;
        }
        return locRepository.getById(locId);
    }

    @Override
    @Transactional
    public boolean deleteLocation(Long locId) {
        Location existing = getLocationById(locId);
        if (existing == null) {
            return false;
        }
        locRepository.deleted(existing);
        log.info("Deleted {}", existing);
        return true;
    }

    @Override
    @Transactional
    public Location updateLocation(Long id, Location givenLoc) {
        Location existing = getLocationById(id);
        if (existing == null) {
            return null;
        }

        copy(givenLoc, existing);
        locRepository.save(existing);
        log.info("Updated! {}", existing);
        return existing;
    }

    private Location copy(Location source, Location target) {
        target.setName(source.getName());
        target.setLatitude(source.getLatitude());
        target.setLongitude(source.getLongitude());
        target.setPostCode(source.getPostCode());
        target.setStreetNumber(source.getStreetNumber());
        target.setStreetName(source.getStreetName());
        target.setCity(source.getCity());
        target.setProvince(source.getProvince());
        target.setCountry(source.getCountry());
        target.setGoogleAddress(source.getGoogleAddress());
        target.setGoogleAddress(source.getGoogleAddress());
        target.setTimeZone(source.getTimeZone());
        target.setDescription(source.getDescription());
        target.setWeatherUpdatedOn(source.getWeatherUpdatedOn());
        return target;
    }

}
