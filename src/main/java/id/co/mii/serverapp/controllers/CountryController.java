package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.models.dto.request.CountryRequest;
import id.co.mii.serverapp.services.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // JSON return w/ Rest Controller
@RequestMapping("/country") // Set country as endpoint
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class CountryController {
    private CountryService countryService;

    @GetMapping // HTTP Method Get
    public List<Country> getAll(){
        return countryService.getAll();
    }

    @GetMapping("/{id}") // HTTP Method Get
    public Country getById(@PathVariable Integer id){
        return countryService.getById(id);
    }

    // w/out DTO
    @PostMapping // HTTP Method Post
    public Country create(@RequestBody Country country){
        return countryService.create(country);
    }

    // w/ DTO
    @PostMapping("/dto") // HTTP Method Post
    public Country createWithDTO(@RequestBody CountryRequest countryRequest){
        return countryService.createWithDTO(countryRequest);
    }

    // w/ ModelMapper
    @PostMapping("/dto-mm")
    public Country createWithDTOModelMapper(@RequestBody CountryRequest countryRequest){
        return countryService.createWithDTOModelMapper(countryRequest);
    }

    @PutMapping("/{id}")
    public Country update(@PathVariable Integer id,@RequestBody Country country){
        return countryService.update(id, country);
    }

    @DeleteMapping("/{id}")
    public Country delete(@PathVariable Integer id){
        return countryService.delete(id);
    }
}
