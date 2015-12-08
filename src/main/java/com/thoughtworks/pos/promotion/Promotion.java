package com.thoughtworks.pos.promotion;

import com.thoughtworks.pos.domain.CartItem;

public interface Promotion {
    double promotion(CartItem cartItem, double originPrice);
}
