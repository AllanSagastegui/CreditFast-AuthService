package pe.ask.creditfast.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import pe.ask.creditfast.model.utils.annotations.Loggable;
import pe.ask.creditfast.model.utils.gateways.LoggableGateway;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggableAspect {

    private final LoggableGateway loggableGateway;

    @Around("@annotation(loggable)")
    public Object logAround(
            ProceedingJoinPoint proceedingJoinPoint,
            Loggable loggable
    ) {
        String methodName = proceedingJoinPoint.getSignature().toShortString();
        Object[] args = proceedingJoinPoint.getArgs();

        long startTime = System.currentTimeMillis();

        if (loggable.logArgs()) {
            loggableGateway.info("Starting {} with args: {}", methodName, Arrays.toString(args));
        } else {
            loggableGateway.info("Starting {}", methodName);
        }

        try {
            Object result = proceedingJoinPoint.proceed();

            if (result instanceof Mono<?>) {
                return ((Mono<?>) result)
                        .doOnSuccess(res -> {
                            if (loggable.logResult()) {
                                loggableGateway.info("{} finished with result: {}", methodName, res);
                            } else {
                                loggableGateway.info("{} finished", methodName);
                            }
                        })
                        .doOnError(err -> loggableGateway.error("Error on {}: {}", methodName, err.getMessage()))
                        .doFinally(signal -> {
                            if (loggable.logTime()) {
                                long elapsed = System.currentTimeMillis() - startTime;
                                loggableGateway.trace("{} tomó {} ms", methodName, elapsed);
                            }
                        });
            }

            if (loggable.logResult()) {
                loggableGateway.info("{} finished with result: {}", methodName, result);
            }

            if (loggable.logTime()) {
                long elapsed = System.currentTimeMillis() - startTime;
                loggableGateway.trace("{} takes {} ms", methodName, elapsed);
            }

            return result;

        } catch (Throwable e) {
            loggableGateway.error("Error on {}: {}", methodName, e.getMessage());
            return Mono.error(e);
        }
    }
}
