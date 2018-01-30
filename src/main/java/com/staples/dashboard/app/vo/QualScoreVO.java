/**
 * 
 */
package com.staples.dashboard.app.vo;

import java.io.Serializable;

/**
 * @author jaisa001
 *
 */
public class QualScoreVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String qualScoreId;
    
    private String qualScoreName;
    
    private String qualScoreDesc;

    /**
     * @return the qualScoreId
     */
    public String getQualScoreId() {
        return qualScoreId;
    }

    /**
     * @param qualScoreId the qualScoreId to set
     */
    public void setQualScoreId(String qualScoreId) {
        this.qualScoreId = qualScoreId;
    }

    /**
     * @return the qualScoreName
     */
    public String getQualScoreName() {
        return qualScoreName;
    }

    /**
     * @param qualScoreName the qualScoreName to set
     */
    public void setQualScoreName(String qualScoreName) {
        this.qualScoreName = qualScoreName;
    }

    /**
     * @return the qualScoreDesc
     */
    public String getQualScoreDesc() {
        return qualScoreDesc;
    }

    /**
     * @param qualScoreDesc the qualScoreDesc to set
     */
    public void setQualScoreDesc(String qualScoreDesc) {
        this.qualScoreDesc = qualScoreDesc;
    }

}
