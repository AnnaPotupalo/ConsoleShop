package common;

import java.util.Scanner;

public class PageLoop { // класс который, определяет логику и позволяет управлять и выводить страницы
    final AppView view;

    int getChildrenSize() {
        return view.children.size();
    }

    int getOptionalsSize() { // проверить если страницы уже закончились - нужен ли компаратор
        int optionalSize = 0;
        if (view.hasNextPage) optionalSize++;
        optionalSize += view.availableComparator.size();
        return optionalSize;
    }

    public PageLoop(AppView view) {
        this.view = view;
    }

    public void run() {         // прибавляем везде 1 - потому что 1 - "назад"
        while (true) {
            view.action();      // в цикле делает action если он есть, он есть всегда, но по умолчанию он пустой
            displayChildren(); // после этого выводит дочерние виджеты - дочерние страницы
            final int fullSize = getChildrenSize() + getOptionalsSize() + 1; // для кнопки вперед/назад -
            Scanner in = new Scanner(System.in);    // cколько страниц с учетом чилдренов и сколько мы можем еще добавить
            int value = in.nextInt();
            if (value < 0 || value > fullSize) {        // выводит страницу или нужный пункт
                System.out.println("Неверное значение страницы");
            } else if (value == fullSize) {
                break;
            } else if ((value - 1) < getChildrenSize()) {
                AppView selectedView = view.children.get(value - 1);
                new PageLoop(selectedView).run();
            } else {
                if ((value - 1) == getChildrenSize() && view.hasNextPage) {
                    view.nowPage++;
                    new PageLoop(view).run();
                } else {
                    view.nowPage = 0;
                    int comparatorIndex = value - getChildrenSize() -1 - (view.hasNextPage ? 1 : 0);
                    view.selectedComparator = view.availableComparator.get(comparatorIndex);
                    new PageLoop(view).run();
                }
            }
        }
    }


    public void displayChildren() { // выводим все страницы
        int currentIndex = 1;
        System.out.println(view.title);
        System.out.println("Выберите вариант (от 1 до " + (getChildrenSize() + 1) + ")");
        for (int i = 0; i < getChildrenSize(); i++) {
            AppView _view = view.children.get(i);
            System.out.println(currentIndex + " - " + _view.title);
            currentIndex++;
        }
        if (view.hasNextPage) {
            System.out.println(currentIndex + " - " + "Следующая страница");
            currentIndex++;
        }
        for (int i = 0; i < view.availableComparator.size(); i++) {
            System.out.println(currentIndex + " - " + view.availableComparator.get(i).name);
            currentIndex++;
        }
        System.out.println((getChildrenSize() + getOptionalsSize() + 1) + " - назад");
    }

}









