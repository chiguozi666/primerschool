package ty;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int flag = 1;
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        ExpProvider xx = new ExpProvider();
        String inStr = "aaa";
        File file = null;
        int a = 0;
        int b = 0;
        int c = 0;
        while (flag == 1) {
            int range = 0;
            System.out.println("����������,1:������Ŀ,2:У����Ƿ���ȷ ,3:�˳�");
            if (sc.hasNextInt()) {
                b = sc.nextInt();
            }
            switch (b) {
                case 1:
                    System.out.println("--------��������ֵ���޶�������Ŀ��ֵ��Χ,��ʽ:-r 10---------");
                    if (sc1.hasNextLine()) {
                        inStr = sc1.nextLine();
                    }
                    String[] path = inStr.split(" ");
                    if (path.length > 1) {
                        range = Integer.parseInt(path[1]);
                    }
                    System.out.println("---------��������ֵ���޶�������Ŀ����,��ʽ:-n 10----------");
                    if (sc1.hasNextLine()) {
                        inStr = sc1.nextLine();
                    }
                    path = inStr.split(" ");
                    if (path.length > 1) {
                        c = Integer.parseInt(path[1]);
                    }
                    try {
                        IOUtil.create(c, range);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("-----------������У���ļ�,��ʽ:exercisefile.txt answerfile.txt---------");
                    if (sc1.hasNextLine()) {
                        inStr = sc1.nextLine();
                    }
                    try {
                        IOUtil.check(inStr);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    flag = 0;
                    break;
                default:
                    System.out.println("��ʽ����,����������!");
            }
        }
    }
}
