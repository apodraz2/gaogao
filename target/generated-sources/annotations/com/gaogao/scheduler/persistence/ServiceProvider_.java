package com.gaogao.scheduler.persistence;

import com.gaogao.scheduler.persistence.Dog;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-27T19:11:59")
@StaticMetamodel(ServiceProvider.class)
public abstract class ServiceProvider_ { 

    public static volatile SingularAttribute<ServiceProvider, String> phoneNumber;
    public static volatile SingularAttribute<ServiceProvider, String> name;
    public static volatile SingularAttribute<ServiceProvider, Dog> dog;
    public static volatile SingularAttribute<ServiceProvider, String> email;

}