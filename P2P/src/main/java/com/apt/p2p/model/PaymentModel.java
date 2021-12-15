package com.apt.p2p.model;

import com.apt.p2p.common.DateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
public class PaymentModel {
    private Integer id;

    @NotBlank(message = "Họ tên không thể trống")
    private String fullname;

    @NotBlank(message = "Số thẻ không thể trống")
    private String number;

    @Enumerated(EnumType.STRING)
    private String type;

    @JsonDeserialize(using = DateDeserializer.class)
    @NotNull(message = "Due date can't be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date due;

    @NotNull(message = "CVV can't be empty")
    @Min(value = 1, message = "CVV is not valid (ex: XXX)")
    @Max(value = 999, message = "CVV is not valid (ex: XXX)")
    private Integer cvv;

    @NotBlank(message = "Address can't be empty")
    private String addressRegister;

    @NotNull(message = "Postal Code can't be empty")
    private Integer postalCode;

    private Integer shopId;

    private ShopModel shop;

    @NotNull
    private Integer userId;

    private UserModel user;
}
