package com.btl.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "toanha")
@Getter
@Setter
public class ToaNhaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maTn;

    @Column(name = "TenTn")
    private String tenTn;

    @Column(name = "Socanho")
    private Integer soCanHo;

}
