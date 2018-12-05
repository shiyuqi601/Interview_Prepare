package CodingPart;
import java.util.*;
/**
 * Created by yuqishi on 10/24/18.
 */
public class QueueWithFixedLengthArray {

    private int count;
    private int maxLength;
    private List<Object> tail;
    private List<Object> head;
    private int i;
    private int j;

    public QueueWithFixedLengthArray(int maxLength) {
        this.count = 0;
        this.maxLength = maxLength;
        this.head = new ArrayList<>();
        this.tail = this.head;
        this.i = 0;
        this.j = 0;
    }

    private void offer(int num) {
        if (j < maxLength - 1) {
            tail.add(num);
        } else {
            List<Object> temp = new ArrayList<>();
            temp.add(num);
            tail.add(temp);
            tail = temp;
            j = 0;
        }
        count++;
        j++;
    }

    private Integer poll(){
        if (count == 0) {
            return null;
        }
        if (i == maxLength - 1) {
            head = (List<Object>)head.get(i);
            i = 0;
        }
        count--;
        return (int)head.get(i++);
    }

    private int getSize(){
        return count;
    }

    public static void main(String[] argus) {
        QueueWithFixedLengthArray qw = new QueueWithFixedLengthArray(3);
        System.out.println(qw.poll());
        for (int i = 1; i <= 7; i++) {
            qw.offer(i);
        }
        System.out.println("=================");
        for (int i = 0; i < 5; i++) {
            System.out.println(qw.poll());
        }
        System.out.println("=================");
        qw.offer(8);
        System.out.println(qw.getSize());
        System.out.println("=================");
        for (int i = 0; i < qw.getSize(); i++) {
            System.out.println(qw.poll());
        }
    }

//    public static void main(String args[])
//    {
//        QueueWithFixedLengthArray queue = new QueueWithFixedLengthArray(5);
//        System.out.println(queue.poll());//null
//        queue.offer(1);
//        queue.offer(1);
//        queue.offer(2);
//        queue.offer(3);
//
//        queue.offer(4);
//        queue.offer(5);
//        queue.offer(6);
//        System.out.println(queue.poll());//1
//        System.out.println(queue.poll());//1
//        System.out.println(queue.poll());//2
//        System.out.println(queue.poll());//3
//        System.out.println(queue.poll());//4
//        queue.offer(7);
//        System.out.println("size: " + queue.getSize());//size:3
//        System.out.println(queue.poll());//5
//        System.out.println(queue.poll());//6
//        System.out.println(queue.poll());//7
//        System.out.println(queue.poll());//null
//    }

}
