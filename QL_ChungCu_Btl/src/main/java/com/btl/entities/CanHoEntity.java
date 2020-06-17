package com.btl.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "canHo")
@Getter
@Setter
@NoArgsConstructor
public class CanHoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maCh;

    @Column(name = "id")
    private int id;

    @Column(name = "TenCh")
    private String tenCanHo;

    @Column(name = "Sotang")
    private Integer soTang;

    @Column(name = "Sophong")
    private Integer soPhong;

}
