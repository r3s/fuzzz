package com.novytes.fuzzz.app.helpers;

import com.novytes.fuzzz.app.models.TargetInfo;

/**
 * Created by res on 21/10/14.
 */
public class TargetHelper {





    public void clearAllFields(TargetInfo target){
        target.setAppName("");
        target.setTargetActivity("");
        target.setType(-1);
        target.setSuccessActivity("");
        target.setFailureActivity("");
        target.setToastText("");
    }

    public void clearField(TargetInfo target, int field){
        switch(field){
            case 0: target.setAppName("");
            case 1: target.setTargetActivity("");
            case 2: target.setType(-1);
            case 3: target.setSuccessActivity("");
            case 4: target.setFailureActivity("");
            case 5: target.setToastText("");
            default: break;
        }
    }

}
