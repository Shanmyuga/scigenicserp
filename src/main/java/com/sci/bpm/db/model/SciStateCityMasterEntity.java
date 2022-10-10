package com.sci.bpm.db.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SCI_STATE_CITY_MASTER", schema = "SCIGENICS", catalog = "")
public class SciStateCityMasterEntity implements Serializable {
    private long seqStateCtId;
    private String stateName;
    private String stateCode;
    private String cityName;
    private String cityCode;

    @Id
    @Column(name = "SEQ_STATE_CT_ID")
    public long getSeqStateCtId() {
        return seqStateCtId;
    }

    public void setSeqStateCtId(long seqStateCtId) {
        this.seqStateCtId = seqStateCtId;
    }

    @Basic
    @Column(name = "STATE_NAME")
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Basic
    @Column(name = "STATE_CODE")
    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    @Basic
    @Column(name = "CITY_NAME")
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Basic
    @Column(name = "CITY_CODE")
    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SciStateCityMasterEntity that = (SciStateCityMasterEntity) o;

        if (seqStateCtId != that.seqStateCtId) return false;
        if (stateName != null ? !stateName.equals(that.stateName) : that.stateName != null) return false;
        if (stateCode != null ? !stateCode.equals(that.stateCode) : that.stateCode != null) return false;
        if (cityName != null ? !cityName.equals(that.cityName) : that.cityName != null) return false;
        if (cityCode != null ? !cityCode.equals(that.cityCode) : that.cityCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (seqStateCtId ^ (seqStateCtId >>> 32));
        result = 31 * result + (stateName != null ? stateName.hashCode() : 0);
        result = 31 * result + (stateCode != null ? stateCode.hashCode() : 0);
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        result = 31 * result + (cityCode != null ? cityCode.hashCode() : 0);
        return result;
    }
}
