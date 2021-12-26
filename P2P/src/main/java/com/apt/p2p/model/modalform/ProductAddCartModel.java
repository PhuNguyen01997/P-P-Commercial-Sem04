package com.apt.p2p.model.modalform;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductAddCartModel {
    private int quantity;
    private int productId;
}
