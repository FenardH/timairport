package be.technifutur.java.timairport.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDTO {
    private String method;
    private String path;
    private String message;
    private int status;
}
