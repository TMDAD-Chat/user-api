package es.unizar.tmdad.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationDto {
    
    private String name;
    private String email;
    private String photoUri;

}
