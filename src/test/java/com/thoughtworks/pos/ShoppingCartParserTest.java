package com.thoughtworks.pos;

import com.thoughtworks.pos.domain.CartItem;
import com.thoughtworks.pos.parser.ShoppingCartParser;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ShoppingCartParserTest {
    private ShoppingCartParser parser;

    @Before
    public void setUp() {
        parser = new ShoppingCartParser();
    }

    @Test
    public void should_get_empty_when_given_none() {
        List<CartItem> cartItems = parser.parse(Arrays.<String>asList());
        List<CartItem> expectCartItems = Arrays.<CartItem>asList();
        assertThat(cartItems, is(expectCartItems));
    }

    @Test
    public void should_get_1_cart_item() {
        List<CartItem> cartItems = parser.parse(Arrays.asList("I1-2"));

        assertThat(cartItems.size(), is(1));
        assertThat(cartItems.get(0).getBarcode(), is("I1"));
        assertThat(cartItems.get(0).getQuantity(), is(2));
    }

    @Test
    public void should_get_2_cart_item() {
        List<CartItem> cartItems = parser.parse(Arrays.asList("I1-2", "I2-3"));
        assertThat(cartItems.size(), is(2));
        assertThat(cartItems.get(0).getBarcode(), is("I1"));
        assertThat(cartItems.get(1).getBarcode(), is("I2"));
        assertThat(cartItems.get(0).getQuantity(), is(2));
        assertThat(cartItems.get(1).getQuantity(), is(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_complain_invalid_input_format() {
        parser.parse(Arrays.<String>asList("wahaha"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_complain_invalid_amount() {
        parser.parse(Arrays.<String>asList("T1-2b"));
    }
}
