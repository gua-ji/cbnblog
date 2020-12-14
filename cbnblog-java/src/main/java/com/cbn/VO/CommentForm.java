package com.cbn.VO;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class CommentForm {

    @NotBlank(message = "昵称不能为空")
    private String nickName;

    @Email(message = "邮箱不能为空")
    private String email;

    @NotBlank(message = "内容不能为空")
    private String content;

}
