/*
 * 
 * 
 */
package ua.cn.al.teach.library2.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author al
 */
@Entity
@Table(name = "ugroup")
@NamedQueries({
    @NamedQuery(name = "Ugroup.findAll", query = "SELECT u FROM Ugroup u")})
public class Ugroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "group_id")
    private Long groupId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "group_name")
    private String groupName;
    @Size(max = 200)
    @Column(name = "description")
    private String description;
    @ManyToMany(mappedBy = "ugroupList", cascade = CascadeType.DETACH)
    private List<Appuser> appuserList = new ArrayList<>();

    public Ugroup() {
    }

    public Ugroup(Long groupId) {
        this.groupId = groupId;
    }

    public Ugroup(Long groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Appuser> getAppuserList() {
        return appuserList;
    }

    public void setAppuserList(List<Appuser> appuserList) {
        this.appuserList = appuserList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupId != null ? groupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ugroup)) {
            return false;
        }
        Ugroup other = (Ugroup) object;
        if ((this.groupId == null && other.groupId != null) || (this.groupId != null && !this.groupId.equals(other.groupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.cn.al.teach.library2.jpa.Ugroup[ groupId=" + groupId + " ]";
    }
    
}
