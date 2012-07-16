package com.jarias14.tekstratego.common.resource;

import java.util.Map;

/**
 *
    {
        "type":"lt",
        "studyValue":"25.01",
        "barUnderTest":"0",
        "indicatorId":"ec393711-a289-47b3-9fb8-0d4c3834c85a"
    }
 * @author jarias14
 *
 */
public class StudyResource extends BaseResource {
   
    private static final long serialVersionUID = 1L;
    
    private String type;
    private String studyValue;
    private String barUnderTest;
    private String indicatorId;
    private Map<String, LinksResource> studies;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStudyValue() {
        return studyValue;
    }

    public void setStudyValue(String studyValue) {
        this.studyValue = studyValue;
    }

    public String getBarUnderTest() {
        return barUnderTest;
    }

    public void setBarUnderTest(String barUnderTest) {
        this.barUnderTest = barUnderTest;
    }

    public String getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(String indicatorId) {
        this.indicatorId = indicatorId;
    }

    public Map<String, LinksResource> getStudies() {
        return studies;
    }

    public void setStudies(Map<String, LinksResource> studies) {
        this.studies = studies;
    }
}
