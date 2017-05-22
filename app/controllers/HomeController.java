package controllers;

import controllers.core.secure.Secured;
import controllers.supplier.*;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.supplier.pages.map;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {


    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    @Security.Authenticated(Secured.class)
    public Result index() {
        return redirect(controllers.supplier.routes.SupplierController.mainMapPage());
    }

}
