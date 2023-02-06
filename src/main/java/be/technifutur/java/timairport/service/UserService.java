package be.technifutur.java.timairport.service;

import be.technifutur.java.timairport.model.form.RegistrationForm;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

public interface UserService extends UserDetailsService {
    void register(RegistrationForm form);
}
