package ty;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;

public class IOUtil {
    /**
     * 创建表达式，并且写入文件
     * @param d
     * @param range
     * @throws Exception
     */
    public static final String EXERCISES_URL = "D:/cal/Exercises.txt";
    public static final String ANSWERS_URL = "D:/cal/Answers.txt";
    public static void create(int d,int range) throws Exception{
        String s="";
        FileWriter fw1 = new FileWriter(ANSWERS_URL);
        BufferedWriter bw1 = new BufferedWriter(fw1);
        FileWriter fw = new FileWriter(EXERCISES_URL);
        BufferedWriter bw = new BufferedWriter(fw);
        LinkedList<String> result = new LinkedList<>();
        /**
         * 生成d个式子
         */
        for(int i = 0,tCount = 0;i<d;){
            boolean isSame = false;
            String problem = ExpProvider.createExp(range);
            if(result.size()==0)result.add(problem);//为空就直接添加
            else//每生成一个都和之前集合的作对比
            for(int j = 0;j<result.size();j++){
                if(ExpChecker.isEqual(result.get(i).split(";")[1],problem.split(";")[1])){
                    isSame = true;
                }
            }
            if (isSame == false){
                result.add(problem);
                String[] path = problem.split(";");
                bw.write(i+". "+ path[0]);
                bw.newLine();
                bw.flush();
                bw1.write(i+". " + ExpCounter.count(path[1]));
                bw1.newLine();
                bw1.flush();
                i++;
            }
        }
        bw.close();
        bw1.close();
    }

    /**
     * 检查并且写入到文件夹中
     * @param str
     * @throws Exception
     */
    public static void check(String str) throws Exception {
        BufferedReader br1=null;
        String line= "";
        String line2= "(";
        String line3= "(";
        String line4=null;
        String line5=null;
        int a=0;
        int b=0;
        String[] path = str.split(" ");
        String[] path2 = null;
        FileWriter fw = new FileWriter("D:/cal/Grade.txt");
        BufferedWriter bw = new BufferedWriter(fw);

        FileReader fr = new FileReader(path[0]);
        BufferedReader br = new BufferedReader(fr);

        if(path.length>1) {
            FileReader fr1 = new FileReader(path[1]);
            br1 = new BufferedReader(fr1);
        }
        for(int i=0;(line = br.readLine())!=null;i++) {
            path = line.split("=");
            if(path.length>1) {
                line4 = path[1];
            }
            path2 = br1.readLine().split("\\.");
            if(path2.length>1) {
                line5 = path2[1];
            }
            if(line4.equals(line5)){
                line2 = line2 + i+",";
                a++;
            }else {
                line3 = line3 + i+",";
                b++;
            }
        }
        line2=line2.substring(0,line2.length()-1);
        line3=line3.substring(0,line3.length()-1);
        bw.write("Correct: "+ a + line2 +")");
        bw.newLine();
        bw.write("Wrong: "+ b + line3 +")");
        bw.close();
    }
}
