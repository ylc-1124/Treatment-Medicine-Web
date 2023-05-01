package edu.sust.common.vo;

import edu.sust.order.entity.PatientOrder;
import edu.sust.order.entity.ShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderData {

    private PatientOrder order;

    private List<ShoppingCart> shopCartRecordList;

}
