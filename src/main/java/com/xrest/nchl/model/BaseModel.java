package com.xrest.nchl.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;
import java.io.Serializable;

@Getter
@Setter
public class BaseModel<L extends Serializable> extends AbstractPersistable<L> {
    @Override
    protected void setId(L id) {
        super.setId(id);
    }
}
