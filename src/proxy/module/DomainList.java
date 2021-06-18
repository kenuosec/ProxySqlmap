package proxy.module;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DomainList {

    public StringProperty Domain;

    public DomainList(String Domain){
        this.Domain = new SimpleStringProperty(Domain);
    }

    public void setDomain(String domain) {
        this.Domain.set(domain);
    }

    public StringProperty domainProperty() {
        return Domain;
    }

    public String getDomain() {
        return Domain.get();
    }
}
