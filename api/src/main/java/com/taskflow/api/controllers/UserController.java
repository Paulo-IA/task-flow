package com.taskflow.api.controllers;

import com.taskflow.api.domain.user.User;
import com.taskflow.api.domain.user.UserRequestDTO;
import com.taskflow.api.domain.user.UserResponseDTO;
import com.taskflow.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<User> createUser(@RequestParam("name") String name,
                                           @RequestParam("email") String email,
                                           @RequestParam("image")MultipartFile image
                                           ) {
        UserRequestDTO userRequestDTO = new UserRequestDTO(name, email, image);
        User newUser = this.userService.createUser(userRequestDTO);

        return ResponseEntity.ok(newUser);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "20") int size) {
        List<UserResponseDTO> allUsers = userService.getUsers(page, size);

        return ResponseEntity.ok(allUsers);
    }
}
