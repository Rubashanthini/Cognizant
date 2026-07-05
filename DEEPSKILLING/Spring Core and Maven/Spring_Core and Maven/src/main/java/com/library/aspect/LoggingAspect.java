package com.library.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

/**
 * LoggingAspect - Exercises 3 and 8.
 *
 * A single cross-cutting aspect that provides:
 *   - Exercise 3: execution time logging around service/repository methods.
 *   - Exercise 8: separate Before advice and After advice methods that log
 *     entry into, and exit from, service/repository methods.
 *
 * Registered either explicitly via a <bean> definition in
 * applicationContext.xml (combined with <aop:aspectj-autoproxy/>), or
 * picked up automatically by component-scanning when using
 * applicationContext-annotation.xml (Exercise 6). Either way, AspectJ
 * auto-proxying (enabled via @EnableAspectJAutoProxy in Java config, or
 * <aop:aspectj-autoproxy/> in XML) is what actually wires the advice around
 * the target beans.
 */
@Aspect
@Component
public class LoggingAspect {

    /**
     * Pointcut matching every public method in the service and repository
     * packages, i.e. the classes we want to cross-cut with logging.
     */
    @Pointcut("execution(* com.library.service..*(..)) || execution(* com.library.repository..*(..))")
    public void libraryOperations() {
        // Pointcut signature only - no body required.
    }

    /**
     * Exercise 8 - Before Advice.
     * Runs before the matched method executes and logs the method name.
     *
     * @param joinPoint provides reflective access to the intercepted method
     */
    @Before("libraryOperations()")
    public void logBefore(org.aspectj.lang.JoinPoint joinPoint) {
        System.out.println("[LoggingAspect] BEFORE  -> Entering method: " + joinPoint.getSignature().toShortString());
    }

    /**
     * Exercise 8 - After Advice.
     * Runs after the matched method completes (successfully or not) and
     * logs the method name.
     *
     * @param joinPoint provides reflective access to the intercepted method
     */
    @After("libraryOperations()")
    public void logAfter(org.aspectj.lang.JoinPoint joinPoint) {
        System.out.println("[LoggingAspect] AFTER   -> Exiting method : " + joinPoint.getSignature().toShortString());
    }

    /**
     * Exercise 3 - Execution time logging.
     * Wraps the matched method call and logs how long it took to execute,
     * in milliseconds.
     *
     * @param proceedingJoinPoint allows the aspect to proceed with the
     *                            original method invocation
     * @return whatever the original method returns
     * @throws Throwable if the original method throws
     */
    @Around("libraryOperations()")
    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println("[LoggingAspect] AROUND  -> " + proceedingJoinPoint.getSignature().toShortString()
                + " executed in " + (endTime - startTime) + " ms");
        return result;
    }
}
