package com.thoughtworks.pos.promotion;

import com.thoughtworks.pos.domain.CartItem;

public class DiscountPromotion implements Promotion {
    private double discountRatio;

    public DiscountPromotion( double discountRatio) {
        this.discountRatio = discountRatio/100d;
    }


    @Override
    public double promotion(CartItem cartItem,double originPrice) {
        if (cartItem.getQuantity()>1){
            return cartItem.getQuantity() * originPrice * discountRatio-originPrice*discountRatio/2;
        }else {
            return cartItem.getQuantity() * originPrice * discountRatio;
        }
    }


}
