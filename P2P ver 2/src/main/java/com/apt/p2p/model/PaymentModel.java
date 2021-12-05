package com.apt.p2p.model;

import com.apt.p2p.common.DateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
public class PaymentModel {
    private Integer id;

    @NotBlank(message = "Fullname can't be empty")
    private String fullname;

    @NotBlank(message = "Number can't be empty")
    private String number;

    private  Integer type;

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

    private Integer userId;
}
