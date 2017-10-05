package collections;

/**
 * Created by Amanda Curyło on 22.09.2017.
 */
public class colTest {
    public static void main(String [] args) {
//        MinPQ minPQ = new MinPQ(10);
//        minPQ.insert(2);
//        minPQ.insert(6);
//        minPQ.insert(3);
//        minPQ.insert(4);
//        minPQ.insert(10);
//        minPQ.insert(0);
//        minPQ.insert(5);
//        minPQ.insert(8);
//        System.out.println(minPQ.delMin());
//        System.out.println(minPQ.delMin());
//        System.out.println(minPQ.delMin());
//        System.out.println(minPQ.delMin());
//        System.out.println(minPQ.delMin());
//        System.out.println(minPQ.delMin());
//        System.out.println(minPQ.delMin());
//        System.out.println(minPQ.delMin());

//        Bag<Integer> bag = new Bag();
//        bag.add(12);
//        bag.add(2);
//        bag.add(6);
//        bag.add(5);
//        bag.add(21);
//        for (Integer i : bag)
//        {
//            System.out.println(i);
//        }

//        FIFOqueue<Integer> fifOqueue = new FIFOqueue<>();
//        fifOqueue.enqueue(3);
//        fifOqueue.enqueue(32);
//        fifOqueue.enqueue(13);
//        fifOqueue.enqueue(30);
//        fifOqueue.enqueue(4);
//        fifOqueue.enqueue(1);
//        fifOqueue.enqueue(2);
//        for (Integer i : fifOqueue)
//        {
//            System.out.println(i);
//        }
//        System.out.println(fifOqueue.dequeue());
//        System.out.println(fifOqueue.dequeue());

//        LIFOqueue<Integer> lifOqueue = new LIFOqueue<>();
//        lifOqueue.push(2);
//        lifOqueue.push(23);
//        lifOqueue.push(42);
//        lifOqueue.push(1);
//        lifOqueue.push(5);
//        lifOqueue.push(9);
//        for (Integer i : lifOqueue)
//        {
//            System.out.println(i);
//        }
//        System.out.println(lifOqueue.pop());
//        System.out.println(lifOqueue.pop());
//        for (Integer i : lifOqueue)
//        {
//            System.out.println(i);
//        }



        IndexMinPQ<Double> indexMinPQ = new IndexMinPQ<>(5);
        indexMinPQ.insert(2, 0.4);
        indexMinPQ.insert(3, 0.9);
        indexMinPQ.insert(5, 0.41);
//        indexMinPQ.delete(2);
        System.out.println(indexMinPQ.delMin());
        System.out.println(indexMinPQ.delMin());
        System.out.println(indexMinPQ.delMin());
        System.out.println(indexMinPQ.delMin());



    }

}
