package com.thoughtworks.pos.domain;

public final class Item {
    private final String barcode;
    private final double price;

    public Item(final String barcode, final double price) {
        this.barcode = barcode;
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public double getPrice() {
        return price;
    }
}
