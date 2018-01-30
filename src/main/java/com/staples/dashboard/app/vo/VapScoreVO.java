/**
 * 
 */
package com.staples.dashboard.app.vo;

import java.io.Serializable;

/**
 * @author jaisa001
 *
 */
public class VapScoreVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String vapScoreId;
    
    private String vapScoreType;
    
    private String vapScoreName;
    
    private String vapScoreDesc;

    /**
     * @return the vapScoreId
     */
    public String getVapScoreId() {
        return vapScoreId;
    }

    /**
     * @param vapScoreId the vapScoreId to set
     */
    public void setVapScoreId(String vapScoreId) {
        this.vapScoreId = vapScoreId;
    }

    /**
     * @return the vapScoreType
     */
    public String getVapScoreType() {
        return vapScoreType;
    }

    /**
     * @param vapScoreType the vapScoreType to set
     */
    public void setVapScoreType(String vapScoreType) {
        this.vapScoreType = vapScoreType;
    }

    /**
     * @return the vapScoreName
     */
    public String getVapScoreName() {
        return vapScoreName;
    }

    /**
     * @param vapScoreName the vapScoreName to set
     */
    public void setVapScoreName(String vapScoreName) {
        this.vapScoreName = vapScoreName;
    }

    /**
     * @return the vapScoreDesc
     */
    public String getVapScoreDesc() {
        return vapScoreDesc;
    }

    /**
     * @param vapScoreDesc the vapScoreDesc to set
     */
    public void setVapScoreDesc(String vapScoreDesc) {
        this.vapScoreDesc = vapScoreDesc;
    }
    
}
