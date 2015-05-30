package com.gaogao.scheduler.persistence;

import com.gaogao.scheduler.persistence.Event;
import com.gaogao.scheduler.persistence.Owner;
import com.gaogao.scheduler.persistence.ServiceProvider;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-29T20:51:29")
@StaticMetamodel(Dog.class)
public class Dog_ { 

    public static volatile SingularAttribute<Dog, Date> birthday;
    public static volatile ListAttribute<Dog, Event> eventList;
    public static volatile SingularAttribute<Dog, String> name;
    public static volatile ListAttribute<Dog, Owner> ownerList;
    public static volatile SingularAttribute<Dog, Long> id;
    public static volatile ListAttribute<Dog, ServiceProvider> providerList;

}