package be.technifutur.java.timairport.config;

import be.technifutur.java.timairport.jwt.JwtAuthentificationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationObservationConvention;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;
import java.util.Objects;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthentificationFilter jwtFilter) throws Exception {

        // configuration de securité
        http.csrf().disable();
        http.httpBasic().disable();
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.authorizeHttpRequests().requestMatchers("/plane/add").permitAll().anyRequest().authenticated();
//        http.authorizeHttpRequests().anyRequest().permitAll();

        /*
        *  Les premiers matchers ont la priorité
        *  anyRequest, s'il est mis, doit être le dernier matcher
        *
        *
        *  ReauestMatchers:
        *    Lambda RequestMatchers:
        *       - prend une HttpServletRequest en parm, renvoi un boolean
        *
        *    Methods:        *
        *       - une valeur de l'enum HttpMethod
        *
        *    Patterns:
        *       - ? : remplace une lettre
        *       - * : n'importe quelle valeur dans 1 segment
        *       - {machin:regex} : n'importe quelles valeurs coresspondant au pattern regex pour 1 segment
        *       - ** : n'importe quelle valeur dans 0 à N segment (seulement en dernier segment)
        *
        *  Authorization:
        *       -permitAll()       tout le monde passe
        *       -denyAll()         personne passe
        *       -authenticated()   les users authentifiés passent
        *       -anonymous()
        *       -hasAuthority()
        *       -hasAnyAuthority(... ?)
        *       -hasRole(?)
        *       -hasAnyRole(... ?)
        *
        *       une Authority c'est un droit sous forme de String (plus un droit à une action)
        *       un Role c'est une Authority qui commence par 'Role' (qui est l'utilisateur pour mon app)
        *
        *       auth: "ROLE_TRUC"  ->  role:"TRUC"
        *       auth: "MACHIN"  ->  (/) role
        *
        */

        http.authorizeHttpRequests((authorize) -> {
                authorize
                        .requestMatchers(HttpMethod.POST, "/auth/*").anonymous()
                        // via lambda
                        .requestMatchers(request -> request.getRequestURI().length() > 50).hasRole("ADMIN")
                        // via HttpMethod
//                        .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
                        // égale à la façon au-dessous
//                        .requestMatchers(request -> Objects.equals(request.getMethod(), "POST")).hasRole("ADMIN")
                        // via mapping d'URI
                        .requestMatchers("/plane/all").anonymous()
                        .requestMatchers("/plane/add").authenticated()
                        .requestMatchers("/plane/{id:[0-9]+}/?pdate").hasRole("ADMIN")//.hasAuthority("ROLE_ADMIN")
                        // via HttpMethod + mapping d'URI
                        .requestMatchers(HttpMethod.GET,"/plane/*").hasAnyRole("ROLE", "ADMIN")
                        // .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN"
//                        .requestMatchers(HttpMethod.GET,"/flight/*").hasAnyRole("ROLE", "ADMIN")
//                        .requestMatchers(HttpMethod.POST,"/flight/*").hasAnyRole( "ADMIN")
//                        .requestMatchers(HttpMethod.PATCH,"/flight/*").hasAnyRole( "ADMIN")
//                        .requestMatchers(HttpMethod.DELETE,"/flight/*").hasAnyRole("ADMIN")
                        .anyRequest().permitAll();
                });

        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        List<UserDetails> users = List.of(
//                User.builder()
//                        .username("user")
//                        .password(encoder.encode("pass"))
//                        .roles("USER")
//                        .build(),
//                User.builder()
//                        .username("admin")
//                        .password(encoder.encode("pass"))
//                        .roles("ADMIN", "USER")
//                        .build()
//        );
//
//        users.forEach(u -> System.out.println(u.getPassword()));
//
//        return new InMemoryUserDetailsManager(users);
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
