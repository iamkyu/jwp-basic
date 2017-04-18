package next.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kj Nam
 * @since 2017-04-17
 */
class RequestMapping {
    private Map<String, Controller> controllers;

    public RequestMapping() {
        controllers = new HashMap() {{
            put("/", new HomeController());
            put("/users/create", new CreateUserController());
            put("/users/form", new ForwardController("/user/form.jsp"));
            put("/users", new ListUserController());
            put("/users/login", new LoginController());
            put("/users/loginForm", new ForwardController("/user/login.jsp"));
            put("/users/logout", new LogoutController());
            put("/users/profile", new ProfileController());
            put("/users/update", new UpdateUserController());
            put("/users/updateForm", new UpdateUserFormController());
        }};
    }

    Controller get(String key) {
        return controllers.get(key);
    }
}
