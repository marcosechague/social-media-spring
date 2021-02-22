package py.com.mechague.springsocial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import py.com.mechague.springsocial.entity.User;
import py.com.mechague.springsocial.exception.ResourceNotFoundException;
import py.com.mechague.springsocial.repository.UserRepository;
import py.com.mechague.springsocial.security.CurrentUser;
import py.com.mechague.springsocial.security.UserPrincipal;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}