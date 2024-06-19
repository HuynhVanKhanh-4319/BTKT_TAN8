package com.example.BaiKTJava.service;

import com.example.BaiKTJava.model.NhanVien;
import com.example.BaiKTJava.repository.NhanVienRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class NhanVienService {
    private final NhanVienRepository nhanvienRepository;
    public List<NhanVien> getAllNhanvien() {
        return nhanvienRepository.findAll();
    }
    public Optional<NhanVien> getNhanVienById(Long id) {
        return nhanvienRepository.findById(id);
    }
    public NhanVien addNhanVien(NhanVien nhanvien) {
        return nhanvienRepository.save(nhanvien);
    }
    public NhanVien updateNhanVien(@NotNull NhanVien nhanVien) {
        NhanVien existingNhanVien =nhanvienRepository.findById(nhanVien.getId())
                .orElseThrow(() -> new IllegalStateException("Product with ID " +
                        nhanVien.getId() + " does not exist."));
        existingNhanVien.setTenNv(nhanVien.getTenNv());
        existingNhanVien.setMaNv(nhanVien.getMaNv());
        existingNhanVien.setPhai(nhanVien.getPhai());
        existingNhanVien.setLuong(nhanVien.getLuong());
        existingNhanVien.setNoiSinh(nhanVien.getNoiSinh());
        existingNhanVien.setPhongBan(nhanVien.getPhongBan());

        return nhanvienRepository.save(existingNhanVien);
    }
    public void deleteNhanVienById(Long id) {
        if (!nhanvienRepository.existsById(id)) {
            throw new IllegalStateException("Product with ID " + id + " does not exist.");
        }
        nhanvienRepository.deleteById(id);
    }
}
