# rate-limiter

> 限流是对系统的出入流量进行控制，防止大流量出入，导致资源不足，系统不稳定。

## 描述
本人基于redis配合lua脚本，使用了自己改进后的令牌桶算法实现的限流系统，对于该系统的设计和实现，有兴趣的可以看一下本人写的文章：[基于Redis的限流系统的设计](https://mp.weixin.qq.com/s?__biz=MzI0MTk0NTY5MA==&mid=2247483711&idx=1&sn=28780c8b26f24ac6314ff5c599bb622c&chksm=e9029c0ade75151c353cd6b720ce438b4342afd8ef3a7d03c61712554c6a000ac3646bbc3124&scene=38#wechat_redirect)


## 测试步骤
> 1 调用http://localhost:8080/rate-limiter-server/rate-limiters 保存限流
> {"context":"context","name":"name","maxPermits":100,"rate":50}
> 2 调用RateLimiterClient
> rateLimiterClient.acquire("context", "name");

> https://gitee.com/zmyer/VX-API-Gateway
