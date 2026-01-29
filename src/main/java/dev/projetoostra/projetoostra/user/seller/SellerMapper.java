package dev.projetoostra.projetoostra.user.seller;

import org.springframework.stereotype.Component;

@Component
public class SellerMapper {

    public Seller toSeller(SellerRequestDTO sellerRequestDTO){
        Seller seller = new Seller();
        seller.setAddress(sellerRequestDTO.getAddress());
        seller.setName(sellerRequestDTO.getName());
        seller.setCpf(sellerRequestDTO.getCpf());
        seller.setCommission(sellerRequestDTO.getCommission());
        seller.setSoldValue(sellerRequestDTO.getSoldValue());

        return seller;
    }

    public SellerResponseDTO toResponse(Seller seller){
        SellerResponseDTO sellerResponseDTO = new SellerResponseDTO();
        sellerResponseDTO.setId(seller.getId());
        sellerResponseDTO.setAddress(seller.getAddress());
        sellerResponseDTO.setCommission(seller.getCommission());
        sellerResponseDTO.setCpf(seller.getCpf());
        sellerResponseDTO.setSoldValue(seller.getSoldValue());
        sellerResponseDTO.setName(seller.getName());

        return sellerResponseDTO;
    }

}
