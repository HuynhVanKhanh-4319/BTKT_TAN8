package com.example.BaiKTJava.controller;

import com.example.BaiKTJava.model.NhanVien;
import com.example.BaiKTJava.service.NhanVienService;
import com.example.BaiKTJava.service.PhongBanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/nhanvien")
public class NhanvienController {
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private PhongBanService phongBanService;

    @GetMapping
    public String showProductList(Pageable pageable, Model model) {
        model.addAttribute("nhanvien", nhanVienService.getAllNhanvien());
        return "/nhanvien/nhanvien-list";
    }
    // For adding a new product
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("nhanvien", new NhanVien());
        model.addAttribute("phongban", phongBanService.getAllPhongBan());
        return "/nhanvien/add-nhanvien";
    }
    @PostMapping("/add")
    public String addnhanvien(@Valid NhanVien nhanVien, BindingResult result) {
        if (result.hasErrors()) {
            return "/nhanvien/add-nhanvien";
        }
        nhanVienService.addNhanVien(nhanVien);
        return "redirect:/nhanvien";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        NhanVien nhanVien = nhanVienService.getNhanVienById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid nhanvien Id:" + id));
        model.addAttribute("nhanvien", nhanVien);
        model.addAttribute("phongban", phongBanService.getAllPhongBan());
        return "/nhanvien/update-nhanvien";
    }
    // Process the form for updating a product
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @Valid NhanVien nhanVien, BindingResult result) {
        if (result.hasErrors()) {
            nhanVien.setId(id); //
            return "/nhanvien/update-nhanvien";
        }

        nhanVienService.updateNhanVien(nhanVien);
        return "redirect:/nhanvien";
    }
    // Handle request to delete a product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {

        nhanVienService.deleteNhanVienById(id);
        return "redirect:/nhanvien";
    }

}
