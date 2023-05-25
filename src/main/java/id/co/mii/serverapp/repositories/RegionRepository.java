package id.co.mii.serverapp.repositories;

import id.co.mii.serverapp.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

    // Cara ke-1 = JPQL
    @Query("select r from Region r where r.name = ?1")
    public List<Region> searchByName(String name);

    // Cara ke-2 = Native Spring
    @Query(value = "SELECT * FROM tb_region WHERE region_name = :name", nativeQuery = true)
    public List<Region> searchByNameNative(String name);

    // Cara ke-3 = Query Method by Spring Boot
    public Boolean existsByName(String name);
}
