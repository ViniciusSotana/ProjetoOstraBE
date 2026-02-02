package dev.projetoostra.projetoostra.product.jewelryType;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/jewelrytypes")
public class JewelryTypeController {

    private final JewelryTypeService jewelryTypeService;

    public JewelryTypeController(JewelryTypeService jewelryTypeService) {
        this.jewelryTypeService = jewelryTypeService;
    }

    @GetMapping
    public ResponseEntity<List<JewelryTypeResponseDTO>> getJewelryTypes() {
        List<JewelryTypeResponseDTO> lJewelryTypes = jewelryTypeService.getAllJewelryTypes();
        return ResponseEntity.ok(lJewelryTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JewelryTypeResponseDTO> getJewelryTypeById(@PathVariable UUID id) {
        JewelryTypeResponseDTO jewelryType = jewelryTypeService.getJewelryTypeById(id);
        if (jewelryType != null) {
            return ResponseEntity.ok(jewelryType);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<JewelryTypeResponseDTO> addJewelryType(@RequestBody @Valid JewelryTypeRequestDTO jewelryTypeRequestDTO) {
        JewelryTypeResponseDTO createdJewelryType = jewelryTypeService.addJewelryType(jewelryTypeRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdJewelryType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JewelryTypeResponseDTO> updateJewelryType(@PathVariable UUID id, @RequestBody @Valid JewelryTypeRequestDTO jewelryTypeRequestDTO) {
        JewelryTypeResponseDTO updatedJewelryType = jewelryTypeService.updateJewelryType(id, jewelryTypeRequestDTO);
        if (updatedJewelryType != null) {
            return ResponseEntity.ok(updatedJewelryType);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJewelryType(@PathVariable UUID id) {
        jewelryTypeService.deleteJewelryType(id);
        return ResponseEntity.noContent().build();
    }
    
}
