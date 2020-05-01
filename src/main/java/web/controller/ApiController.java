package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.RestServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.List;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("rest")
public class ApiController {

    @Autowired
    public RestServiceImpl restService;


    @GetMapping(path = "list")
    public ResponseEntity<List> listUserGetRestController() {
        ResponseEntity<List> listUsers = restService.getAllUsers();
        return listUsers;
    }

    @PutMapping(path = "update")
    public ResponseEntity<User> updateUserPutRestController(@RequestBody User user) {
        restService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(path = "create")
    public ResponseEntity<User> createUserPostRestController(@RequestBody User user) {
        restService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(path = "read")
    public User readUserGetRestController(HttpSession session) {
        return restService.getUserById((User) session.getAttribute("user"));
    }

    @DeleteMapping(path = "delete")
    public ResponseEntity<List> deleteUserRestController(@RequestBody Long id) {
        restService.deleteUser(id);
        ResponseEntity<List> listUsers = restService.getAllUsers();
        return listUsers;
    }
}
