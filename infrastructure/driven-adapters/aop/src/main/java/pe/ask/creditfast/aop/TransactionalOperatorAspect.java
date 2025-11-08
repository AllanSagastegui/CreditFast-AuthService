package pe.ask.creditfast.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import pe.ask.creditfast.model.utils.annotations.TransactionalOperator;
import pe.ask.creditfast.model.utils.gateways.TransactionalOperatorGateway;
import reactor.core.publisher.Mono;

@Aspect
@Component
@RequiredArgsConstructor
public class TransactionalOperatorAspect {

    private final TransactionalOperatorGateway transactionalOperatorGateway;

    @Around("@annotation(transactionalOperator)")
    public Object aroundTransactionalOperator(
            ProceedingJoinPoint proceedingJoinPoint,
            TransactionalOperator transactionalOperator
    ) {
        final boolean readOnly = transactionalOperator.readOnly();
            try {
                final Object result = proceedingJoinPoint.proceed();
                if (result instanceof Publisher<?> publisher){
                    return transactionalOperatorGateway.execute(publisher, readOnly);
                }
                return Mono.error(IllegalStateException::new);
            } catch (Throwable t) {
                return Mono.error(t);
            }
    }
}
