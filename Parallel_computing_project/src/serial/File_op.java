package serial;

import java.io.*;

public class File_op
{

private int[] numbers;
private File inputFile;
private String inputString; //读取的字符串
private  File outputFilfe ; //输出文件

    public File_op()
    {
        numbers = new int[30000];//固定初始化
        inputFile = null; //为空
        inputString = ""; //为空
        outputFilfe = null;
    }

    public void string_to_numbers() //实现字符串到数组的转化
    {
        System.out.println("开始进行字符串的转化");
        String [] temp;
        temp = inputString.split("\\s+");
        int count = 0;
        for(String ss : temp){
            Integer t  = Integer.parseInt(ss); //实现转换
            numbers[count] = t; //加入t
            count ++;
        }
        System.out.println("数组的大小为:"+ count);
    }

    public void readTxtFile(String filePath){
        try {
            String encoding = "GBK";
            inputFile = new File(filePath);
            if(inputFile.isFile() &&  inputFile.exists()){
                //判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(inputFile),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                inputString = bufferedReader.readLine(); //读取
                //str转字符串
                string_to_numbers();
                read.close();
                bufferedReader.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }

    public void writeTxtFile(String filePath,int [] numbers)
    {
        try {
            String encoding = "GBK";
            outputFilfe = new File(filePath);
            if(outputFilfe.exists() == false)
            {
                outputFilfe.createNewFile();
            }
            if(outputFilfe.isFile() &&  outputFilfe.exists()){
                //判断是否是存在的
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilfe),"GBK"));
                String t = "";
                for(int i = 0; i < numbers.length - 1;i++)
                {
                    t += numbers[i] ;
                    t += " ";
                }
                t += numbers[numbers.length-1]; //长度减一
                out.write(t);//写出t
                out.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }

    public int[] return_numbers()
    {
        return this.numbers; //返回
    }
}
