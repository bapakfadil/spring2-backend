package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.request.UserRequest;
import id.co.mii.serverapp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    /*
    @PostMapping
    public User create(@RequestBody UserRequest userRequest) {
        return userService.create(userRequest);
    }
    */

    @PutMapping("/{id}")
    public User update(@PathVariable Integer id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public User delete(@PathVariable Integer id) {
        return userService.delete(id);
    }

}
