import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class NumberCount extends RecursiveAction{
    int arr[], lo, hi;
    int elem;
    long count;

    public NumberCount(int [] arr, int lo, int hi, int elem) {
        this.arr = arr;
        this.lo = lo;
        this.hi = hi;
        this.elem = elem;
    }

    //Q1 - 1
    public long computeSeq(){
        for (int i = lo; i <= hi; i++){
            if(arr[i] == elem){
                count++;
            }
        }
        return count;
    }

    //Q1 - 2
    @Override
    public void compute(){
        if (hi - lo > 1_000_000) {
            int mid = (lo + hi) / 2;
            NumberCount left = new NumberCount(arr, lo, mid, elem);
            NumberCount right = new NumberCount(arr, mid + 1, hi, elem);
            left.fork();
            right.compute();
            left.join();
            count = left.count + right.count;
        }
        else{
            for(int i = lo; i <= hi; i++){
                if(arr[i] == elem)
                    count++;
            }
        }
    }

    //Q1 - 3 Sequential
    public void computeStreamSeq() {
        count = Arrays.stream(arr).filter(i -> i == elem).count();
    }

    //Q1 - 3 Parallel
    public void computeStreamPar() {
        count = Arrays.stream(arr).asLongStream().parallel().filter(i -> i == elem).count();
    }
}
