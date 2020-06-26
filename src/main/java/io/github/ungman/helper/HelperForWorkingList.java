package io.github.ungman.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class HelperForWorkingList {
    public static <T> Collection<T> symmetricDifference(Collection<T> a, Collection<T> b) {
        Collection<T> result = new ArrayList<>(a);
        result.addAll(b);
        Collection<T> intersection = new ArrayList<>(a);
        intersection.retainAll(b);
        result.removeAll(intersection);
        return result;
    }

    public static <T> T getRandomStringFromList(List<T> from) {
        Random rand = new Random();
        return from.get(rand.nextInt(from.size()));
    }

    public static <T> T getNotUsedElement(List<T> from, List<T> used) {
        ArrayList<T> unusedGoods = (ArrayList<T>) HelperForWorkingList.symmetricDifference(from, used);
        return HelperForWorkingList.getRandomStringFromList(unusedGoods);
    }
}