package dev.projetoostra.projetoostra.user.seller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping
    public ResponseEntity<List<SellerResponseDTO>> getSellers() {
        List<SellerResponseDTO> lSellers = sellerService.getAllSellers();
        return ResponseEntity.ok(lSellers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerResponseDTO> getSellerById(@PathVariable UUID id) {
        SellerResponseDTO seller = sellerService.getSellerById(id);
        if (seller != null) {
            return ResponseEntity.ok(seller);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<SellerResponseDTO> addSeller(@RequestBody @Valid SellerRequestDTO sellerRequestDTO) {
        SellerResponseDTO createdSeller = sellerService.addSeller(sellerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSeller);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SellerResponseDTO> updateSeller(@PathVariable UUID id, @RequestBody @Valid SellerRequestDTO sellerRequestDTO) {
        SellerResponseDTO updatedSeller = sellerService.updateSeller(id, sellerRequestDTO);
        if (updatedSeller != null) {
            return ResponseEntity.ok(updatedSeller);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable UUID id) {
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }

}
