package br.com.criptosys.controller;

import br.com.criptosys.domain.entity.UserDE;
import br.com.criptosys.dto.UserDTO;
import br.com.criptosys.service.UserService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigInteger;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody UserDTO userDTO){
        UserDE user = this.userService.save(this.userService.mapDE(userDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getUserId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserDE>> findAll(){
        return ResponseEntity.ok(this.userService.findAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<UserDE>> findById(@PathVariable @NotNull BigInteger id){
        return ResponseEntity.ok(this.userService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull BigInteger id){
        this.userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
