package academy.prog;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class UserServlet extends HttpServlet {
    private UserList userList = UserList.getInstance();
    private List<User> users = userList.getListU();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        String body = reader.lines().collect(Collectors.joining());
        String bufStr = new String(body.getBytes(), StandardCharsets.UTF_8);
        User user = User.fromJSON(bufStr);
        if (user != null) {
            for (int i = 0; i < users.size(); i++) {
                userList.add(user);
            }
        }
        String json = userList.toJSON();
        if (json != null) {
            OutputStream os = resp.getOutputStream();
            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
            try {
                os.write(buf);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

