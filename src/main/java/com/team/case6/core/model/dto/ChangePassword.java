package com.team.case6.core.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChangePassword {
    @NotNull(message = "?")
    private String oldPassword;
    @NotNull(message = "?")
    private String newPassword;
    @NotNull(message = "?")
    private String confirmNewPassword;
}
