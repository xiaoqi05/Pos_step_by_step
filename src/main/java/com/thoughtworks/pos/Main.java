package com.thoughtworks.pos;

import com.thoughtworks.pos.domain.CartItem;
import com.thoughtworks.pos.domain.DiscountItem;
import com.thoughtworks.pos.domain.Item;
import com.thoughtworks.pos.domain.SecondHalfPriceItem;
import com.thoughtworks.pos.parser.DiscountItemParser;
import com.thoughtworks.pos.parser.ItemParser;
import com.thoughtworks.pos.parser.SecondHalfPriceParser;
import com.thoughtworks.pos.parser.ShoppingCartParser;
import com.thoughtworks.pos.promotion.DiscountPromotion;
import com.thoughtworks.pos.promotion.Promotion;
import com.thoughtworks.pos.promotion.PromotionManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ItemParser itemParser = new ItemParser();
        List<Item> allItems = itemParser.parse(ShopData.ITEMS_DATA);
        ShoppingCartParser shoppingCartParser = new ShoppingCartParser();
        List<CartItem> cartItems = shoppingCartParser.parse(ShopData.SHOPPING_CART_DATA);
        List<SecondHalfPriceItem> secondHalfPriceItems = new SecondHalfPriceParser().parse(ShopData.SECOND_HALF_PRICE_PROMOTION);


        DiscountItemParser discountItemParser = new DiscountItemParser();
        List<DiscountItem> discountItems = discountItemParser.parse(ShopData.DISCOUNT_ITEMS);
        Map<String, Promotion> promotionMap = new HashMap<>();
        for (DiscountItem item : discountItems) {
            promotionMap.put(item.getBarcode(), new DiscountPromotion(item.getDiscountPrice()));
        }

        for (SecondHalfPriceItem item:secondHalfPriceItems) {
            if (discountItems.contains(item)){
                promotionMap.put(item.getBarcode(),new DiscountPromotion(100d));

            }
           // if ((promotionMap.get(item)==null)){
            //}
        }



        PosMachine posMachine = new PosMachine(allItems, secondHalfPriceItems,new PromotionManager(promotionMap));
        double total = posMachine.calculate(cartItems);

        System.out.println("总价:" + total);
    }
}
