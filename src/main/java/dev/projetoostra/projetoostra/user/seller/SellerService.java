package dev.projetoostra.projetoostra.user.seller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;

    public SellerService(SellerRepository sellerRepository, SellerMapper sellerMapper){
        this.sellerRepository = sellerRepository;
        this.sellerMapper = sellerMapper;
    }

    public List<SellerResponseDTO> getAllSellers(){
        List<Seller> lSellers = sellerRepository.findAll();

        return lSellers.stream()
                .map(sellerMapper::toResponse)
                .collect(Collectors.toList());
    }

    public SellerResponseDTO getSellerById(UUID id){
        Optional<Seller> seller = sellerRepository.findById(id);

        return seller.map(sellerMapper::toResponse).orElse(null);
    }

    public SellerResponseDTO addSeller(SellerRequestDTO sellerRequestDTO){
        Seller seller = sellerMapper.toSeller(sellerRequestDTO);

        Seller savedSeller = sellerRepository.save(seller);
        return sellerMapper.toResponse(savedSeller);
    }

    public SellerResponseDTO updateSeller(UUID id, SellerRequestDTO sellerRequestDTO){
        Seller existentSeller = sellerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vendedora nao encontrada"));

        if (sellerRequestDTO.getCpf() != null && !sellerRequestDTO.getCpf().isBlank()){
            existentSeller.setCpf(sellerRequestDTO.getCpf());
        }
        if (sellerRequestDTO.getName() != null && !sellerRequestDTO.getName().isBlank()){
            existentSeller.setName(sellerRequestDTO.getName());
        }
        if (sellerRequestDTO.getCommission() != null){
            existentSeller.setCommission(sellerRequestDTO.getCommission());
        }
        if (sellerRequestDTO.getSoldValue() != null){
            existentSeller.setSoldValue(sellerRequestDTO.getSoldValue());
        }
        if (sellerRequestDTO.getAddress() != null && !sellerRequestDTO.getAddress().isBlank()){
            existentSeller.setAddress(sellerRequestDTO.getAddress());
        }

        Seller savedSeller = sellerRepository.save(existentSeller);

        return sellerMapper.toResponse(savedSeller);
    }

    public void deleteSeller(UUID id){
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vendedora não encontrada"));

        sellerRepository.delete(seller);
    }

}
