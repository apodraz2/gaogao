package com.gaogao.scheduler.gaogaopractice;

import com.gaogao.scheduler.gaogaopractice.Dog;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-04-28T09:17:08")
@StaticMetamodel(Owner.class)
public class Owner_ { 

    public static volatile SingularAttribute<Owner, String> password;
    public static volatile ListAttribute<Owner, Dog> dogList;
    public static volatile SingularAttribute<Owner, String> email;

}