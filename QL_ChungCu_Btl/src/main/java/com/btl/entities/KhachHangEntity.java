package com.btl.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "khachhang")
@Getter
@Setter
@NoArgsConstructor
public class KhachHangEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maKh;

    @Column(name = "id")
    private int id;

    @Column(name = "TenKH")
    private String tenKhachHang;

    @Column(name = "Sodienthoai")
    private String phone;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "SoCmtnd")
    private String soCMTND;

    @Column(name = "SoTkNganHang")
    private String soTkNganHang;


}
