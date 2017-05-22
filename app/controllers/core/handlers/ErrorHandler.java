package controllers.core.handlers;


import play.Configuration;
import play.Environment;
import play.Logger;
import play.api.OptionalSourceMapper;
import play.api.routing.Router;
import play.http.DefaultHttpErrorHandler;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * Created by LuisSebastian on 5/21/17.
 */
public class ErrorHandler extends DefaultHttpErrorHandler {

    private ErrorController errorController;

    @Inject
    public ErrorHandler(Configuration configuration,
                        Environment environment,
                        OptionalSourceMapper sourceMapper,
                        Provider<Router> routes,
                        ErrorController errorController) {
        super(configuration, environment, sourceMapper, routes);

        this.errorController = errorController;
    }


    @Override
    protected CompletionStage<Result> onNotFound(Http.RequestHeader request, String message) {
        Logger.debug("Error: onNotFound, message: " + message);
        return CompletableFuture.completedFuture(this.errorController.notFoundPage());
    }

}
