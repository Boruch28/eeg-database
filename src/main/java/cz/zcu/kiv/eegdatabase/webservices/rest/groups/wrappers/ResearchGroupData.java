package cz.zcu.kiv.eegdatabase.webservices.rest.groups.wrappers;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Wrapper for research group information.
 *
 * @author Petr Miko
 */
@XmlType
@XmlRootElement(name = "researchGroup")
public class ResearchGroupData {

    private int groupId;
    private String groupName;

    public ResearchGroupData(){}

    public ResearchGroupData(int groupId, String groupName){
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}