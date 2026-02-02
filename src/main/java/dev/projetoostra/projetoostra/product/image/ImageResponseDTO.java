package dev.projetoostra.projetoostra.product.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponseDTO {

    private UUID id;
    private String url;

}
