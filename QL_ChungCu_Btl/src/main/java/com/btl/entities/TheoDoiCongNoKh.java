package com.btl.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "theodoicongnokh")
@Getter
@Setter
@NoArgsConstructor
public class TheoDoiCongNoKh implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maKh;

    @Column(name = "CongnoKh")
    private Integer congNoKh;


}
