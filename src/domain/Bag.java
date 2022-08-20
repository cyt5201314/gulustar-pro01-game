package domain;

import java.util.List;
import java.util.Map;

/**
 *背包类
 */
public class Bag {
    private Map<String,Items> itemList;
       class Items {
          private Item item;
          private int  number;
    }
}
