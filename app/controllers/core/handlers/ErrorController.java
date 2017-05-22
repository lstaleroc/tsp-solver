package controllers.core.handlers;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by LuisSebastian on 5/21/17.
 */
public class ErrorController extends Controller{


    public Result notFoundPage() {
        return ok();
    }
}
