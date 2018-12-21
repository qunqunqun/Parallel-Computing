package serial;

public class merge_sort {

    public static void sort(int []numbers,int begin,int end){
        int []temp = new int[numbers.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        sort(numbers,begin,end,temp);
    }
    private static void sort(int[] numbers,int left,int right,int []temp){
        if(left<right)
        {
            int mid = (left+right)/2;
            sort(numbers,left,mid,temp);//左边归并排序，使得左子序列有序
            sort(numbers,mid+1,right,temp);//右边归并排序，使得右子序列有序
            merge(numbers,left,mid,right,temp);//将两个有序子数组合并操作
        }
    }
    private static void merge(int[] numbers,int left,int mid,int right,int[] temp){
        int i = left;//左序列指针
        int j = mid + 1;//右序列指针
        int t = 0;//临时数组指针
        while (i <= mid && j <= right){
            if(numbers[i] <= numbers[j]){
                temp[t++] = numbers[i++];
            }else {
                temp[t++] = numbers[j++];
            }
        }
        while(i <= mid){//将左边剩余元素填充进temp中
            temp[t++] = numbers[i++];
        }
        while(j <= right){//将右序列剩余元素填充进temp中
            temp[t++] = numbers[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while(left <= right){
            numbers[left++] = temp[t++];
        }
    }
}
