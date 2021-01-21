package com.example.monolitna.soap;

import com.example.monolitna.soap.wsdl.FuelType;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class CarClient extends WebServiceGatewaySupport {
    public Long createFuelType(FuelType value) {   //radi
        JAXBElement<FuelType> jaxbElement =
                new JAXBElement(new QName("http://www.car.com/car","createFuelType"),
                        FuelType.class, value);
        System.out.println("pre responsa");
        getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
        System.out.println("posle responsa");
        return 1L;
    }
}
