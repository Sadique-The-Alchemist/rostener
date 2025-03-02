import java.util.ArrayList;
import java.util.List;

class DevideAndConquer {
    List<Integer> merge_sort(List<Integer> arr) {
        if (arr.size() <= 1) {
            return arr;
        }
        int mid = arr.size() / 2;
        List<Integer> left = merge_sort(arr.subList(0, mid));
        List<Integer> right = merge_sort(arr.subList(mid, arr.size()));

        return merge(left, right);
    }

    List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < left.size() & j < right.size()) {
            if (left.get(i) < right.get(j)) {
                result.add(left.get(i));
                i++;
            } else {
                result.add(right.get(j));
                j++;
            }
        }
        result.addAll(left.subList(i, left.size()));
        result.addAll(right.subList(j, right.size()));
        return result;
    }

    public static void main(String[] args) {
        List<Integer> arr = List.of(3, 5, 1, 0, 2, 10, 17, 12, 13);
        System.out.print("List: ");
        System.out.println(arr);
        DevideAndConquer dc = new DevideAndConquer();
        List<Integer> sortedArray = dc.merge_sort(arr);
        System.out.println(sortedArray);
    }
}
