package com.gaogao.scheduler.gaogaopractice;

import com.gaogao.scheduler.gaogaopractice.Event;
import com.gaogao.scheduler.gaogaopractice.Owner;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-11T08:55:11")
@StaticMetamodel(Dog.class)
public class Dog_ { 

    public static volatile SingularAttribute<Dog, Date> birthday;
    public static volatile ListAttribute<Dog, Event> eventList;
    public static volatile SingularAttribute<Dog, String> name;
    public static volatile ListAttribute<Dog, Owner> ownerList;
    public static volatile SingularAttribute<Dog, Long> id;

}