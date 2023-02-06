package be.technifutur.java.timairport.controller;

import be.technifutur.java.timairport.model.form.RegistrationForm;
import be.technifutur.java.timairport.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid RegistrationForm form) {
        userService.register(form);
    }
}
