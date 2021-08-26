package com.xunli.hosp.yygh.vo.order;

import com.xunli.hosp.yygh.model.base.BaseMongoEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * HospitalSet
 *
 * @author qy
 */
@Data
@ApiModel(description = "签名信息")
public class SignInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "api基础路径")
    private String apiUrl;

    @ApiModelProperty(value = "签名秘钥")
    private String signKey;
}
