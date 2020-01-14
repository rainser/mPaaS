package com.ibyte.sys.org.event;
import com.ibyte.common.core.entity.IEntity;
import com.ibyte.common.core.event.AbstractEntityEvent;

/**
 * 组织架构置为无效事件
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public class SysOrgElementInvalidatedEvent extends AbstractEntityEvent {
    private static final long serialVersionUID = -5447853593691193700L;

    public SysOrgElementInvalidatedEvent(IEntity source) {
        super(source);
    }
}
