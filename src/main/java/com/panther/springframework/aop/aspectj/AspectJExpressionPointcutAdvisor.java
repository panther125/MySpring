package com.panther.springframework.aop.aspectj;

import com.panther.springframework.aop.Pointcut;
import com.panther.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @author Gin 琴酒
 * @data 2023/10/31 10:31
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    // 切面
    private AspectJExpressionPointcut pointcut;

    // 表达式
    private String expression;

    //具体拦截方法
    private Advice advice;


    public void setExpression(String expression){
        this.expression = expression;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == this.pointcut) {
            this.pointcut = new AspectJExpressionPointcut(expression);
        }
        return this.pointcut;
    }
}
