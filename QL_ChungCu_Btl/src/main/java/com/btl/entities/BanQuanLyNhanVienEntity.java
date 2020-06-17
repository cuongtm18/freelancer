package com.btl.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "banquanlynhanvien")
@Getter
@Setter
@NoArgsConstructor
public class BanQuanLyNhanVienEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "TenNv")
    private String tenNhanVien;

    @Column(name = "Sodienthoai")
    private String phone;
}
