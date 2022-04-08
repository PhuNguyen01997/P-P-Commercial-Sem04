package com.apt.p2p.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponsePagiView {
    private int index;
    private int total;
    private List<Integer> pagiList;
    private int next;
    private int prev;

    public ResponsePagiView(int index, int total) {
        this.index = index;
        this.total = total;
        calculatePagiList(index, total);
        this.next = index == total - 1 ? index : index + 1;
        this.prev = index == 0 ? index : index - 1;
    }

    private void calculatePagiList(int index, int total){
        if (index + 1 > total) {
            throw new IllegalArgumentException("Index is > Pagi length");
        }
        List<Integer> result = new ArrayList<>();
        int showRange = 2;
        int borderStart = showRange;
        int borderEnd = total - showRange - 1;
        if (borderStart > borderEnd) {
            for (int i = 0; i <= total - 1; i++) {
                result.add(i);
            }
        } else if (borderStart <= index && index <= borderEnd) {
            for (int i = index - showRange; i < index; i++) {
                result.add(i);
            }
            for (int i = index; i <= index + showRange; i++) {
                result.add(i);
            }
        } else if (index >= borderEnd) {
            for (int i = total - 1; i > total - 1 - ((showRange * 2) + 1); i--) {
                result.add(0, i);
            }
        } else if (index <= borderStart) {
            for (int i = 0; i < ((showRange * 2) + 1); i++) {
                result.add(i);
            }
        }

        this.pagiList = result;
    }
}
