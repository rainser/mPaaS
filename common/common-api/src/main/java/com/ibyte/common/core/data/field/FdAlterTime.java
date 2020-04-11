package com.ibyte.common.core.data.field;

import com.ibyte.common.core.data.IData;
import com.ibyte.common.core.data.handler.FieldHandler;
import com.ibyte.common.core.data.handler.FieldHandlerExtension;
import com.ibyte.framework.meta.annotation.MetaProperty;

import javax.persistence.Column;
import java.util.Date;

/**
 * 修改时间字段
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
public interface FdAlterTime extends IData, IField {
    /**
     * 读-修改时间
     *
     * @return
     */
    @Column
    @MetaProperty(readOnly = true, messageKey = "property.fdAlterTime")
    default Date getFdAlterTime() {
        return (Date) getExtendProps().get("fdAlterTime");
    }

    /**
     * 写-修改时间
     *
     * @param fdAlterTime
     */
    default void setFdAlterTime(Date fdAlterTime) {
        getExtendProps().put("fdAlterTime", fdAlterTime);
    }

    /**
     * 修改时间更新
     *
     * @author iByte
     */
    @FieldHandlerExtension
    public class FdAlterTimeHandler implements FieldHandler {
        @Override
        public boolean support(IData entity) {
            return entity instanceof FdAlterTime;
        }

        @Override
        public void doInit(IData entity) {
            ((FdAlterTime) entity).setFdAlterTime(new Date());
        }

        @Override
        public void beforeSaveOrUpdate(IData entity, boolean isAdd) {
            ((FdAlterTime) entity).setFdAlterTime(new Date());
        }
    }
}
