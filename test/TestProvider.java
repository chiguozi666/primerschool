import org.junit.Test;
import ty.ExpProvider;

public class TestProvider {
    /**
     * 测试中缀表达式的生成器
     */
    @Test
    public void testExpCreater(){
        for(int i =0;i<10000;i++){
            System.out.println(ExpProvider.createExp(10));
        }
    }

    /**
     * 生成测试数字的
     * 生成的数必然是整数或者带分数
     */
    @Test
    public void testMathCreater(){
        for(int i =0;i<1000;i++){
            System.out.println( ExpProvider.createMath(100));
        }
    }

    /**
     * 输入一个带分数
     * 生成的数字必然是假分数
     */
    @Test
    public void testTrueToFalse(){
        for(int i =0;i<1000;i++){
            System.out.println(ExpProvider.trueToFalse(""+i+"'"+i%10+"/"+i%7));
        }
    }

    /**
     * 运算符生成
     */
    @Test
    public void testOp(){
        for(int i =0 ;i<10000;i++){
            System.out.println(ExpProvider.createOp());
        }
    }
}
