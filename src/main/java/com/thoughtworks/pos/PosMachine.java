package com.thoughtworks.pos;

import com.thoughtworks.pos.domain.CartItem;
import com.thoughtworks.pos.domain.Item;
import com.thoughtworks.pos.domain.SecondHalfPriceItem;
import com.thoughtworks.pos.promotion.DiscountPromotion;
import com.thoughtworks.pos.promotion.PromotionManager;

import java.util.ArrayList;
import java.util.List;

public final class PosMachine {
    private final List<Item> allItems;

    private com.thoughtworks.pos.promotion.PromotionManager promotionManager;
    private List<SecondHalfPriceItem> halfPriceItems;

    public PosMachine(List<Item> allItems, List<SecondHalfPriceItem> halfPriceItems, PromotionManager promotionManager) {
        this.allItems = allItems;
        this.promotionManager = promotionManager;
        this.halfPriceItems = halfPriceItems;
    }

    public PosMachine(final List<Item> allItems) {
        this.allItems = allItems;
        promotionManager = null;
    }


    public double calculate(final List<CartItem> cartItems) {
        double total = 0;
        for (CartItem cartItem : cartItems) {
            total += calculateSubtotal(cartItem);
            System.out.print("total" + total);
        }
        return total;
    }

    private double calculateSubtotal(final CartItem cartItem) {
        String barcode = cartItem.getBarcode();
        double originPrice = queryItemPrice(barcode);//// TODO: 15-12-9  
        if (promotionManager.getAvailablePromotion(barcode) != null) {
            return promotionManager.promotion(barcode, cartItem, originPrice);
        } else {
            //// TODO: 15-12-9  
            return cartItem.getQuantity() * originPrice-originPrice/2;
        }

    }

    private double queryItemPrice(final String barcode) {
        for (Item item : allItems) {
            if (item.getBarcode().equals(barcode)) {
                return item.getPrice();
            }
        }

        throw new IllegalArgumentException("unknown item");
    }

}
