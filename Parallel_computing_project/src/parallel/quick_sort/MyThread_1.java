package parallel.quick_sort;
import java.util.*;
import java.util.concurrent.Callable;

public class MyThread_1 implements Callable<QRES>
{

        private int left;
        private int right;
        private int middle;
        private int str[];

        public MyThread_1(int left, int right, int  str[])
        {
            this.left = left;
            this.right = right;
            this.str = str;
        }

        @Override
        public QRES call()
        {
            quick_sort();
            return new QRES(left,right,middle);
        }

        public void quick_sort()
        {
            int i,j,pos;
            if(left >= right)
            {
                return;
            }
            i = left;
            j = right;
            pos = str[i];
            while( i < j )
            {
                while( i < j && pos <= str[j])
                {
                    j--;
                }
                if( i < j )
                {
                    str[i] = str[j];
                }

                while( i < j && pos >= str[i])
                {
                    i++;
                }
                if(i < j)
                {
                    str[j] = str[i];
                }
                str[i]=pos;
            }
            middle = i;
        }
}

