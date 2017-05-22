package controllers.core.secure;

import controllers.core.user.UserController;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by LuisSebastian on 5/21/17.
 */
public class Secured extends Security.Authenticator {

    @Override
    public String getUsername(Http.Context ctx) {
        return ctx.session().get(UserController.EMAIL_SESSION_VARIABLE);
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        return redirect(controllers.core.user.routes.UserController.loginForm());
    }
}
