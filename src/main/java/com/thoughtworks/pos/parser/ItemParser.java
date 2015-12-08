package com.thoughtworks.pos.parser;

import com.thoughtworks.pos.domain.Item;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class ItemParser extends Parser<Item> {
    private static final Pattern PATTERN = compile("^(\\w+):(\\d+)$");

    @Override
    protected Item parseLine(final String line) {
        String barcode = line.split(":")[0];
        double price = Double.parseDouble(line.split(":")[1]);
        return new Item(barcode, price);
    }

    @Override
    protected Pattern getPattern() {
        return PATTERN;
    }
}
