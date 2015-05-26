package com.gaogao.scheduler.persistence;

import com.gaogao.scheduler.persistence.Dog;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-26T11:13:22")
@StaticMetamodel(Owner.class)
public class Owner_ { 

    public static volatile SingularAttribute<Owner, String> password;
    public static volatile ListAttribute<Owner, Dog> dogList;
    public static volatile SingularAttribute<Owner, String> email;

}