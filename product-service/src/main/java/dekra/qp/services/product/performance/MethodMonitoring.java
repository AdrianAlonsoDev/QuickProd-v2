package dekra.qp.services.product.performance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Aspect to monitor the execution time of the methods
 * and log the execution time in different levels depending on the elapsed time
 */
@Aspect
@Component
public class MethodMonitoring {
    private static final Logger logger = LoggerFactory.getLogger(MethodMonitoring.class);
    enum LogLevel {
        INFO, WARN, ERROR
    }

    private static final long WARNING_THRESHOLD_MS = 35;
    private static final long ERROR_THRESHOLD_MS = 1000;


    /**
     * Logs the execution time of the methods
     * @param joinPoint the join point at which the method is being executed
     * @return the result of the method
     * @throws Throwable if an error occurs
     */
    @Around("execution(public * dekra.qp.services.product.*.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - startTime;

        logMethodExecution(joinPoint, elapsedTime);
        return result;
    }

    private LogLevel determineLogLevel(long elapsedTime) {
        if (elapsedTime > ERROR_THRESHOLD_MS) {
            return LogLevel.ERROR;
        } else if (elapsedTime > WARNING_THRESHOLD_MS) {
            return LogLevel.WARN;
        } else {
            return LogLevel.INFO;
        }
    }

    private void logMethodExecution(ProceedingJoinPoint joinPoint, long elapsedTime) {
        LogLevel logLevel = determineLogLevel(elapsedTime);
        String logMessage = String.format("Method [%s] executed in %d ms", joinPoint.getSignature(), elapsedTime);

        switch (logLevel) {
            case ERROR:
                logger.error(logMessage);
                break;
            case WARN:
                logger.warn(logMessage);
                break;
            case INFO:
                logger.info(logMessage);
                break;
        }
    }
}