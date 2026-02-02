package dev.projetoostra.projetoostra.product.jewelryType;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class JewelryTypeService {

    private final JewelryTypeRepository jewelryTypeRepository;
    private final JewelryTypeMapper jewelryTypeMapper;

    public JewelryTypeService(JewelryTypeRepository jewelryTypeRepository, JewelryTypeMapper jewelryTypeMapper){
        this.jewelryTypeRepository = jewelryTypeRepository;
        this.jewelryTypeMapper = jewelryTypeMapper;
    }

    public List<JewelryTypeResponseDTO> getAllJewelryTypes(){
        List<JewelryType> lJewelryTypes = jewelryTypeRepository.findAll();

        return lJewelryTypes.stream()
                .map(jewelryTypeMapper::toResponse)
                .collect(Collectors.toList());
    }

    public JewelryTypeResponseDTO getJewelryTypeById(UUID id){
        Optional<JewelryType> jewelryType = jewelryTypeRepository.findById(id);

        return jewelryType.map(jewelryTypeMapper::toResponse).orElse(null);
    }

    public JewelryTypeResponseDTO addJewelryType(JewelryTypeRequestDTO jewelryTypeRequestDTO){
        JewelryType jewelryType = jewelryTypeMapper.toJewelryType(jewelryTypeRequestDTO);

        JewelryType savedJewelryType = jewelryTypeRepository.save(jewelryType);
        return jewelryTypeMapper.toResponse(savedJewelryType);
    }

    public JewelryTypeResponseDTO updateJewelryType(UUID id, JewelryTypeRequestDTO jewelryTypeRequestDTO){
        JewelryType existentJewelryType = jewelryTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de Joia nao encontrado(A)"));

        if (jewelryTypeRequestDTO.getName() != null && !jewelryTypeRequestDTO.getName().isBlank()){
            existentJewelryType.setName(jewelryTypeRequestDTO.getName());
        }
        if (jewelryTypeRequestDTO.getSoldValue() != null){
            existentJewelryType.setSoldValue(jewelryTypeRequestDTO.getSoldValue());
        }
        if (jewelryTypeRequestDTO.getQuantitySold() != null){
            existentJewelryType.setQuantitySold(jewelryTypeRequestDTO.getQuantitySold());
        }

        JewelryType savedJewelryType = jewelryTypeRepository.save(existentJewelryType);

        return jewelryTypeMapper.toResponse(savedJewelryType);
    }

    public void deleteJewelryType(UUID id){
        JewelryType jewelryType = jewelryTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de Joia não encontrada"));

        jewelryTypeRepository.delete(jewelryType);
    }
    
}
