package zx.soft.sns.redis.common;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自动重试的动态代理
 * @author wanggang
 * @param <T>
 *
 */
public abstract class RetryHandler<T> implements InvocationHandler {

	private final T instance;

	private final int interval;

	private final int count;

	/**
	 * @param instance 实体类
	 * @param interval 重试间隔时间（毫秒）
	 * @param count 重试次数
	 */
	public RetryHandler(T instance, int interval, int count) {
		this.instance = instance;
		this.interval = interval;
		this.count = count;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return retry(method, args, 0);
	}

	protected abstract boolean isRetry(Throwable e);

	private Object retry(Method method, Object[] args, int retryCount) throws Throwable {
		try {
			return method.invoke(instance, args);
		} catch (InvocationTargetException e) {
			Throwable targetException = e.getTargetException();
			if (++retryCount > count) {
				throw targetException;
			}
			if (isRetry(targetException)) {
				Thread.sleep(interval);
				return retry(method, args, retryCount);
			}
			throw targetException;
		}
	}

}
