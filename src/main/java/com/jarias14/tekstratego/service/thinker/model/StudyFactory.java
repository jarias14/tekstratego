package com.jarias14.tekstratego.service.thinker.model;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import com.jarias14.tekstratego.common.resource.StudyResource;
import com.jarias14.tekstratego.service.thinker.model.study.AndStudy;
import com.jarias14.tekstratego.service.thinker.model.study.EqualToStudy;
import com.jarias14.tekstratego.service.thinker.model.study.GreaterThanStudy;
import com.jarias14.tekstratego.service.thinker.model.study.LessThanStudy;
import com.jarias14.tekstratego.service.thinker.model.study.OrStudy;

public class StudyFactory {
    
    private static final HashMap<String, Class<? extends Study>> STUDY_MAP = new HashMap<String, Class<? extends Study>>();
    
    static {
        STUDY_MAP.put("or", (Class<? extends Study>) OrStudy.class);
        STUDY_MAP.put("and", (Class<? extends Study>) AndStudy.class);
        STUDY_MAP.put("gt", (Class<? extends Study>) GreaterThanStudy.class);
        STUDY_MAP.put("lt", (Class<? extends Study>) LessThanStudy.class);
        STUDY_MAP.put("et", (Class<? extends Study>) EqualToStudy.class);
    }

    public static Study getInstance(StudyResource resource) {
        Study studyObject = null;

        try {
            // get the class
            Class<? extends Study> studyClass = (Class<? extends Study>)STUDY_MAP.get(resource.getType());
            // get the constructor for the class
            Constructor<? extends Study> studyConstructor = studyClass.getDeclaredConstructor(new Class[] { StudyResource.class });
            // build the object through the constructor 
            studyObject = (Study) studyConstructor.newInstance(resource);
            
        } catch (Exception e) {

        }
        
        return studyObject;
    }
}
