package serial;

import java.util.List;

public class quick_sort {

    public void sort(int [] numbers,int begin,int end) //快速排序
    {
        if(begin >= end) return;
        int partition = numbers[begin]; //划分元素
        int part_pos = begin; //从开始进行划分
        for(int i = begin + 1; i <= end ; i++) //划分
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
        sort(numbers,begin,part_pos - 1); //排序
        sort(numbers,part_pos + 1,end); //排序
    }
}
