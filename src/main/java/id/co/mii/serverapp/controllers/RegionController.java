package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.services.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // JSON return w/ Rest Controller
@RequestMapping("/region") // Set region as endpoint
@AllArgsConstructor
@PreAuthorize("hasRole('USER')")
public class RegionController {
    private RegionService regionService;

    /* initialize RegionService sebagai penampung sementara, namun tidak jadi dipakai karena sudah ada Lombok AllArgsConstructor */
    /*
    public RegionController(RegionService regionService){
        this.regionService = regionService;
    }
    */

    @GetMapping // HTTP Method Get
    public List<Region> getAll(){
        return regionService.getAll();
    }

    @GetMapping("/{id}") // HTTP Method Get
    public Region getById(@PathVariable Integer id){
        return regionService.getById(id);
    }
    // http://localhost:9000/region/id (adalah sebuah PATH)
    // http://localhost:9000/region?id=1 (adalah sebuah Parameter)
    // Gunakan PATH saat sudah mengetahui 'id', namun gunakan PARAM saat ingin mencari 'name'

    @PostMapping // HTTP Method Post (create)
    public Region create(@RequestBody Region region){
        return regionService.create(region);
    }

    @PutMapping("/{id}") // HTTP Method PUT (update)
    public Region update(@PathVariable Integer id,@RequestBody Region region){
        return regionService.update(id, region);
    }

    @DeleteMapping("/{id}") // HTTP Method Delete
    public Region delete(@PathVariable Integer id){
        return regionService.delete(id);
    }

    // JPQL menggunakan paramater
    @GetMapping("/jpql")
    public List<Region> searchByName(@RequestParam(name = "name") String name){
        return regionService.searchByName(name);
    }

    // Native menggunakan PATH
    @GetMapping("/ntv/{name}")
    public List<Region> searchByNameNative(@PathVariable String name){
        return regionService.searchByNameNative(name);
    }
}
