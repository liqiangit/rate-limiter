package cn.caijiajia.ratelimiter.client;

import static org.junit.Assert.fail;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext-rateLimiter.xml" })
public class RateLimiterClientTest {
	@Resource
	StringRedisTemplate stringRedisTemplate;
	@Resource
	RedisScript<Long> rateLimiterClientLua;
	@Resource
	RateLimiterClient rateLimiterClient;
	@Test
	public void testRateLimiterClient() {
		for (int i = 0; i < 1000; i++) {
			boolean b = rateLimiterClient.acquire("context", "name");
			if(b){
				System.out.println(b);
			}else{
				System.err.println(b);
			}
//			System.out.println(b);
//			assertTrue(b);
		}
	}

	@Test
	public void testAcquire() {
		AtomicInteger success = new AtomicInteger(0);
		AtomicInteger t = new AtomicInteger(0);
		AtomicInteger fail = new AtomicInteger(0);
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		long start=System.currentTimeMillis();
		int count = 1000;
		CountDownLatch countDownLatch = new CountDownLatch(count);
		for (int i = 0; i < count; i++) {
			executorService.execute(new Runnable() {

				@Override
				public void run() {
					boolean b = rateLimiterClient.acquire("context", "name");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (b) {
						success.incrementAndGet();
					} else {
						fail.incrementAndGet();
					}
					// System.out.println(t.incrementAndGet());
					countDownLatch.countDown();
				}
			});
		}
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end=System.currentTimeMillis();
		System.out.println(end-start);
		System.out.println(Double.valueOf(String.valueOf(Double.valueOf(count+"")/(Double.valueOf(end+"")-Double.valueOf(start+""))))*1000);
		System.out.println(success);
		System.out.println(fail);
	}

	@Test
	public void testAcquireTokenStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testAcquireTokenStringStringInteger() {
		fail("Not yet implemented");
	}

}
