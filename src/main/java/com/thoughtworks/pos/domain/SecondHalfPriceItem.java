package com.thoughtworks.pos.domain;

public final class SecondHalfPriceItem {
    private final String barcode;


    public SecondHalfPriceItem(final String barcode) {
        this.barcode = barcode;
    }

    public String getBarcode() {
        return barcode;
    }

}
