package com.btl.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "taikhoan")
@Getter
@Setter
@NoArgsConstructor
public class TaiKhoanEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "HoVaTen")
    private String fullName;

    @Column(name = "TenTaiKhoan")
    private String userName;

    @Column(name = "MatKhau")
    private String passWord;

    @Column(name = "VaiTro")
    private Integer role;

    @Column(name = "Email")
    private String email;

    @Column(name = "SDT")
    private String phone;

    @Column(name = "TrangThai")
    private Integer status;
}
