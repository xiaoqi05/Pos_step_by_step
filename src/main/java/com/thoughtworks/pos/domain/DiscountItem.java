package com.thoughtworks.pos.domain;

public final class DiscountItem {
    private final String barcode;
    private final double discount;

    public DiscountItem(final String barcode, final double discount) {
        this.barcode = barcode;
        this.discount = discount;
    }

    public String getBarcode() {
        return barcode;
    }

    public double getDiscountPrice() {
        return discount;
    }
}
