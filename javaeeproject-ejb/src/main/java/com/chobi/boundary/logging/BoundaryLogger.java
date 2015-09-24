package com.chobi.boundary.logging;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Chobii on 24/09/15.
 */

public class BoundaryLogger {

    @Inject
    private Logger logger;

    @AroundInvoke
    public Object log(InvocationContext ic) throws Exception {
        Long start = System.currentTimeMillis();
        try {
           return ic.proceed();
        } finally {
            Long stop = System.currentTimeMillis() - start;
            logger.log(Level.INFO, "task -- " + ic.getMethod().getName() + " took: " + stop + "ms");
        }
    }
}
