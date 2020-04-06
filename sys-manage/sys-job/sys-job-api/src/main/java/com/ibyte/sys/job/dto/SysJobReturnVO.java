package com.ibyte.sys.job.dto;

import com.ibyte.common.core.dto.IViewObject;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 定时任务执行返回结果
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Getter
@Setter
public class SysJobReturnVO implements IViewObject {

    /**
     * 是否成功
     * true 成功
     * false 失败
     */
    private Boolean success;
    /**
     * 描述信息
     */
    private String content;

    public SysJobReturnVO() {
        this.success = false;
    }

    public SysJobReturnVO(Boolean success) {
        this.success = success;
    }

    public SysJobReturnVO(Boolean success, String content) {
        this.success = success;
        this.content = content;
    }

    @Override
    public List<String> getNullValueProps() {
        return null;
    }

    @Override
    public void setNullValueProps(List<String> list) {

    }

    @Override
    public void addNullValueProps(String... strings) {

    }

    @Override
    public String getFdId() {
        return null;
    }

    @Override
    public void setFdId(String s) {

    }

    @Override
    public Map<String, Object> getMechanisms() {
        return null;
    }

    @Override
    public void setMechanisms(Map<String, Object> map) {

    }

    @Override
    public Map<String, Object> getDynamicProps() {
        return null;
    }

    @Override
    public void setDynamicProps(Map<String, Object> map) {

    }

    @Override
    public Map<String, Object> getExtendProps() {
        return null;
    }
}