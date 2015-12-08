package com.thoughtworks.pos.promotion;

import com.thoughtworks.pos.domain.CartItem;

import java.util.Map;

public class PromotionManager {
    private Map<String, Promotion> promotionMap;

    public PromotionManager(Map<String, Promotion> promotionMap) {

        this.promotionMap = promotionMap;
    }

    public Promotion getAvailablePromotion(String barcode) {
        return this.promotionMap.get(barcode);
    }

    public double promotion(String barcode,CartItem cardItem, double originPrice) {
          return   promotionMap.get(barcode).promotion(cardItem,originPrice);
    }









}
