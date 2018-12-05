package CodingPart;

import java.util.*;

/**
 * Created by yuqishi on 10/25/18.
 * For example, Given 2d vector = [ [1,2], [3], [4,5,6] ]
    By calling next repeatedly until hasNext returns false,
    the order of elements returned by next should be: [1,2,3,4,5,6].

        • boolean hasNext() return true if there is another element in the set
        • int next() return the value of the next element in the array
        • void remove()
             o remove the last element returned by the iterator.
             o That is, remove the element that the previous next() returned.
             o This method can be called only once per call to next(), otherwise an exception will be
             thrown.
 */
public class List_2DIterator {

    int i;
    int j;
    List<List<Integer>> list;
    int removeNum;
    public List_2DIterator(List<List<Integer>> list) {
        i = 0;
        j = 0;
        this.list = list;
        removeNum = -1;
    }
    public int next() {
        if (hasNext()) {
            return list.get(i).get(j++);
        }
        return -1;
    }

    public void remove() {
        if (j == 0){
            List<Integer> prevList = list.get(i - 1);
            prevList.remove(prevList.size() - 1);
            if (prevList.size() == 0) {
                list.remove(i - 1);
                i--;
            }
        } else {
            list.get(i).remove(j - 1);
            if (list.get(i).size() == 0) {
                list.remove(i);
                j = 0;
            } else {
                j--;
            }
        }
    }

    public boolean hasNext() {
        while (i < list.size()) {
            if (j < list.get(i).size()) {
                return true;
            }
            i++;
            j = 0;
        }
        return false;
    }

    public static void main(String[] argus) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1,2,3));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(4));
        List<Integer> list3 = new ArrayList<>(Arrays.asList(5,6));
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);

        List_2DIterator li = new List_2DIterator(lists);
        System.out.println(li.hasNext()); //true
        System.out.println(li.next()); // 1
        li.remove();
        System.out.println(li.next()); //2
        System.out.println(li.next()); //3
        System.out.println(li.next()); //4
        li.remove();
        System.out.println(li.next()); //5
        System.out.println(li.hasNext()); //true
        System.out.println(li.next()); //6
        System.out.println(li.hasNext()); //false
        li.remove();


    }
}
