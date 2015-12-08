package com.thoughtworks.pos;

import com.thoughtworks.pos.domain.DiscountItem;
import com.thoughtworks.pos.domain.Item;
import com.thoughtworks.pos.parser.DiscountItemParser;
import com.thoughtworks.pos.parser.ItemParser;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class DiscountItemParserTest {

    private DiscountItemParser parser;
    private ItemParser itemParser;

    @Before
    public void setUp() {
        parser = new DiscountItemParser();
        itemParser = new ItemParser();
    }

    @Test
    public void should_get_empty_goods_given_none() {
        List<DiscountItem> items = parser.parse(Arrays.<String>asList());
        List<DiscountItem> expectedItems = asList();
        assertThat(items, is(expectedItems));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_complain_invalid_input() {
        parser.parse(asList("blabla"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_complain_invalid_price() {
        parser.parse(asList("blabla:d8"));
    }


    @Test
    public void should_get_right_discount() throws Exception {
        List<DiscountItem> items = parser.parse(asList("I1:75"));
        List<Item> items1 = itemParser.parse(asList("T1:4"));
        assertThat(items.get(0).getBarcode(), is("I1"));
        assertThat(items1.get(0).getBarcode(),is("T1"));
        assertEquals(items1.get(0).getPrice(),4,1e-6);
        assertEquals(items.get(0).getDiscountPrice(), 75, 1e-6);
        Double discountPrice = parser.getDiscount(items1.get(0));
        assertEquals(discountPrice,Double.valueOf(items.get(1).getDiscountPrice()*items1.get(1).getPrice()));
    }
}
