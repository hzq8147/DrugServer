package com.drug.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "drug", schema = "drug", catalog = "")
public class DrugEntity {
    private long id;
    @NotNull(message = "姓名不能为空")
    @Length(max = 255,message = "姓名过长")
    private String name;
    @NotNull(message = "数量不能为空")
    private BigDecimal quantity;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Long rid;
    @NotNull(message = "日常储量不能空")
    private BigDecimal reserves;

    @NotNull(message = "存储教室不能为空")
    private Long placeRoom;
    @NotNull(message = "存储单位不能为空")
    private String reservesUnit;
    @NotNull(message = "主要安全风险不能为空")
    private String securityRisk;
    private String securityRiskRate;
    private String danger;
    private BigDecimal reservesMin;
    private String remark;
    private String management;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 128)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "quantity", nullable = false, precision = 2)
    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "created_at", nullable = false)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updated_at", nullable = false)
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @Column(name = "rid", nullable = true)
    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    @Basic
    @Column(name = "reserves", nullable = false, precision = 2)
    public BigDecimal getReserves() {
        return reserves;
    }

    public void setReserves(BigDecimal reserves) {
        this.reserves = reserves;
    }

    @Basic
    @Column(name = "reserves_unit", nullable = false, length = 5)
    public String getReservesUnit() {
        return reservesUnit;
    }

    public void setReservesUnit(String reservesUnit) {
        this.reservesUnit = reservesUnit;
    }

    @Basic
    @Column(name = "security_risk", nullable = false, length = 128)
    public String getSecurityRisk() {
        return securityRisk;
    }

    public void setSecurityRisk(String securityRisk) {
        this.securityRisk = securityRisk;
    }

    @Basic
    @Column(name = "security_risk_rate", nullable = true, length = 10)
    public String getSecurityRiskRate() {
        return securityRiskRate;
    }

    public void setSecurityRiskRate(String securityRiskRate) {
        this.securityRiskRate = securityRiskRate;
    }

    @Basic
    @Column(name = "danger", nullable = true, length = 20)
    public String getDanger() {
        return danger;
    }

    public void setDanger(String danger) {
        this.danger = danger;
    }

    @Basic
    @Column(name = "reserves_min", nullable = true, precision = 2)
    public BigDecimal getReservesMin() {
        return reservesMin;
    }

    public void setReservesMin(BigDecimal reservesMin) {
        this.reservesMin = reservesMin;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = 100)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "management", nullable = true, length = 100)
    public String getManagement() {
        return management;
    }

    public void setManagement(String management) {
        this.management = management;
    }

    @Basic
    @Column(name = "place_room", nullable = false)
    public Long getPlaceRoom() {
        return placeRoom;
    }

    public void setPlaceRoom(Long placeRoom) {
        this.placeRoom = placeRoom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrugEntity that = (DrugEntity) o;
        return id == that.id &&
                placeRoom == that.placeRoom &&
                Objects.equals(name, that.name) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(rid, that.rid) &&
                Objects.equals(reserves, that.reserves) &&
                Objects.equals(reservesUnit, that.reservesUnit) &&
                Objects.equals(securityRisk, that.securityRisk) &&
                Objects.equals(securityRiskRate, that.securityRiskRate) &&
                Objects.equals(danger, that.danger) &&
                Objects.equals(reservesMin, that.reservesMin) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(management, that.management);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, createdAt, updatedAt, rid, placeRoom, reserves, reservesUnit, securityRisk, securityRiskRate, danger, reservesMin, remark, management);
    }
}
