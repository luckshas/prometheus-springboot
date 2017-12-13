package info.luxman.prometheusdemo;

import info.luxman.prometheusdemo.model.WOrder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceA {

    private Map<String,WOrder> orderMap = new HashMap<String,WOrder>();

    public WOrder createWorder(WOrder worder)
    {
        orderMap.put(worder.getWorkOrderId(),worder);
        return worder;
    }
    public WOrder getWOrder(String orderid)
    {
        return orderMap.get(orderid);
    }
}
