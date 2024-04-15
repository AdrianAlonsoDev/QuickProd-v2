package dekra.qp.services.inventory.performance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Aspect for monitoring method execution time
 */
@Aspect
@Component
public class MethodMonitoring {
    private static final Logger logger = LoggerFactory.getLogger(MethodMonitoring.class);
    private static final long WARNING_THRESHOLD_MS = 35;
    private static final long ERROR_THRESHOLD_MS = 2000;

    @Around("execution(public * dekra.qp.services.inventory.*.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - startTime;

        logMethodExecution(joinPoint, elapsedTime);
        return result;
    }

    enum LogLevel {
        INFO, WARN, ERROR
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
