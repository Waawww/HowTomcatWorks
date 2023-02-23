package ex2.SimpleServlet;

import java.io.*;
import java.util.Locale;
import javax.servlet.ServletResponse;
import javax.servlet.ServletOutputStream;

/**
 * @Author hjz
 * @Date 2022-04-28 21:15
 */
public class Response implements ServletResponse {
    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream output;
    PrintWriter writer;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    /* This method is used to serve static pages */
    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        try {
            /* request.getUri has been replaced by request.getRequestURI */
            File file = new File(Constants.WEB_ROOT, request.getUri());
            fis = new FileInputStream(file);
/*
HTTP Response = Status-Line
*(( general-header | response-header | entity-header ) CRLF)
CRLF
[ message-body ]
Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
*/
            //TODO 这里，为啥浏览器，会收不到返回的数据？
//            int ch = fis.read(bytes, 0, BUFFER_SIZE);
//            while (ch != -1) {
//                output.write(bytes, 0, ch);
//                ch = fis.read(bytes, 0, BUFFER_SIZE);
//            }

            String s2 = "HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 2000\r\n"+
                    "\r\n";

            byte[] bytes1 = new byte[fis.available()];
            fis.read(bytes1);
            System.out.println(s2+new String(bytes1));
            output.write((s2+new String(bytes1)).getBytes());


        } catch (Exception e) {
            String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 23\r\n" +
                    "\r\n" +
                    "<h1>File Not Found</h1>";
            output.write(errorMessage.getBytes());
        } finally {
            if (fis != null)
                fis.close();
        }
    }

    /**
     * implementation of ServletResponse
     */

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        // autoflush自动刷新 is true, println() will flush,
        // but print() will not.
        //传递 true 作为第二个参数将会使任何 println 方法的调用都会刷新输出(output)。
        //不过，print 方法不会刷新输出(老的不会？现在的会？)
        writer = new PrintWriter(output, true);
        return writer;
    }

    @Override
    public void setCharacterEncoding(String s) {

    }

    @Override
    public void setContentLength(int i) {

    }

    @Override
    public void setContentType(String s) {

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
