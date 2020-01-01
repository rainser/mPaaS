package com.ibyte.sys.org.dto;

import com.ibyte.common.core.dto.IdsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 批量修改上级DTO
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Getter
@Setter
@ToString
public class IdsParentVO extends IdsDTO {

    /**
     * 上级ID
     */
    private String fdParentId;

    public static IdsParentVO of(String fdId, String fdParentId) {
        List<String> fdIds = new ArrayList<>();
        fdIds.add(fdId);
        IdsParentVO dto = new IdsParentVO();
        dto.setFdIds(fdIds);
        dto.setFdParentId(fdParentId);
        return dto;
    }

    public static IdsParentVO of(List<String> fdIds, String fdParentId) {
        IdsParentVO dto = new IdsParentVO();
        dto.setFdIds(fdIds);
        dto.setFdParentId(fdParentId);
        return dto;
    }
}
