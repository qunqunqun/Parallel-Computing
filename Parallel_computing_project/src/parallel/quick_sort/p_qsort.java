package parallel.quick_sort;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;

import serial.File_op;

public class p_qsort {

    ExecutorService pool = Executors.newFixedThreadPool(10);
    private int[] str;

    public void qqsort(int [] numbers,int begin,int end) //快速排序
    {
        if (begin >= end) return;
        int partition = numbers[begin]; //划分元素
        int part_pos = begin; //从开始进行划分
        for (int i = begin + 1; i <= end; i++) //划分
        {
            if (numbers[i] <= partition) //比划分元素小
            {
                part_pos++; //加一
                int t = numbers[i];
                numbers[i] = numbers[part_pos];
                numbers[part_pos] = t; //交换
            }
        }
        //交换最初的元素和part_pos
        int t = numbers[part_pos];
        numbers[part_pos] = partition; //交换
        numbers[begin] = t;//
        qqsort(numbers, begin, part_pos - 1); //排序
        qqsort(numbers, part_pos + 1, end); //排序
    }

    public p_qsort(int str[])
    {
        this.str = str;
        qsort(0,str.length-1);
        pool.shutdown();
    }
    //分治
    public void qsort(int begin, int end)
    {
        if(begin <= end + 1000)
        {
            qqsort(this.str,begin,end);
            return;
        }
        //Future用来接收返回值
        MyThread_1 t = new MyThread_1(begin, end, str);
        Future<QRES> future = pool.submit(t);
        try {
               if (future.get().cmpLeftMiddle()) {
                    qsort(future.get().getLeft(), future.get().getMiddle() - 1);
               }
               if (future.get().cmpMiddleRight()) {
                    qsort(future.get().getMiddle() + 1, future.get().getRight());
               }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        File_op f = new File_op();
        f.readTxtFile("random.txt");
         int[] numbers = f.return_numbers();
         long startTime = System.currentTimeMillis();
         p_qsort a = new p_qsort(numbers);
         long endTime = System.currentTimeMillis();
        System.out.println("快排程序运行时间： " + (endTime - startTime) + "ms");
        f.writeTxtFile("quick_sort_2.txt", numbers);
    }
}
