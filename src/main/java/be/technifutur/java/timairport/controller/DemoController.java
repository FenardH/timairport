package be.technifutur.java.timairport.controller;

import be.technifutur.java.timairport.model.form.DemoForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/demo")
public class DemoController {

    // Récupérable depuis la requête HTTP:
    // - URL / URI: chemin
    //      - variable de chemin:
    //      - parametre: (ex: http://localhost:8080/machin/truc?param=value)
    // - body: contenu de la requête
    // - method: type de requête envoyée, détermine l'action atendue:
    //      - GET       : récupérer une/pls ressource(s)
    //      - POST      : envoyer/créer une resscource
    //      - PATCH     : modifier partiellement une ressource
    //      - DELETE    : supprimer une ressource
    // - headers: meta-données sur la requête (MultiValueMap: 1 key potentiellement N valeurs)
    // - body: contenu de la requête
    //
    // Dans la réponse HTTP:
    // - status: code représentant succès/échec de la requête
    // - headers: méta-données de la réponse
    // - body: contenu de la réponse

    // Ce qui se trouve dans l'URL
    @GetMapping("/url/{var:[A-Z]{1,3}}")
    public void displayUrlInfo(@PathVariable String var, @RequestParam int param) {
        System.out.println(var); // variable de chemin {var}
        System.out.println(param); // valuer du paramétre param
    }


    @PostMapping(value = "/body", consumes = "application/json")  // consumes verifie la valeur du header
    // "content-type" de la requête (valeur par défaut:"application/json")
    public void displayBody(@RequestBody @Valid DemoForm body) {
        System.out.println(body);
    }

    public void displayHeaders(@RequestHeader("accept") String accept) {
        System.out.println(accept);
    }

    @GetMapping("/param/all")
    public void displayAllParams(@RequestParam Map<String, String> params) {
        params.forEach(
                (key, value) -> System.out.printf("%s : %s\n", key, value)
        );
    }

    @GetMapping("/header/all")
//    public void displayAllHeaders(@RequestHeader MultiValueMap<String, String> headers) {
    public void displayAllHeaders(@RequestHeader HttpHeaders headers) {
        headers.forEach(
                (key, value) -> System.out.printf("%s : %s\n", key, value)
        );
    }

    @GetMapping("/other")
    public void displayRequest(HttpServletRequest request) {
        System.out.println(request.getRequestURI());
        System.out.println(request.getRequestURL());
        System.out.println(request.getMethod());
    }

    // body - return type
    // status
    // headers
    // 'produces' donne une valeur au header "content-type de la réponse
    @GetMapping(value = "/response/basic", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody - pas nécessaire car @RestController
    public String createBasicResponse() {
        return "ma réponse";
    }

    @GetMapping("/response/detailed")
    public ResponseEntity<String> createDetailedReponse() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        return new ResponseEntity<>("ma réponse", headers, HttpStatus.OK)

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body("ma réponse")
//                .build() // si sans .body()
                ;
    }
}
