package com.apt.p2p.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class District {
    @Id
    @Column(length = 10)
    private String districtId;

    @NotNull
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provinceId")
    @JsonIgnoreProperties("districts")
    private Province province;

    @OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("district")
    private List<Ward> wards;
}
