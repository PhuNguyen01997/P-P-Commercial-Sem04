package com.apt.p2p.model.form;

import com.apt.p2p.model.view.RateModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RateMultiProductForm {
    List<Integer> productId;
    List<RateModel> rates;
}
