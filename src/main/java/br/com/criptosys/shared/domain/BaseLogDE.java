package br.com.criptosys.shared.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseLogDE implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(insertable = true, updatable = false, name = "PROCESS_CREATE")
    private String processCreate;

    @Column(insertable = true, updatable = false, unique = false, name = "DATE_CREATE", nullable = false, columnDefinition = "timestamp")
    private Timestamp dateCreate;

    @Column(insertable = true, updatable = false, unique = false, name = "CREATE_USER", length = 20, nullable = false)
    private String createUser;

    @Column(insertable = false, updatable = true, unique = false, name = "PROCESS_UPDATE", length = 70)
    private String processUpdate;

    @Version
    @Column(insertable = true, updatable = true, unique = false, name = "DATE_UPDATE", columnDefinition = "timestamp")
    private Timestamp dateUpdate;

    @Column(insertable = true, updatable = true, unique = false, name = "UPDATE_USER", length = 20)
    private String updateUser;

    @PrePersist
    protected void prePersist() {
        this.setDateCreate(new Timestamp(new Date().getTime()));
        this.setCreateUser(this.getCreateUser());
    }

    @PreUpdate
    protected void preUpdate() {
        Timestamp ts = new Timestamp(Calendar.getInstance().getTime().getTime());
        this.setDateUpdate(ts);
        this.setUpdateUser(getUpdateUser());
    }
}
