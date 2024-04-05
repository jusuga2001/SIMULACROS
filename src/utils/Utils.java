package utils;

import java.util.List;

public class Utils {
    public static <T> T[] fromListToArray(List<T> list){
        T[] array = (T[]) new Object[list.size()];
        int i = 0;
        for (T iterador:  list){
            array[i++] = iterador;
        }
        return array;
    }
}
