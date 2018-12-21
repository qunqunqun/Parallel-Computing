package serial;

;

public class Main {


    public static void main(String[] args) {
        //主函数
        long startTime;
        long endTime;
        File_op f = new File_op();
        f.readTxtFile("random.txt");
        //获取成功
        //快速排序

        /*System.out.println("快速排序");
        int []numbers_1 = f.return_numbers();
        quick_sort quick = new quick_sort();
        startTime = System.currentTimeMillis();
        quick.sort(numbers_1,0,numbers_1.length-1); //进行快速排序
        endTime = System.currentTimeMillis();
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
        f.writeTxtFile("quick_sort_1.txt",numbers_1);
        */
        //归并排序

        System.out.println("归并排序");
        //int []numbers_2 = f.return_numbers();
        for (int i = 0; i < 1; i++) {
            int[] numbers_2 = f.return_numbers();
            merge_sort merge = new merge_sort();
            startTime = System.currentTimeMillis();
            merge.sort(numbers_2, 0, numbers_2.length - 1); //进行快速排序
            endTime = System.currentTimeMillis();
            System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
            f.writeTxtFile("merge_sort_1.txt",numbers_2);
        }

        System.out.println("枚举排序");
            int[] numbers_3 = f.return_numbers();
            enum_sort insertion = new enum_sort();
            startTime = System.currentTimeMillis();
            insertion.sort(numbers_3); //进行快速排序
            endTime = System.currentTimeMillis();
            System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
            f.writeTxtFile("enum_sort_1.txt",numbers_3);
    }
}
