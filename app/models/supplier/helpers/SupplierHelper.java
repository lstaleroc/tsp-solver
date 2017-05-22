package models.supplier.helpers;

import models.supplier.entities.Supplier;
import views.supplier.dto.SupplierDTO;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LuisSebastian on 5/21/17.
 */
@Singleton
public class SupplierHelper {


    /**
     * Builds a DTO with all the suppliers.
     *
     * @return List of suppliers
     */
    public List<SupplierDTO> buildSupplierDTO() {
        List<SupplierDTO> supplierDTOList = new ArrayList<>();
        List<Supplier> supplierList = Supplier.findAll(Supplier.class);
        for(Supplier supplier : supplierList){
            SupplierDTO supplierDTO = new SupplierDTO();
            supplierDTO.setName(supplier.name);
            supplierDTO.setValue(supplier.latitude.concat(",").concat(supplier.longitude));
            supplierDTOList.add(supplierDTO);
        }
        return supplierDTOList;
    }
}
