import junit.framework.TestCase;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class NumberCountTest extends TestCase{

    private int[] intStream(int size){
        IntStream intStream = IntStream.range(1, size);
        return intStream.toArray();
    }

    private int[] randomArray(int size){
        Random rand = new Random();
        int[] arr = new int[size];
        for(int i = 0; i < size; i++){
            arr[i] = rand.nextInt(size);
        }
        return arr;
    }
    //Q1 - 1
    public void testArrayCountSeq(){
        int size = 1000_000_000;
        int[] arr = randomArray(size);

        NumberCount array = new NumberCount(arr, 0, arr.length - 1, 10);
        long start = System.currentTimeMillis();
        long count = array.computeSeq();
        long endTimer = System.currentTimeMillis() - start;
        System.out.printf("Sequential Time execution for Random Array of size %d is %d and the number %d appears %d times in the array\n", size, endTimer, 10, count);
    }
    //Q1 - 2
    public void testArrayCountPP(){
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","7");
        int size = 1000_000_000;
        int [] arr = randomArray(size);
        NumberCount array = new NumberCount(arr, 0, arr.length - 1, 10);
        long start = System.currentTimeMillis();
        ForkJoinPool.commonPool().invoke(array);
        long endTimer = System.currentTimeMillis() - start;
        System.out.printf("Sequential Time execution for Random Array of size %d is %d and the number %d appears %d times in the array\n", size, endTimer, 10, array.count);
    }

    //Q1 - 3 sequential
    public void testArrayCountStreamSeq() {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","7");
        int size = 1000_000_000;
        int [] arr = randomArray(size);
        NumberCount array = new NumberCount(arr, 0, arr.length - 1, 10);
        long start = System.currentTimeMillis();
        array.computeStreamSeq();
        long endTimer = System.currentTimeMillis() - start;
        System.out.printf("Sequential Time execution for Random Array of size %d is %d and the number %d appears %d times in the array\n", size, endTimer, 10, array.count);
    }

    //Q1 - 3 sequential
    public void testArrayCountStreamPar() {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","7");
        int size = 1000_000_000;
        int [] arr = randomArray(size);
        NumberCount array = new NumberCount(arr, 0, arr.length - 1, 10);
        long start = System.currentTimeMillis();
        array.computeStreamPar();
        long endTimer = System.currentTimeMillis() - start;
        System.out.printf("Sequential Time execution for Random Array of size %d is %d and the number %d appears %d times in the array\n", size, endTimer, 10, array.count);
    }
}
