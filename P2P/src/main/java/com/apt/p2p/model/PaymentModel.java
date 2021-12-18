package com.apt.p2p.model;

import com.apt.p2p.common.DateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.util.Date;

@Data
public class PaymentModel {
    private Integer id;

    @NotBlank(message = "Họ tên không thể trống")
    private String fullname;

    @Pattern(regexp = "^[\\d\\s]+$", message = "Mã thẻ không hợp lệ")
    @NotBlank(message = "Mã thẻ không thể trống")
    private String number;

    @NotBlank(message = "Loại thẻ không thể trống")
    @Enumerated(EnumType.STRING)
    private String type;

    @JsonDeserialize(using = DateDeserializer.class)
    @NotNull(message = "Ngày hết hạn không thể trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date due;

    @NotNull(message = "CVV không thể trống")
    @Min(value = 1, message = "CVV không hợp lệ (ex: XXX)")
    @Max(value = 999, message = "CVV không hợp lệ (ex: XXX)")
    private Integer cvv;

    @NotBlank(message = "Địa chỉ không thể trống")
    private String addressRegister;

    @Pattern(regexp = "^[\\d\\s]+$", message = "Postal code không hợp lệ")
    @NotNull(message = "Postal code không thể trống")
    private String postalCode;

    private Integer shopId;

    private ShopModel shop;

    private Integer userId;

    private UserModel user;
}
