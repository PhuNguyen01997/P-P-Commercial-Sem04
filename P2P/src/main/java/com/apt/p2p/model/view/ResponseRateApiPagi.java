package com.apt.p2p.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseRateApiPagi extends ResponsePagiView{
    private List<RateModel> rates;

    public ResponseRateApiPagi(int index, int total, List<RateModel> rates) {
        super(index, total);
        this.rates = rates;
    }

    public ResponseRateApiPagi(int index, int total) {
        super(index, total);
    }
}
