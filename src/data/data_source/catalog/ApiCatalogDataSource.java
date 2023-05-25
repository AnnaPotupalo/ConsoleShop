package data.data_source.catalog;

import data.models.Product;

import java.util.ArrayList;
import java.util.Comparator;

public class ApiCatalogDataSource extends CatalogDataSource {


    @Override
    public ArrayList<Product> getCatalog(int page, int limit, Comparator<Product> comparator) {
        return null;
    }

    @Override
    public ArrayList<Product> getCatalog(int page, int limit) {
        return null;
    }

    @Override
    public Product productById(String id) {
        return null;
    }
}
