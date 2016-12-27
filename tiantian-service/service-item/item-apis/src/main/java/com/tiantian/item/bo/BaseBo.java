package com.tiantian.item.bo;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseBo implements  Serializable {

    private Date created;
    private Date updated;
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public Date getUpdated() {
        return updated;
    }
    public void setUpdated(Date updated) {
        this.updated = updated;
    }


}
