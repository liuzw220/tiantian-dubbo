package com.tiantian.order.vo;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseVo implements  Serializable {
	private static final long serialVersionUID = -2151059750924228803L;
	
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
