package com.greptilian.addressbookmvc.ozark;

import com.greptilian.addressbookmvc.ozark.PeopleController;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("resources")
public class MyApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(PeopleController.class);
        return classes;
    }

}
