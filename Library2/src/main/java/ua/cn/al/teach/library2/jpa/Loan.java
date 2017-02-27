/*
 * 
 * 
 */
package ua.cn.al.teach.library2.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author al
 */
@Entity
@Table(name = "loan")
@NamedQueries({
    @NamedQuery(name = "Loan.findAll", query = "SELECT l FROM Loan l")})
public class Loan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "loan_id")
    private Long loanId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private long userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "be_id")
    private long beId;
    @Column(name = "aqired")
    @Temporal(TemporalType.DATE)
    private Date aqired;
    @Column(name = "returned")
    @Temporal(TemporalType.DATE)
    private Date returned;

    public Loan() {
    }

    public Loan(Long loanId) {
        this.loanId = loanId;
    }

    public Loan(Long loanId, long userId, long beId) {
        this.loanId = loanId;
        this.userId = userId;
        this.beId = beId;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBeId() {
        return beId;
    }

    public void setBeId(long beId) {
        this.beId = beId;
    }

    public Date getAqired() {
        return aqired;
    }

    public void setAqired(Date aqired) {
        this.aqired = aqired;
    }

    public Date getReturned() {
        return returned;
    }

    public void setReturned(Date returned) {
        this.returned = returned;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loanId != null ? loanId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Loan)) {
            return false;
        }
        Loan other = (Loan) object;
        if ((this.loanId == null && other.loanId != null) || (this.loanId != null && !this.loanId.equals(other.loanId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.cn.al.teach.library2.jpa.Loan[ loanId=" + loanId + " ]";
    }
    
}
