package com.example.pos_backend.service;

import com.example.pos_backend.dto.StoreRequestDTO;
import com.example.pos_backend.dto.StoreResponseDTO;
import com.example.pos_backend.entity.Store;
import com.example.pos_backend.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public interface StoreService {
    StoreResponseDTO createStore(StoreRequestDTO requestDTO);
    StoreResponseDTO updateStore(String id, StoreRequestDTO requestDTO);
    void deleteStore(String id);
    StoreResponseDTO getStoreById(String id);
    List<StoreResponseDTO> getStoresByMerchantId(String merchantId);
    List<StoreResponseDTO> getAllStores();
}

@Service
@RequiredArgsConstructor
class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    private StoreResponseDTO mapToDTO(Store store) {
        return StoreResponseDTO.builder()
                .id(store.getId())
                .merchantId(store.getMerchantId())
                .storeName(store.getStoreName())
                .address(store.getAddress())
                .timezone(store.getTimezone())
                .status(store.getStatus())
                .taxRate(store.getTaxRate())
                .currency(store.getCurrency())
                .businessHours(store.getBusinessHours())
                .createdAt(store.getCreatedAt())
                .createdBy(store.getCreatedBy())
                .updatedAt(store.getUpdatedAt())
                .updatedBy(store.getUpdatedBy())
                .isDeleted(store.getIsDeleted())
                .build();
    }

    private void updateEntity(Store store, StoreRequestDTO dto) {
        store.setMerchantId(dto.getMerchantId());
        store.setStoreName(dto.getStoreName());
        store.setAddress(dto.getAddress());
        store.setTimezone(dto.getTimezone());
        store.setStatus(dto.getStatus());
        store.setTaxRate(dto.getTaxRate());
        store.setCurrency(dto.getCurrency());
        store.setBusinessHours(dto.getBusinessHours());
        store.setUpdatedAt(Instant.now());
        store.setUpdatedBy(dto.getUpdatedBy());
    }

    @Override
    public StoreResponseDTO createStore(StoreRequestDTO requestDTO) {
        Store store = new Store();
        store.setId(UUID.randomUUID().toString());
        updateEntity(store, requestDTO);
        store.setCreatedAt(Instant.now());
        store.setCreatedBy(requestDTO.getCreatedBy());
        store.setIsDeleted(false);
        return mapToDTO(storeRepository.save(store));
    }

    @Override
    public StoreResponseDTO updateStore(String id, StoreRequestDTO requestDTO) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found"));
        updateEntity(store, requestDTO);
        return mapToDTO(storeRepository.save(store));
    }

    @Override
    public void deleteStore(String id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found"));
        store.setIsDeleted(true);
        store.setUpdatedAt(Instant.now());
        storeRepository.save(store);
    }

    @Override
    public StoreResponseDTO getStoreById(String id) {
        return storeRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Store not found"));
    }

    @Override
    public List<StoreResponseDTO> getStoresByMerchantId(String merchantId) {
        return storeRepository.findByMerchantIdAndIsDeleted(merchantId, false).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StoreResponseDTO> getAllStores() {
        return storeRepository.findByIsDeleted(false).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}
