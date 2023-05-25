package view;

import common.AppView;
import data.models.CartItem;
import data.service.ShopService;

import java.util.ArrayList;

public class CartView extends AppView {
    final ShopService shopService;


    public CartView(ShopService shopService) {
        super("Корзина", new ArrayList<>());
        this.shopService = shopService;
    }

    @Override
    public void action() {      // это контроллер
        ArrayList<CartItem> cart = shopService.getCart();
        for (CartItem cartItem : cart) {
            System.out.println(cartItem.product.id + " " + cartItem.product.title + " " + cartItem.count);
        }
        System.out.println();
    }
}




























