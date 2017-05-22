package controllers.supplier;

import controllers.core.secure.Secured;
import models.supplier.helpers.SupplierHelper;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.supplier.pages.map;
import views.supplier.dto.SupplierDTO;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by LuisSebastian on 5/21/17.
 * Controller that implements the services for the supplier
 */
public class SupplierController extends Controller {

    private final SupplierHelper supplierHelper;

    @Inject
    public SupplierController(SupplierHelper supplierHelper){
        this.supplierHelper = supplierHelper;
    }

    @Transactional
    @Security.Authenticated(Secured.class)
    public Result mainMapPage(){
        List<SupplierDTO> supplierDTOList = supplierHelper.buildSupplierDTO();
        return ok(map.render(supplierDTOList));
    }

}
