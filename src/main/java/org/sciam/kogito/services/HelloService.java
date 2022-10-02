package org.sciam.kogito.services;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloService {

    public String sayHello(String name) {
        return "Hello " + name;
    }

}
