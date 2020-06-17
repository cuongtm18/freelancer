package com.btl.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "hopdong")
@Getter
@Setter
@NoArgsConstructor
public class HopDongEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maHd;

    @Column(name = "id")
    private Integer id;

    @Column(name = "Ngayky")
    private Date ngayDangKy;

    @Column(name = "Ngaybangiao")
    private Date ngayBanGiao;

    @Column(name = "Giatri")
    private String giaiTri;

    @Column(name = "Tinhtrangthucte")
    private String tinhTrangThucTe;


}
