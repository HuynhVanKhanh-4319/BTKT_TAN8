package com.example.BaiKTJava.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NhanVien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String MaNv;
    @Column
    private String TenNv;
    @Column
    private String Phai;
    @Column
    private double Luong;
    @Column
    private String NoiSinh;
    @ManyToOne
    @JoinColumn(name = "Ma_Phong")
    private PhongBan phongBan;
}
