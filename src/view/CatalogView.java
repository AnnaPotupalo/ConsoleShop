package view;

import common.AppView;
import common.Paginable;
import data.comparators.AppComparator;
import data.comparators.PriceComparator;
import data.models.Product;
import data.service.ShopService;

import java.util.ArrayList;

public class CatalogView extends AppView implements Paginable {
    final ShopService shopService;

    public CatalogView(ShopService shopService, ArrayList<AppView> children, ArrayList<AppComparator<Product>> comparators) {
        super("Каталог", children);
        this.shopService = shopService;
        this.availableComparator.addAll(comparators);
        if (!availableComparator.isEmpty()) {
            selectedComparator = availableComparator.get(0);
        }
    }

    @Override
    public void action() {
        PriceComparator comparator = new PriceComparator();
        comparator.isAsc = false;
        ArrayList<Product> catalog = shopService.getCatalog(nowPage, pageLimit, selectedComparator.comparator);
        hasNextPage = catalog.size() == pageLimit;
//       if (catalog.size() < pagelimit) { // тоже самое что и предыдущая строка
//           hasNextPage = false;
//       } else {
//           hasNextPage = true;
//       }
        for (Product product : catalog) {
            System.out.println(product.id + " " + product.title + " " + product.price);
        }
        System.out.println();
    }
}














