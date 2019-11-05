package cn.caijiajia.ratelimiter.client;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:applicationContext-rateLimiter.xml"})  
public class RateLimiterClientTest {

	@Resource
	RateLimiterClient rateLimiterClient;
	@Test
	public void testRateLimiterClient() {
		boolean b=rateLimiterClient.acquire("context",UUID.randomUUID().toString());
		System.out.println(b);
		assertTrue(b);
	}

	@Test
	public void testAcquire() {
		fail("Not yet implemented");
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
