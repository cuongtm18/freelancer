package com.btl.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tinhtrangsdcanho")
@Getter
@Setter
@NoArgsConstructor
public class TinhTrangSuDungCanHoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maCh;

    @Column(name = "Tinhtrang")
    private Integer tinhTrang;

    @Column(name = "ThoigianHd")
    private Date thoiGianHd;

}
