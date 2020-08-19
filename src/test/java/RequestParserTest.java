import com.pollra.server.client.http.HttpProtocol;
import com.pollra.server.client.http.RequestParser;
import org.junit.Test;

import java.util.Arrays;

public class RequestParserTest {
    final String text = "POST / HTTP/1.1\n" +
            "Host: localhost:12345\n" +
            "Connection: keep-alive\n" +
            "Content-Length: 17\n" +
            "Postman-Token: ee172b14-e51a-325e-4fec-ee09382421ca\n" +
            "Cache-Control: no-cache\n" +
            "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36\n" +
            "Content-Type: application/x-www-form-urlencoded\n" +
            "Accept: */*\n" +
            "Origin: chrome-extension://fhbjgbiflinjbdggehcddcbncdddomop\n" +
            "Sec-Fetch-Site: none\n" +
            "Sec-Fetch-Mode: cors\n" +
            "Sec-Fetch-Dest: empty\n" +
            "Accept-Encoding: gzip, deflate, br\n" +
            "Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7\n";

}
