package com.thoughtworks.pos.parser;

import com.thoughtworks.pos.domain.CartItem;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class ShoppingCartParser extends Parser<CartItem> {
    private static final Pattern PATTERN = compile("^(\\w+)-(\\d+)$");

    @Override
    protected CartItem parseLine(final String line) {
        String[] splitLine = line.split("-");
        String barcode = splitLine[0];
        int quantity = Integer.parseInt(splitLine[1]);
        return new CartItem(barcode, quantity);
    }

    @Override
    protected Pattern getPattern() {
        return PATTERN;
    }
}
