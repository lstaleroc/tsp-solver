package controllers.core.user;

import forms.UserForm;
import models.core.user.entities.User;
import models.core.user.helpers.UserHelper;
import play.data.Form;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.http.DefaultHttpErrorHandler;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.core.user.login;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

/**
 * Created by LuisSebastian on 5/21/17.
 * Controller that implements the user services.
 */
public class UserController extends Controller {

    public final static String EMAIL_SESSION_VARIABLE = "email_session_variable";

    private final FormFactory formFactory;
    private final UserHelper userHelper;
    private final DefaultHttpErrorHandler errorHandler;

    /**
     * Controller constructor that injects the components needed for this controller.
     *
     * @param formFactory
     * @param userHelper
     */
    @Inject
    public UserController(FormFactory formFactory, UserHelper userHelper, DefaultHttpErrorHandler errorHandler) {

        this.formFactory = formFactory;
        this.userHelper = userHelper;
        this.errorHandler = errorHandler;
    }

    /**
     * Render user login form
     *
     * @return ok(200) loginForm
     */
    public Result loginForm() {

        Form<UserForm> formAuthenticationUser = formFactory.form(UserForm.class);
        return ok(login.render(formAuthenticationUser));
    }

    /**
     * Validates the login credentials of an user
     *
     * @return ok(200) login Ok - badRequest(400) Login fail
     */
    @Transactional
    public Result login() {

        Form<UserForm> formAuthenticationUser = formFactory.form(UserForm.class);
        UserForm authenticationUserForm = formAuthenticationUser.bindFromRequest().get();
        User user = userHelper.authenticateUser(authenticationUserForm.getEmail(), authenticationUserForm.getPassword());
        if (user == null) {
            formAuthenticationUser.reject("Las credenciales ingresadas no son correctas, intentelo de nuevo.");
            return badRequest(login.render(formAuthenticationUser));
        }
        else {
            session("username", user.getName().concat(" ").concat(user.getLastName()));
            session(EMAIL_SESSION_VARIABLE, user.getEmail());
            return redirect(controllers.supplier.routes.SupplierController.mainMapPage());
        }
    }

    /**
     * Close the session of the user logged in the application
     *
     * @return
     */
    public Result logout() {

        session().clear();
        return redirect(routes.UserController.loginForm());
    }

    /**
     * Show error when user's login fail
     * @param context
     * @param content
     * @return
     */
    public CompletionStage<Result> onAuthFailure(final Http.Context context,
                                                 final Optional<String> content) {
        return this.errorHandler.onClientError(context.request(), Http.Status.FORBIDDEN, "You don't have required permissions.");
    }

}
