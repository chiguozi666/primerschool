import org.junit.Test;
import ty.ExpProvider;
import ty.ExpResolver;

public class TestResolver {
    @Test
    public void test(){
        for (int i = 0; i < 100; i++) {
            String suffix = ExpProvider.createExp(10).split(";")[1];
            String s = ExpResolver.creatSuffixExepression(suffix);
            System.out.println("原来的式子: "+suffix);
            System.out.println("后缀表达式: "+s);
        }
    }
}
