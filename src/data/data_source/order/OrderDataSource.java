package data.data_source.order;

import data.models.Order;

public abstract class OrderDataSource {
    public abstract void createOrder(Order order); // формирование заказа

    public abstract Order getOrder(); // получение заказа

}
