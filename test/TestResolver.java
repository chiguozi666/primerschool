import org.junit.Test;
import ty.ExpProvider;
import ty.ExpResolver;

public class TestResolver {
    @Test
    public void test(){
        for (int i = 0; i < 100; i++) {
            String suffix = ExpProvider.createExp(10).split(";")[1];
            String s = ExpResolver.creatSuffixExepression(suffix);
            System.out.println("ԭ����ʽ��: "+suffix);
            System.out.println("��׺���ʽ: "+s);
        }
    }
}
