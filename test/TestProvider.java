import org.junit.Test;
import ty.ExpProvider;

public class TestProvider {
    /**
     * ������׺���ʽ��������
     */
    @Test
    public void testExpCreater(){
        for(int i =0;i<10000;i++){
            System.out.println(ExpProvider.createExp(10));
        }
    }

    /**
     * ���ɲ������ֵ�
     * ���ɵ�����Ȼ���������ߴ�����
     */
    @Test
    public void testMathCreater(){
        for(int i =0;i<1000;i++){
            System.out.println( ExpProvider.createMath(100));
        }
    }

    /**
     * ����һ��������
     * ���ɵ����ֱ�Ȼ�Ǽٷ���
     */
    @Test
    public void testTrueToFalse(){
        for(int i =0;i<1000;i++){
            System.out.println(ExpProvider.trueToFalse(""+i+"'"+i%10+"/"+i%7));
        }
    }

    /**
     * ���������
     */
    @Test
    public void testOp(){
        for(int i =0 ;i<10000;i++){
            System.out.println(ExpProvider.createOp());
        }
    }
}
