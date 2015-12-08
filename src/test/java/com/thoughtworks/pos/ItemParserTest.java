package com.thoughtworks.pos;

import com.thoughtworks.pos.domain.Item;
import com.thoughtworks.pos.parser.ItemParser;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ItemParserTest {

    private ItemParser parser;

    @Before
    public void setUp() {
        parser = new ItemParser();
    }

    @Test
    public void should_get_empty_goods_given_none() {
        List<Item> items = parser.parse(Arrays.<String>asList());
        List<Item> expectedItems = asList();
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
    public void should_get_1_good() {
        List<Item> items = parser.parse(asList("I1:40"));
        assertThat(items.get(0).getBarcode(), is("I1"));
        assertEquals(items.get(0).getPrice(), 40, 1e-6);
    }

    @Test
    public void should_get_2_goods() {
        List<Item> items = parser.parse(asList("I1:40", "I2:30"));
        assertThat(items.get(0).getBarcode(), is("I1"));
        assertThat(items.get(1).getBarcode(), is("I2"));
        assertEquals(items.get(0).getPrice(), 40, 1e-6);
        assertEquals(items.get(1).getPrice(), 30, 1e-6);
    }
}
