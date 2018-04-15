package project.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "aenaflight_source")
public class DestinationFlight implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    @Size(max = 8)
    @Column(name = "adep")
    @NotNull
    private String adep;

    @Size(max = 8)
    @Column(name = "ades")
    @NotNull
    private String ades;

    @Size(max = 8)
    @Column(name = "flight_code")
    @NotNull
    private String flightCode;

    @Size(max = 8)
    @Column(name = "flight_number")
    @NotNull
    private String flightNumber;

    @Size(max = 8)
    @Column(name = "carrier_code")
    private String carrierCode;

    @Size(max = 8)
    @Column(name = "carrier_number")
    private String carrierNumber;

    @Size(max = 256)
    @Column(name = "status_info")
    @NotNull
    private String statusInfo;

    @Column(name = "schd_dep_lt")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date schdDepLt;

    @Column(name = "schd_arr_lt")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date schdArrLt;

    @Column(name = "est_dep_lt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date estDepLt;

    @Column(name = "est_arr_lt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date estArrLt;

    @Column(name = "act_dep_lt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actDepLt;

    @Column(name = "act_arr_lt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actArrLt;

    @Column(name = "flt_leg_seq_no")
    @NotNull
    private Integer fltLegSeqNo;

    @Column(name = "aircraft_name_scheduled")
    private String AircraftNameScheduled;

    @Size(max = 128)
    @Column(name = "baggage_info")
    private String baggageInfo;

    @Size(max = 128)
    @Column(name = "counter")
    private String counter;

    @Size(max = 128)
    @Column(name = "gate_info")
    private String gateInfo;

    @Size(max = 128)
    @Column(name = "lounge_info")
    private String loungeInfo;

    @Size(max = 128)
    @Column(name = "terminal_info")
    private String terminalInfo;

    @Size(max = 128)
    @Column(name = "arr_terminal_info")
    private String arrTerminalInfo;

    @Column(name = "source_data")
    private String sourceData;

    @Column(name = "created_at")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdep() {
        return adep;
    }

    public void setAdep(String adep) {
        this.adep = adep;
    }

    public String getAdes() {
        return ades;
    }

    public void setAdes(String ades) {
        this.ades = ades;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public String getCarrierNumber() {
        return carrierNumber;
    }

    public void setCarrierNumber(String carrierNumber) {
        this.carrierNumber = carrierNumber;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public Date getSchdDepLt() {
        return schdDepLt;
    }

    public void setSchdDepLt(Date schdDepLt) {
        this.schdDepLt = schdDepLt;
    }

    public Date getSchdArrLt() {
        return schdArrLt;
    }

    public void setSchdArrLt(Date schdArrLt) {
        this.schdArrLt = schdArrLt;
    }

    public Date getEstDepLt() {
        return estDepLt;
    }

    public void setEstDepLt(Date estDepLt) {
        this.estDepLt = estDepLt;
    }

    public Date getEstArrLt() {
        return estArrLt;
    }

    public void setEstArrLt(Date estArrLt) {
        this.estArrLt = estArrLt;
    }

    public Date getActDepLt() {
        return actDepLt;
    }

    public void setActDepLt(Date actDepLt) {
        this.actDepLt = actDepLt;
    }

    public Date getActArrLt() {
        return actArrLt;
    }

    public void setActArrLt(Date actArrLt) {
        this.actArrLt = actArrLt;
    }

    public Integer getFltLegSeqNo() {
        return fltLegSeqNo;
    }

    public void setFltLegSeqNo(Integer fltLegSeqNo) {
        this.fltLegSeqNo = fltLegSeqNo;
    }

    public String getAircraftNameScheduled() {
        return AircraftNameScheduled;
    }

    public void setAircraftNameScheduled(String AircraftNameScheduled) {
        this.AircraftNameScheduled = AircraftNameScheduled;
    }

    public String getBaggageInfo() {
        return baggageInfo;
    }

    public void setBaggageInfo(String baggageInfo) {
        this.baggageInfo = baggageInfo;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
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

    public String getSourceData() {
        return sourceData;
    }

    public void setSourceData(String sourceData) {
        this.sourceData = sourceData;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
