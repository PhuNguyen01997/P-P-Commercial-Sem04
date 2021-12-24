package com.apt.p2p.model;

import com.apt.p2p.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressModel {

    @NotNull(message = "Họ và tên không được để trống")
    private User user;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Min(value = 10)
    @Max(value = 11)
    private String number;

    @NotBlank(message = "Phường không được để trống")
    private String ward;

    @NotBlank(message = "Quận không được để trống")
    private String district;

    @NotBlank(message = "Tỉnh/Thành phố không được để trống")
    private String province;
}
