package com.example.BaiKTJava.service;

import com.example.BaiKTJava.model.PhongBan;
import com.example.BaiKTJava.repository.PhongBanRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PhongBanService {
    private final PhongBanRepository phongBanRepository;

    public List<PhongBan> getAllPhongBan() {
        return phongBanRepository.findAll();
    }

    public Optional<PhongBan> getPhongBanById(Long id) {
        return phongBanRepository.findById(id);
    }

    public void addPhongBan(PhongBan phongBan) {
        phongBanRepository.save(phongBan);
    }

    public void updatePhongBan(@NotNull PhongBan phongBan) {
        PhongBan existingPhongBan = phongBanRepository.findById(phongBan.getId())
                .orElseThrow(() -> new IllegalStateException("Category with ID " +
                        phongBan.getId() + " does not exist."));
        existingPhongBan.setMaPhong(phongBan.getMaPhong());
        existingPhongBan.setTenPhong(phongBan.getTenPhong());
        phongBanRepository.save( existingPhongBan);
    }
    public void deletePhongBanById(Long id) {
        if (! phongBanRepository.existsById(id)) {
            throw new IllegalStateException("Category with ID " + id + " does not exist.");
        }
        phongBanRepository.deleteById(id);
    }
}
