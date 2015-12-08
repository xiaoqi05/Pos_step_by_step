package com.thoughtworks.pos.parser;

import com.thoughtworks.pos.domain.DiscountItem;
import com.thoughtworks.pos.domain.Item;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * 打折条目解析类
 */
public class DiscountItemParser extends Parser<DiscountItem> {
    private DiscountItem discountPrice;
    private static final Pattern PATTERN = compile("^(\\w+):(\\d+)$");

    @Override
    protected DiscountItem parseLine(final String line) {
        String barcode = line.split(":")[0];
        double price = Double.parseDouble(line.split(":")[1]);
        discountPrice = new DiscountItem(barcode, price);
        return discountPrice;
    }

    @Override
    protected Pattern getPattern() {
        return PATTERN;
    }


    public Double getDiscount(Item discountItem) {
        return discountItem.getPrice() * discountPrice.getDiscountPrice();
    }
}
