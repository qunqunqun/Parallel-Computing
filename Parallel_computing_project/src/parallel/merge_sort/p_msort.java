package parallel.merge_sort;

import serial.File_op;
import serial.merge_sort;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.Future;

public class p_msort{

    static class MyTask extends RecursiveTask<int[]>
    {
        private int source[];
        public MyTask(int source[])
        {
            this.source = source;
        }

        @Override
        protected int[] compute()
        {
            int sourceLen = source.length;// 如果条件成立，说明任务中要进行排序的集合还不够小
            if(sourceLen > 10000)   //大于200
            {
                int midIndex = sourceLen / 2;// 拆分成两个子任务
                MyTask task1 = new MyTask(Arrays.copyOf(source, midIndex));
                task1.fork();
                MyTask task2 = new MyTask(Arrays.copyOfRange(source, midIndex , sourceLen));
                task2.fork();// 将两个有序的数组，合并成一个有序的数组
                int result1[] = task1.join();
                int result2[] = task2.join();
                int mer[] = joinInts(result1 , result2);
                return mer;
            }
            // 否则说明集合中只有一个或者两个元素，可以进行这两个元素的比较排序了
            else {
                System.out.println("");
                // 如果条件成立，说明数组中只有一个元素，或者是数组中的元素都已经排列好位置了
                merge_sort.sort(source,0,sourceLen-1);
                return this.source;
            }
        }

        private int[] joinInts(int array1[] , int array2[]) {
            // 和上文中出现的代码一致
            int i = 0;//左序列指针
            int j =0;//右序列指针
            int t = 0;//临时数组指
            int len_1 = array1.length - 1;
            int len_2 = array2.length - 1;
            int []temp = new int[len_1 + len_2 + 2];

            while (i <= len_1 && j <= len_2){
                if(array1[i] <= array2[j]){
                    temp[t++] = array1[i++];
                }else {
                    temp[t++] = array2[j++];
                }
            }
            while(i <= len_1){//将左边剩余元素填充进temp中
                temp[t++] = array1[i++];
            }
            while(j <= len_2){//将右序列剩余元素填充进temp中
                temp[t++] = array2[j++];
            }

            return temp; //返回归并好的temp数组
        }
    }


    public static void main(String[] args) throws Exception {
        File_op f = new File_op();
        f.readTxtFile("random.txt");
        int[] numbers = f.return_numbers();
            // 正式开始
            long beginTime = System.currentTimeMillis();
            ForkJoinPool pool = new ForkJoinPool();
            MyTask task = new MyTask(numbers);

            Future<int[]> taskResult = pool.submit(task);
            try {
                taskResult.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace(System.out);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("耗时=" + (endTime - beginTime) + "ms");
        //f.writeTxtFile("merge_sort_2.txt", taskResult.get());
        System.out.println("exit");
    }
}