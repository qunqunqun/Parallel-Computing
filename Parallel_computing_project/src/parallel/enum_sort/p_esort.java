package parallel.enum_sort;
import serial.File_op;

import java.util.Arrays;

public class p_esort {
    static int num = 0;

    static class MyTask extends Thread {
        private int []source; //任务要进行分割的一些数据
        private int []data;
        private int []sortData;
        private int pos; //加入位置
        MyTask(int []sdata,int []sourcedata,int []sortData,int pos)
        {
            num++;
            this.source = sdata; //进行划分
            this.pos = pos;
            this.data = sourcedata;
            this.sortData = sortData;
        }
        //增添计算
        @Override
        public void run() {
            int sourceLen = source.length;// 如果条件成立，说明任务中要进行排序的集合还不够小
            for (int j = 0; j < this.source.length; j++) {
                int index = 0;
                for (int i = 0; i < 30000; i++) {
                    if ((data[i] < this.source[j]) || (data[i] == this.source[j] && (i < j + this.pos))) //小
                    {
                        index++;
                    }
                }
                sortData[index] = this.source[j];
            }
            num--;
        }

    }


    public static void main(String[] args) throws Exception {
        File_op f = new File_op();
        f.readTxtFile("random.txt");
        int[] numbers = f.return_numbers();
        int[] final_res = new int[30000];
        for (int i = 0; i < 30000; i++) {
            final_res[i] = -1;
        }
        p_esort t = new p_esort();

        MyTask task[] = new MyTask[174];
        for(int i = 0; i < 173;i ++)
        {
            task[i] = new MyTask(Arrays.copyOfRange(numbers,i*173,(i+1)*173),numbers,final_res,i*173);
        }
        task[173] = new MyTask(Arrays.copyOfRange(numbers,29929,30000),numbers,final_res,29929);
        long beginTime = System.currentTimeMillis();
        for(int i = 0; i < 174; i++)
        {
            task[i].start();
        }
        while(true) {
            if (num == 0) {
                long endTime = System.currentTimeMillis();
                System.out.println("耗时=" + (endTime - beginTime) + "ms");
                f.writeTxtFile("enum_sort_2_b.txt", final_res);
                System.out.println("exit");
                break;
            }
            else
            {
                Thread.sleep(1);
            }
        }
    }
}
