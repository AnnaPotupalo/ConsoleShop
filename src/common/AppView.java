package common;

import data.comparators.AppComparator;

import java.util.ArrayList;

public abstract class AppView { // страницы которые, выводятся
   public final String title; // название страницы
   public final ArrayList<AppView> children; // дочерние страницы
   public int nowPage = 0; // текущая страница
   public boolean hasNextPage = false;
   public final ArrayList<AppComparator> availableComparator = new ArrayList<>();
   public AppComparator selectedComparator;

    protected AppView(String title, ArrayList<AppView> children) {
        this.title = title;
        this.children = children;
    }

    public void action() { // то, что произойдет когда мы переходим на эту страницу

    }

}
