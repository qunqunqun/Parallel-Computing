package serial;

public class enum_sort {

    void sort(int [] numbers)
    {
        int [] count = new int[30000];
        for(int i = 0; i < count.length ;i ++)
        {
          count[i] = 0; //为0
        }
        for(int i = 0; i < numbers.length ; i++)
        {
            int t = numbers[i]; //
            for( int j = 0;  j < numbers.length ; j++)
            {
                if(numbers[j] < t)
                {
                    count[i]++;
                }
            }
        }
        //进行重新组合的排列
        int []end = new int[30000];
        for(int i = 0; i < end.length; i++)
        {
            end[i] = Integer.MIN_VALUE; //最小值
        }
        for(int i = 0; i < count.length; i++)
        {
            int t = numbers[i];
            if(end[count[i]] == Integer.MIN_VALUE)
            {
                end[count[i]] = t;
            }
            else{
                while(end[count[i]] != Integer.MIN_VALUE) //不是最小的
                {
                    count[i]++;
                }
            }
            end[count[i]] = t; //
        }
        for(int i = 0; i < end.length ; i++)
        {
            numbers[i] = end[i];
        }
    }

}
