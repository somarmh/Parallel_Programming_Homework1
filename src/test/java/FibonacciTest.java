import junit.framework.TestCase;


public class FibonacciTest extends TestCase {

    int n=50;

    public void testFiboPP(){
        long start = System.currentTimeMillis();
        Fibonacci fib = new Fibonacci(n);
        int res = fib.compute();
        long end = System.currentTimeMillis()-start;
        System.out.printf("Fibonacci for %d is %d, and parallel execution took %d ms\n",n,res,end);
//        Fibonacci parallel takes 630 fon n = 40 before enhancement with HashMap
        //We see here after using the HashMap that Parallel programming took more time and the cause
        //behind that is that we only have 40 iterations so when we use Parallel programming
        //it took time to change the contest so it wouldn't be useful here
    }

    public void testFiboSeq(){
        long start = System.currentTimeMillis();
        Fibonacci fib = new Fibonacci(n);
        int res = fib.computeSeq();
        long end = System.currentTimeMillis()-start;
        System.out.printf("Fibonacci for %d is %d, and sequential execution took %d ms %d\n",n,res,end, fib.z);
        //Fibonacci Sequential takes 1378 fon n = 40 before enhancement with HashMap
    }
}
