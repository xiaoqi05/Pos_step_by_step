package com.thoughtworks.pos.parser;

import com.thoughtworks.pos.domain.SecondHalfPriceItem;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class SecondHalfPriceParser extends Parser<SecondHalfPriceItem> {
    private static final Pattern PATTERN = compile("^(\\w+)(\\d+)$");

    @Override
    protected SecondHalfPriceItem parseLine(final String line) {
        return new SecondHalfPriceItem(line);
    }

    @Override
    protected Pattern getPattern() {
        return PATTERN;
    }
}
