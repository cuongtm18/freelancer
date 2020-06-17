package com.btl.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "hoadon")
@Getter
@Setter
@NoArgsConstructor
public class HoaDonEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maGd;

    @Column(name = "id")
    private Integer id;

    @Column(name = "Cacloaichiphi")
    private String cacLoaiChiPhi;

    @Column(name = "NgayGd")
    private Date ngayGiaoDich;

}
