package com.apt.p2p.model.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawForm {
    private int shopId;

    @DecimalMin(value = "999", inclusive = false, message = "Số tiền cần rút không hợp lệ")
    private BigDecimal amount;
}
