package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.repositories.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/* Tidak menggunakan void karena ingin return object */
@Service // Menandai sebuah file bahwa file ini berkategori Service pada Spring
@AllArgsConstructor // Otomatisasi penulisan semua Args constructor oleh Lombok
public class RegionService {
    private RegionRepository regionRepository;

    /* initialize RegionRepository sebagai penampung sementara, namun tidak jadi dipakai karena sudah ada Lombok AllArgsConstructor */
    /*
    public RegionService(RegionRepository regionRepository){
        this.regionRepository = regionRepository;
    }
    */

    // Show all data from table
    public List<Region> getAll(){
        return regionRepository.findAll();
    }

    // Show data from table by id
    public Region getById(Integer id){
        return regionRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Region not found!!!"));
    }

    // Create new data into database table
    public Region create(Region region){
        if (regionRepository.existsByName(region.getName())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Region name already exists!"
            );
        }
        return regionRepository.save(region);
    }

    // Edit existing data in database table
    public Region update(Integer id, Region region){
        getById(id);
        region.setId(id);
        return regionRepository.save(region);
    }

    // Delete existing data in database table
    public Region delete(Integer id){
        Region region = getById(id);
        regionRepository.delete(region);
        return region;
    }

    // JPQL
    public List<Region> searchByName(String name){
        return regionRepository.searchByName(name);
    }

    // Native
    public List<Region> searchByNameNative(String name){
        return regionRepository.searchByNameNative(name);
    }
}

