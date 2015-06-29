package com.waelawada.learn.springboot.jsondoc;

import org.jsondoc.core.annotation.ApiFlow;
import org.jsondoc.core.annotation.ApiFlowSet;
import org.jsondoc.core.annotation.ApiFlowStep;

/**
 * Created by waelawada on 6/20/15.
 */
@ApiFlowSet
public class ResidentSignup {

    @ApiFlow(name = "User Registration", steps = {
            @ApiFlowStep(apimethodid = JsonDocConstants.USER_ADD)
    })
    public void userRegistration(){};

}
