package ty;

import java.util.Random;


/**
 * ������׺���ʽ
 */
public class ExpProvider {
    /**
     * �����תΪ�ٷ���
     * @param a һ���ٷ���
     * @return
     */
    public static String trueToFalse(String a) {
        String b[] = a.split("'");
        String c[] = b[1].split("/");
        int d = Integer.parseInt(b[0]);//����������ߵ�����
        int up = Integer.parseInt(c[0]);//����
        int down = Integer.parseInt(c[1]);//��ĸ
        int newUp = d * down + up;
        String result = newUp + "/" + down;
        return result;
    }

    /**
     * ����һ��range��Χ������
     * @param range �������ķ�Χ
     * @return
     */
    public static String createMath(int range) {//�������������
        Random rand = new Random();
        int a = rand.nextInt(range - 1);
        int b = rand.nextInt(4);//����
        int c = rand.nextInt(6) + b;//��ĸ  ��֤��ĸ�ȷ��Ӵ�
        String d = Integer.toString(a);
        if (b >= 3) {
            if (d.equals("0")) {
                d = b + "/" + c;
            } else
                d = a + "'" + b + "/" + c;
        }
        if (d.equals("0")) d = Integer.toString(1);
        return d;
    }

    public static String createOp() {//��������������
        Random rand = new Random();
        int a = rand.nextInt(4);
        String str = null;
        if (a == 0)
            str = "+";
        else if (a == 1)
            str = "-";
        else if (a == 2)
            str = "*";
        else
            str = "/";
        return str;
    }

    public static String createExp(int range) {//���ɱ��ʽ
        String str = "";
        String str1 = "";
        do {
            str = "";//��׼��ʽ��
            str1 = "";//�ٷ�����ʽ��
            int t = 0;//��ʽ�е����ָ���
            Random rand = new Random();
            t = rand.nextInt(3) + 2;
            String[] number = new String[t];//�������
            String[] symbol = new String[t - 1];//����������,��t�����־�Ӧ����t-1������
            String[] total = new String[4 * t - 3];//���ʽ��
            String[] total1 = new String[4 * t - 3];
            for (int i = 0; i < t; i++) {
                number[i] = createMath(range);
            }
            for (int i = 0; i < t - 1; i++) {
                symbol[i] = createOp();
            }
            //����+�ո�+������+�ո�+���ָպ���ģ4����
            for (int i = 0; i < 4 * t - 3; i++) {
                if (i % 4 == 0) {
                    total[i] = number[i / 4];
                    total1[i] = number[i / 4];
                } else if (i % 4 == 2) {
                    int k = (i + 2) / 4 - 1;
                    total[i] = symbol[k];
                    total1[i] = symbol[k];
                } else {
                    total[i] = " ";
                    total1[i] = " ";
                }
            }
            //total1��������������ת�ɼٷ�������������
            for (int i = 0; i < 4 * t - 3; i++) {
                if (total1[i].contains("'")) {
                    total1[i] = trueToFalse(total1[i]);
                }
            }
            //��t=4��ʱ��ͼ����ž�����
            if (t == 4) {
                if ((symbol[1] == "*" || symbol[1] == "/") && (symbol[0] == "+" || symbol[0] == "-")) {
                    total[0] = "(" + total[0];
                    total[4] = total[4] + ")";
                    total1[0] = "(" + total1[0];
                    total1[4] = total1[4] + ")";
                }
                if ((symbol[1] == "*" || symbol[1] == "-") && (symbol[2] == "+" || symbol[2] == "-")) {
                    total[8] = "(" + total[8];
                    total[12] = total[12] + ")";
                    total1[8] = "(" + total1[8];
                    total1[12] = total1[12] + ")";
                }
            }
            //ƴ��total�ַ���
            for (int i = 0; i < 4 * t - 3; i++) {
                str = str + total[i];
                str1 = str1 + total1[i];
            }
            //�жϴ��Ƿ�Ϊ�����������Ǹ���������
        } while (ExpCounter.count(str1).contains("-"));
        // System.out.println(str);
        return str + " =" + ";" + str1;
    }

    public static void main(String[] args) {
        ExpProvider a = new ExpProvider();
        System.out.println(a.createExp(10));
    }
}
