package com.example.pos_backend.controller;

import com.example.pos_backend.dto.StoreRequestDTO;
import com.example.pos_backend.dto.StoreResponseDTO;
import com.example.pos_backend.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    public ResponseEntity<StoreResponseDTO> createStore(@RequestBody StoreRequestDTO requestDTO) {
        return ResponseEntity.ok(storeService.createStore(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreResponseDTO> updateStore(@PathVariable String id,
                                                        @RequestBody StoreRequestDTO requestDTO) {
        return ResponseEntity.ok(storeService.updateStore(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable String id) {
        storeService.deleteStore(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreResponseDTO> getStoreById(@PathVariable String id) {
        return ResponseEntity.ok(storeService.getStoreById(id));
    }

    @GetMapping("/merchant/{merchantId}")
    public ResponseEntity<List<StoreResponseDTO>> getStoresByMerchantId(@PathVariable String merchantId) {
        return ResponseEntity.ok(storeService.getStoresByMerchantId(merchantId));
    }

    @GetMapping
    public ResponseEntity<List<StoreResponseDTO>> getAllStores() {
        return ResponseEntity.ok(storeService.getAllStores());
    }
}
