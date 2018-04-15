package project.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "aenaflight_2017_01")
public class SourceFlight implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 64)
    @Column(name = "act_arr_date_time_lt")
    private String actArrDateTimeLt;
    @Size(max = 2147483647)
    @Column(name = "aircraft_name_scheduled")
    private String aircraftNameScheduled;
    @Size(max = 128)
    @Column(name = "arr_apt_name_es")
    private String arrAptNameEs;
    @Size(max = 8)
    @Column(name = "arr_apt_code_iata")
    private String arrAptCodeIata;
    @Size(max = 128)
    @Column(name = "baggage_info")
    private String baggageInfo;
    @Size(max = 128)
    @Column(name = "carrier_airline_name_en")
    private String carrierAirlineNameEn;
    @Size(max = 8)
    @Column(name = "carrier_icao_code")
    private String carrierIcaoCode;
    @Size(max = 8)
    @Column(name = "carrier_number")
    private String carrierNumber;
    @Size(max = 64)
    @Column(name = "counter")
    private String counter;
    @Size(max = 128)
    @Column(name = "dep_apt_name_es")
    private String depAptNameEs;
    @Size(max = 8)
    @Column(name = "dep_apt_code_iata")
    private String depAptCodeIata;
    @Size(max = 64)
    @Column(name = "est_arr_date_time_lt")
    private String estArrDateTimeLt;
    @Size(max = 64)
    @Column(name = "est_dep_date_time_lt")
    private String estDepDateTimeLt;
    @Size(max = 128)
    @Column(name = "flight_airline_name_en")
    private String flightAirlineNameEn;
    @Size(max = 128)
    @Column(name = "flight_airline_name")
    private String flightAirlineName;
    @Size(max = 8)
    @Column(name = "flight_icao_code")
    private String flightIcaoCode;
    @Size(max = 8)
    @Column(name = "flight_number")
    private String flightNumber;
    @Size(max = 8)
    @Column(name = "flt_leg_seq_no")
    private String fltLegSeqNo;
    @Size(max = 128)
    @Column(name = "gate_info")
    private String gateInfo;
    @Size(max = 128)
    @Column(name = "lounge_info")
    private String loungeInfo;
    @Size(max = 32)
    @Column(name = "schd_arr_only_date_lt")
    private String schdArrOnlyDateLt;
    @Size(max = 32)
    @Column(name = "schd_arr_only_time_lt")
    private String schdArrOnlyTimeLt;
    @Size(max = 2147483647)
    @Column(name = "source_data")
    private String sourceData;
    @Size(max = 128)
    @Column(name = "status_info")
    private String statusInfo;
    @Size(max = 128)
    @Column(name = "terminal_info")
    private String terminalInfo;
    @Size(max = 128)
    @Column(name = "arr_terminal_info")
    private String arrTerminalInfo;
    @Column(name = "created_at")
    private BigInteger createdAt;
    @Size(max = 64)
    @Column(name = "act_dep_date_time_lt")
    private String actDepDateTimeLt;
    @Size(max = 32)
    @Column(name = "schd_dep_only_date_lt")
    private String schdDepOnlyDateLt;
    @Size(max = 32)
    @Column(name = "schd_dep_only_time_lt")
    private String schdDepOnlyTimeLt;

    public SourceFlight() {
    }

    public SourceFlight(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActArrDateTimeLt() {
        return actArrDateTimeLt;
    }

    public void setActArrDateTimeLt(String actArrDateTimeLt) {
        this.actArrDateTimeLt = actArrDateTimeLt;
    }

    public String getAircraftNameScheduled() {
        return aircraftNameScheduled;
    }

    public void setAircraftNameScheduled(String aircraftNameScheduled) {
        this.aircraftNameScheduled = aircraftNameScheduled;
    }

    public String getArrAptNameEs() {
        return arrAptNameEs;
    }

    public void setArrAptNameEs(String arrAptNameEs) {
        this.arrAptNameEs = arrAptNameEs;
    }

    public String getArrAptCodeIata() {
        return arrAptCodeIata;
    }

    public void setArrAptCodeIata(String arrAptCodeIata) {
        this.arrAptCodeIata = arrAptCodeIata;
    }

    public String getBaggageInfo() {
        return baggageInfo;
    }

    public void setBaggageInfo(String baggageInfo) {
        this.baggageInfo = baggageInfo;
    }

    public String getCarrierAirlineNameEn() {
        return carrierAirlineNameEn;
    }

    public void setCarrierAirlineNameEn(String carrierAirlineNameEn) {
        this.carrierAirlineNameEn = carrierAirlineNameEn;
    }

    public String getCarrierIcaoCode() {
        return carrierIcaoCode;
    }

    public void setCarrierIcaoCode(String carrierIcaoCode) {
        this.carrierIcaoCode = carrierIcaoCode;
    }

    public String getCarrierNumber() {
        return carrierNumber;
    }

    public void setCarrierNumber(String carrierNumber) {
        this.carrierNumber = carrierNumber;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public String getDepAptNameEs() {
        return depAptNameEs;
    }

    public void setDepAptNameEs(String depAptNameEs) {
        this.depAptNameEs = depAptNameEs;
    }

    public String getDepAptCodeIata() {
        return depAptCodeIata;
    }

    public void setDepAptCodeIata(String depAptCodeIata) {
        this.depAptCodeIata = depAptCodeIata;
    }

    public String getEstArrDateTimeLt() {
        return estArrDateTimeLt;
    }

    public void setEstArrDateTimeLt(String estArrDateTimeLt) {
        this.estArrDateTimeLt = estArrDateTimeLt;
    }

    public String getEstDepDateTimeLt() {
        return estDepDateTimeLt;
    }

    public void setEstDepDateTimeLt(String estDepDateTimeLt) {
        this.estDepDateTimeLt = estDepDateTimeLt;
    }

    public String getFlightAirlineNameEn() {
        return flightAirlineNameEn;
    }

    public void setFlightAirlineNameEn(String flightAirlineNameEn) {
        this.flightAirlineNameEn = flightAirlineNameEn;
    }

    public String getFlightAirlineName() {
        return flightAirlineName;
    }

    public void setFlightAirlineName(String flightAirlineName) {
        this.flightAirlineName = flightAirlineName;
    }

    public String getFlightIcaoCode() {
        return flightIcaoCode;
    }

    public void setFlightIcaoCode(String flightIcaoCode) {
        this.flightIcaoCode = flightIcaoCode;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFltLegSeqNo() {
        return fltLegSeqNo;
    }

    public void setFltLegSeqNo(String fltLegSeqNo) {
        this.fltLegSeqNo = fltLegSeqNo;
    }

    public String getGateInfo() {
        return gateInfo;
    }

    public void setGateInfo(String gateInfo) {
        this.gateInfo = gateInfo;
    }

    public String getLoungeInfo() {
        return loungeInfo;
    }

    public void setLoungeInfo(String loungeInfo) {
        this.loungeInfo = loungeInfo;
    }

    public String getSchdArrOnlyDateLt() {
        return schdArrOnlyDateLt;
    }

    public void setSchdArrOnlyDateLt(String schdArrOnlyDateLt) {
        this.schdArrOnlyDateLt = schdArrOnlyDateLt;
    }

    public String getSchdArrOnlyTimeLt() {
        return schdArrOnlyTimeLt;
    }

    public void setSchdArrOnlyTimeLt(String schdArrOnlyTimeLt) {
        this.schdArrOnlyTimeLt = schdArrOnlyTimeLt;
    }

    public String getSourceData() {
        return sourceData;
    }

    public void setSourceData(String sourceData) {
        this.sourceData = sourceData;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public String getTerminalInfo() {
        return terminalInfo;
    }

    public void setTerminalInfo(String terminalInfo) {
        this.terminalInfo = terminalInfo;
    }

    public String getArrTerminalInfo() {
        return arrTerminalInfo;
    }

    public void setArrTerminalInfo(String arrTerminalInfo) {
        this.arrTerminalInfo = arrTerminalInfo;
    }

    public BigInteger getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(BigInteger createdAt) {
        this.createdAt = createdAt;
    }

    public String getActDepDateTimeLt() {
        return actDepDateTimeLt;
    }

    public void setActDepDateTimeLt(String actDepDateTimeLt) {
        this.actDepDateTimeLt = actDepDateTimeLt;
    }

    public String getSchdDepOnlyDateLt() {
        return schdDepOnlyDateLt;
    }

    public void setSchdDepOnlyDateLt(String schdDepOnlyDateLt) {
        this.schdDepOnlyDateLt = schdDepOnlyDateLt;
    }

    public String getSchdDepOnlyTimeLt() {
        return schdDepOnlyTimeLt;
    }

    public void setSchdDepOnlyTimeLt(String schdDepOnlyTimeLt) {
        this.schdDepOnlyTimeLt = schdDepOnlyTimeLt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SourceFlight)) {
            return false;
        }
        SourceFlight other = (SourceFlight) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.Aenaflight201701[ id=" + id + " ]";
    }

}
