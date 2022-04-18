package com.apt.p2p.model.view;

import com.apt.p2p.common.DateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardModel {
    private Integer id;

    private String stripeCardId;

    @NotBlank(message = "Name cannot be empty")
    private String fullname;

    @Pattern(regexp = "^[\\d\\s]+$", message = "Invalid card code")
    @NotBlank(message = "Card code cannot be empty")
    private String number;

    private String last4;

    @NotNull(message = "Please select card type")
    private String type;

    @JsonDeserialize(using = DateDeserializer.class)
    @NotNull(message = "Expiration date cannot be empty")
    @DateTimeFormat(pattern = "yyyy-MM")
    private Date due;

    @NotNull(message = "CVV cannot be empty")
    @Pattern(regexp = "^[\\d\\s]+$", message = "Invalid cvv")
    private String cvv;

    @NotBlank(message = "Address cannot be empty")
    private String addressRegister;

    @Pattern(regexp = "^[\\d\\s]+$", message = "Postal code illegal")
    @NotNull(message = "Postal code can't be empty")
    private String postalCode;

    private String imgUrl;

    private ShopModel shop;

    private UserModel user;

    private List<OrderModel> orders;
}
