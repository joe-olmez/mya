package com.olmez.mya.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.olmez.mya.model.Location;

public interface LocationRepository extends BaseObjectRepository<Location> {

	@Query("SELECT t FROM Location t WHERE t.name = ?1 AND t.deleted = false")
	List<Location> findByName(String locationName);

	@Query("SELECT t FROM Location t WHERE t.latitude = ?1 AND t.longitude = ?2 AND t.deleted = false")
	List<Location> findByLatAndLong(Double latitude, Double longitude);

	@Query("SELECT t FROM Location t WHERE (t.latitude <=?2 AND t.latitude >=?1) AND (t.longitude <=?4 AND t.longitude >=?3) AND t.deleted = false")
	List<Location> findByLatAndLongRange(Double minlatitude, Double maxlatitude, Double minLongitude,
			Double maxLongitude);
}
