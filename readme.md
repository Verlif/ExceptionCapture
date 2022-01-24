# ExceptionCapture

全局异常统一处理服务  
本服务使用在Spring Boot中，通过实现`ExceptionHolder`接口来完成对目标异常的全局处理。

## 使用

1. 添加Jitpack仓库源

> maven
> ```xml
> <repositories>
>    <repository>
>        <id>jitpack.io</id>
>        <url>https://jitpack.io</url>
>    </repository>
> </repositories>
> ```

2. 添加依赖

> maven
> ```xml
>    <dependencies>
>        <dependency>
>            <groupId>com.github.Verlif</groupId>
>            <artifactId>exception-spring-boot-starter</artifactId>
>            <version>2.6.1-beta0.1</version>
>        </dependency>
>    </dependencies>
> ```

3. 启用服务

在任意配置类上使用`@EnableExceptionCapture`注解启用异常捕获服务

4. 对异常的处理

例如对`DuplicateKeyException`异常的全局处理，需要实现`ExceptionHolder`接口并标记`@Component`注解。  
通过`register()`注册需要捕获的异常，通过`handler(T e)`方法来处理异常，并返回前端处理结果或异常意义。

```java
@Component
public class DuplicateKeyExceptionHolder implements ExceptionHolder<DuplicateKeyException> {

    @Override
    public Class<DuplicateKeyException> register() {
        return DuplicateKeyException.class;
    }

    @Override
    public BaseResult<?> handler(DuplicateKeyException e) {
        return new BaseResult<>(ResultCode.FAILURE_PARAMETER).withParam(MessagesUtils.message("error.duplicate_key"));
    }
}
```

5. 自定义基础异常处理

异常捕获服务中内置了基础异常处理类，用于处理未手动捕获的异常。开发者可以通过以下方式实现自定义基础异常处理。  
实现`BaseExceptionHolder`接口并标记`@Component`注解即可。  
也可以通过捕获Throwable异常来实现这一功能。

```java
@Component
public class BaseExceptionHolderImpl implements BaseExceptionHolder {

    @Override
    public Object handler(Throwable throwable) {
        PrintUtils.print(throwable);
        return new FailResult<String>().msg(
                throwable.getMessage() == null ? MessagesUtils.message("error.default") : throwable.getMessage());
    }
}
```
