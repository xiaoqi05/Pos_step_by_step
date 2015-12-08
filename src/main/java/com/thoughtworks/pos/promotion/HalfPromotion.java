package com.thoughtworks.pos.promotion;

import com.thoughtworks.pos.domain.CartItem;

public class HalfPromotion implements Promotion {
    private double discountRatio;

    public HalfPromotion(double discountRatio) {
        this.discountRatio = discountRatio / 100d;
    }


    @Override
    public double promotion(CartItem cartItem, double originPrice) {
        return cartItem.getQuantity() * originPrice * discountRatio;
    }


}
