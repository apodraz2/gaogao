package com.gaogao.scheduler.gaogaopractice;

import com.gaogao.scheduler.gaogaopractice.Dog;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-15T08:13:24")
@StaticMetamodel(Event.class)
public class Event_ { 

    public static volatile SingularAttribute<Event, Date> date;
    public static volatile SingularAttribute<Event, String> description;
    public static volatile SingularAttribute<Event, Long> id;
    public static volatile SingularAttribute<Event, Boolean> completed;
    public static volatile SingularAttribute<Event, Dog> dog;

}