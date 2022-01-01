package com.apt.p2p.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

public class Province {
    @Id
    @Column(length = 10)
    private String provinceId;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "province", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("province")
    private List<District> districts;
}