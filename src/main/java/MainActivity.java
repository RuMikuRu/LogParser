import source.Parser;
import java.io.IOException;


//TODO Сделать ввод через консоль
public class MainActivity {
    public static void main(String[] args) throws IOException {
        Parser a = new Parser("access.log", 55, 10);
        a.calc();
    }
}
