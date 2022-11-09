package com.team.case6.core.model;

import com.team.case6.core.model.dto.SignUpForm;
import com.team.case6.core.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class AbstractToken implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn( name = "user_id")
    private User user;

    @OneToOne(targetEntity = SignUpForm.class, fetch = FetchType.EAGER)
    @JoinColumn( name = "sign_up_form_id")
    private SignUpForm signUpForm;
    private Date expiryDate;

    public AbstractToken(final String token) {
        super();
        this.token = token;
        this.expiryDate = GeneralUtils.calculateExpiryDate(EXPIRATION);
    }

    public AbstractToken(final String token, final User user) {
        super();
        this.token = token;
        this.user = user;
        this.expiryDate = GeneralUtils.calculateExpiryDate(EXPIRATION);
    }
    public AbstractToken(final String token, final SignUpForm signUpForm) {
        super();
        this.token = token;
        this.signUpForm = signUpForm;
        this.expiryDate = GeneralUtils.calculateExpiryDate(EXPIRATION);
    }

    public void updateToken(final String token) {
        this.token = token;
        this.expiryDate = GeneralUtils.calculateExpiryDate(EXPIRATION);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
        result = prime * result + ((token == null) ? 0 : token.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractToken other = (AbstractToken) obj;
        if (expiryDate == null) {
            if (other.expiryDate != null) {
                return false;
            }
        } else if (!expiryDate.equals(other.expiryDate)) {
            return false;
        }
        if (token == null) {
            if (other.token != null) {
                return false;
            }
        } else if (!token.equals(other.token)) {
            return false;
        }
        if (user == null&& signUpForm==null) {
            if (other.user != null &&other.signUpForm!=null) {
                return false;
            }
        } else if (!user.equals(other.user)||!signUpForm.equals(other.signUpForm)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Token [String=").append(token).append("]").append("[Expires").append(expiryDate).append("]");
        return builder.toString();
    }
}
