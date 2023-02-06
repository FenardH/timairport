package be.technifutur.java.timairport.service;

import be.technifutur.java.timairport.jwt.JWTHolderDTO;
import be.technifutur.java.timairport.model.form.LoginForm;
import be.technifutur.java.timairport.model.form.RegistrationForm;

public interface AuthService {
    public void register(RegistrationForm form);
    public JWTHolderDTO login(LoginForm form);
}
