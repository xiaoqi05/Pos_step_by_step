package com.thoughtworks.pos.domain;

public final class CartItem {
    private final String barcode;
    private final Integer quantity;

    public CartItem(final String barcode, final Integer quantity) {
        this.barcode = barcode;
        this.quantity = quantity;
    }

    public String getBarcode() {
        return barcode;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
