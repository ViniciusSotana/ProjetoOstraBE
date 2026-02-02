package dev.projetoostra.projetoostra.product.jewelryType;

import org.springframework.stereotype.Component;

@Component
public class JewelryTypeMapper {

    public JewelryType toJewelryType(JewelryTypeRequestDTO jewelryTypeRequestDTO){
        JewelryType jewelryType = new JewelryType();
        jewelryType.setName(jewelryTypeRequestDTO.getName());
        jewelryType.setQuantitySold(jewelryTypeRequestDTO.getQuantitySold());
        jewelryType.setSoldValue(jewelryTypeRequestDTO.getSoldValue());

        return jewelryType;
    }

    public JewelryTypeResponseDTO toResponse(JewelryType jewelryType){
        JewelryTypeResponseDTO jewelryTypeResponseDTO = new JewelryTypeResponseDTO();
        jewelryTypeResponseDTO.setId(jewelryType.getId());
        jewelryTypeResponseDTO.setName(jewelryType.getName());
        jewelryTypeResponseDTO.setQuantitySold(jewelryType.getQuantitySold());
        jewelryTypeResponseDTO.setSoldValue(jewelryType.getSoldValue());

        return jewelryTypeResponseDTO;
    }

}
