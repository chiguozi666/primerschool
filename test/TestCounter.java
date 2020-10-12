import org.junit.Test;
import ty.ExpCounter;
import ty.ExpProvider;

public class TestCounter {
    @Test
    public void count(){
        for(int i = 0;i<100;i++){
            String s = ExpProvider.createExp(10).split(";")[1];
            String result = ExpCounter.count(s);
            System.out.println("原来的式子是    "+s);
            System.out.println("计算后的结果是  "+result);
        }
    }
}
