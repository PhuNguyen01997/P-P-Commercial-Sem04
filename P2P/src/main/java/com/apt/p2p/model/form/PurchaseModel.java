package com.apt.p2p.model.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseModel {
    private Integer[] cartIds;

    @NotNull
    private Integer addressId;

    private Integer paymentId;

    private Boolean methodPayment;
}
