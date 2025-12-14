package model;

public class UserService {

    // 用户名密码校验（Controller 已处理验证码）
    public UserInfo login(String username, String password) {

        if ("admin".equals(username) && "123".equals(password)) {
            return new UserInfo(username, "admin");
        }

        if ("user".equals(username) && "123".equals(password)) {
            return new UserInfo(username, "user");
        }

        return null;
    }
}
