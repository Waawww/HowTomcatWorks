package ex2.SimpleServlet;

import java.io.IOException;

/**
 * @Author hjz
 * @Date 2022-04-28 21:22
 */
public class StaticResourceProcessor {
    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
