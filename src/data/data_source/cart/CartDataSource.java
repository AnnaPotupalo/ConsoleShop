package data.data_source.cart;

import data.models.CartItem;
import data.models.Product;

import java.util.ArrayList;

public abstract class CartDataSource {

    public abstract void addToCart(Product product, int count); // добавляем в корзину
    public abstract ArrayList<CartItem> getCart(); // получаем из корзины

}
