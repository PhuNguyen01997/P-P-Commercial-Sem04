package com.apt.p2p.model.form;

import javax.validation.constraints.NotNull;
import java.util.List;

public class PurchaseModel {
    private List<Integer> cartIds;

    @NotNull
    private Integer addressId;

    private Integer paymentId;
}
