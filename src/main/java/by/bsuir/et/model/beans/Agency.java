package by.bsuir.et.model.beans;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "agency")
@XmlAccessorType(XmlAccessType.FIELD)
public class Agency {
    @XmlElementWrapper(name = "customers")
    @XmlElement(name = "customer")
    private List<Customer> customers;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
