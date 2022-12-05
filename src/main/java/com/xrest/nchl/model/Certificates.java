package com.xrest.nchl.model;

import jakarta.persistence.Entity;
import lombok.Data;


@Entity
@Data
public class Certificates extends BaseModel<Long> {
    private String citizenshipNo;
    private String gender;
    private String nationality;
}
