package com.apt.p2p.model.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseModel {
    private Integer[] cartIds;

    private Integer[] shopIds;

    private BigDecimal[] shipping;

    @NotNull
    private Integer addressId;

    private String stripeCardId;

    private Boolean methodPayment;
}
