import common.AppView;
import common.PageLoop;
import data.comparators.AppComparator;
import data.comparators.PriceComparator;
import data.data_source.cart.CartDataSource;
import data.data_source.cart.MockCartDataSourceImpl;
import data.data_source.catalog.CatalogDataSource;
import data.data_source.catalog.MockCatalogDataSourceImpl;
import data.data_source.order.MockOrderDataSourceImpl;
import data.data_source.order.OrderDataSource;
import data.models.Product;
import data.service.ShopService;
import view.*;

import java.util.ArrayList;

public class MainShop {
    public static void main(String[] args) {
        CartDataSource cartDataSource = new MockCartDataSourceImpl();
        CatalogDataSource catalogDataSource = new MockCatalogDataSourceImpl();
        OrderDataSource orderDataSource = new MockOrderDataSourceImpl();
        ShopService shopService = new ShopService(catalogDataSource, cartDataSource, orderDataSource);

//        AppView addToCartView = new AddToCartView(shopService, new ArrayList<>());
        AppView addToCartView = new AddToCartView(shopService);

        ArrayList<AppView> catalogChildren = new ArrayList<>();
        catalogChildren.add(addToCartView);
        ArrayList<AppComparator<Product>> catalogComparators = new ArrayList<>();
        catalogComparators.add(new AppComparator<>(new PriceComparator(true), "по возрастанию цены"));
        catalogComparators.add(new AppComparator<>(new PriceComparator(false), "по убыванию цены"));
        AppView catalogView = new CatalogView(shopService, catalogChildren, catalogComparators);

        AppView cartView = new CartView(shopService);
        AppView orderView = new OrderView(shopService);

        ArrayList<AppView> mainChildren = new ArrayList<>();
        mainChildren.add(catalogView);
        mainChildren.add(cartView);
        mainChildren.add(orderView);
        AppView mainView = new MainView(mainChildren);

        new PageLoop(mainView).run();

//        System.out.println(shopService.getCatalog());
//        System.out.println(shopService.getCart());
//        System.out.println(shopService.addToCart(shopService.getCatalog().get(0).id, 5));
//        System.out.println(shopService.addToCart("454616465", 5));
//        System.out.println(shopService.getCart());

    }
}

/*
models:
Product - id, title, description, price, available
CartItem - Product, count
Order - name, phone, address, paymentType, deliveryTime, List<CartItem> cart

Фичи:
    - просмотр каталога
        - добавление в корзину по id
            - сколько штук
    - просмотр корзины
    - оформить заказ
    - ввод данных

Какую фичу вы выберете? // за это отвечает AppView
(1) - вариант1
(2) - вариант2
(3) - назад

Введите данные
свободный ввод

 */
