package data.data_source.catalog;

import data.models.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

// макет каталога данных исходника
public class MockCatalogDataSourceImpl extends CatalogDataSource {
    @Override
    public ArrayList<Product> getCatalog(int page, int limit, Comparator<Product> comparator) {
        ArrayList<Product> products = generateProducts();
        Stream<Product> productStream = products.stream().filter(p -> p.available).sorted(comparator).skip((long)page * limit).limit(limit); // сортировка, фильтрация
        return new ArrayList<>(productStream.toList());
    }

    @Override
    public ArrayList<Product> getCatalog(int page, int limit) {
        ArrayList<Product> products = generateProducts();
        // операции (пагинация, сортировка, фильтрация)
        Stream<Product> productStream = products.stream().filter(p -> p.available).skip((long)page * limit).limit(limit); // пагинация, фильтрация
        return new ArrayList<>(productStream.toList());
    }

    @Override
    public Product productById(String id) {
        ArrayList<Product> products = getCatalog(0, 99999999);
        for (Product p : products) {
            if (p.id.equals(id)) {
                return p;
            }
        }
        return null;
    }

    ArrayList<Product> generateProducts() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("id1", "SmartPhone", "Best phone", 1000, true));
        products.add(new Product("id2", "Laptop", "Some Laptop", 2000, true));
        products.add(new Product("id3", "Watch", "Best Watch", 500, true));
        products.add(new Product("id4", "Phone", "Simple Phone", 100, true));
        for (int i = 0; i < 20; i++) { // создадим еще 20 продуктов // доступность - каждый 4 товар - будем убирать
            products.add(new Product("id" + (i+5), "IPhone-" + i, "Simple Phone", 100 + i*100, i % 4 != 0));
        }
        return products;
    }
}


/*
dataSource
----------
services
--------
controllers // обрабатывает то что ввел пользователь
views // отвечает за то, что будет пользователю показываться

ето товары с индексом от 0
0....30
page 0 limit 10: 0..9
page 1 limit 10: 10..19
page 1 limit 7: 7..13
*/

