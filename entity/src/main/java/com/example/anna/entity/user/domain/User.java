package com.example.anna.entity.user.domain;

import com.example.anna.entity._common.AbstractBaseEntity;
import com.example.anna.entity._common.EnumModel;
import lombok.*;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User extends AbstractBaseEntity {

    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status = Status.ACTIVE;  // 사용자 상태 코드

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private Role role = Role.USER;


    @Getter
    public enum Status implements EnumModel {
        ACTIVE("활성", "ACTIVE"),
        WITHDRAW("탈퇴", "WITHDRAW"),
        INACTIVE("비활성", "INACTIVE");

        private final String korName;
        private final String engName;

        Status(String korName, String engName) {
            this.korName = korName;
            this.engName = engName;
        }

        @Override
        public String getCode() {
            return name();
        }
    }

    @Getter
    public enum Role implements EnumModel {
        USER("일반회원", "USER"),
        ADMIN("관리자", "ADMIN");

        private final String korName;
        private final String engName;

        Role(String korName, String engName) {
            this.korName = korName;
            this.engName = engName;

        }

        @Override
        public String getCode() { return name(); }
    }


    public User(String userId, String password, String email) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    /**
     * 회원 탈퇴
     */
    public void withdrawUser() {
       this.password = "";
       this.status = Status.WITHDRAW;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public boolean checkActiveUser() {
        return this.getStatus().equals(Status.ACTIVE);
    }

}
