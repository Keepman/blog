package com.example.blog.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author: zhangocean
 * @Date: 2018/6/24 9:51
 * Describe: markdown截取文章生成摘要
 */
public class BuildArticleTabloidUtil {

    public static String buildArticleTabloid(String htmlArticleComment) {

        String regex = "\\s+";
        String str = htmlArticleComment.trim();
        //去掉所有空格
        String articleTabloid = str.replaceAll(regex, "");
        String beforeMeg = StringUtils.substringBefore(articleTabloid, "&lt;!--more--&gt;");
        return beforeMeg;
    }


    public static void main(String[] args) {
        String htmlArticleComment = "SpringCloud\n" +
                "\n" +
                "### 基础了解\n" +
                "\n" +
                "主要三大方分别为Provider（生产方）、Consumer（消费方）、Eureka（注册中心）\n" +
                "\n" +
                "### Eureka\n" +
                "\n" +
                "生产方和消费方都要注册在Eureka，下图为注册中心模块的配置\n" +
                "\n" +
                "```java\n" +
                "@SpringBootApplication\n" +
                "@EnableEurekaServer\n" +
                "public class EurekaApplication {public static void main(String[] args) {SpringApplication.run(EurekaApplication.class, args);}}\n" +
                "```\n" +
                "\n" +
                "```YML\n" +
                "server:\n" +
                "  port: 8761\n" +
                "eureka:\n" +
                "  client:\n" +
                "    # 是否要注册到其他Eureka Server实例\n" +
                "    register-with-eureka: false\n" +
                "    # 是否要从其他Eureka Server实例获取数据\n" +
                "    fetch-registry: false\n" +
                "    service-url:\n" +
                "      defaultZone: http://localhost:8761/eureka/\n" +
                "```\n" +
                "\n" +
                "<!--more-->\n" +
                "\n" +
                "如果需要添加登录注册中心的账号密码，可以整合SpringSecurity安全框架，[参考文章](http://www.itmuch.com/spring-cloud/finchley-out-1-eureka-security/)\n" +
                "\n" +
                "```yml\n" +
                "server:\n" +
                "  port: 8761\n" +
                "eureka:\n" +
                "  client:\n" +
                "    # 是否要注册到其他Eureka Server实例\n" +
                "    register-with-eureka: false\n" +
                "    # 是否要从其他Eureka Server实例获取数据\n" +
                "    fetch-registry: false\n" +
                "    service-url:\n" +
                "      defaultZone: http://root:root@localhost:8761/eureka/\u200B\n" +
                "spring:\n" +
                "  security:\n" +
                "    user:\n" +
                "      name: root                # 配置登录的账号是root\n" +
                "      password: root     # 配置登录的密码是root\n" +
                "```\n" +
                "\n" +
                "### Provider\n" +
                "\n" +
                "配置信息\n" +
                "\n" +
                "```YML\n" +
                "server:\n" +
                "  port: 9000\n" +
                "spring:\n" +
                "  application:\n" +
                "    # 指定注册到eureka server上的服务名称\n" +
                "    name: microservice-provider-user\n" +
                "eureka:\n" +
                "  client:\n" +
                "    service-url:\n" +
                "      # 指定eureka server通信地址，注意/eureka/小尾巴不能少\n" +
                "      defaultZone: http://localhost:8761/eureka/\n" +
                "  instance:\n" +
                "    # 是否注册IP到eureka server，如不指定或设为false，那就会注册主机名到eureka server\n" +
                "    prefer-ip-address: true\n" +
                "```\n" +
                "\n" +
                "### Consumer\n" +
                "\n" +
                "配置信息，和Provider的配置除了port和name需要重新设置，其他完全相同\n" +
                "\n" +
                "```yml\n" +
                "server:\n" +
                "  port: 8010\n" +
                "spring:\n" +
                "  application:\n" +
                "    # 指定注册到eureka server上的服务名称\n" +
                "    name: microservice-consumer-movie\n" +
                "\n" +
                "eureka:\n" +
                "  client:\n" +
                "    service-url:\n" +
                "      # 指定eureka server通信地址，注意/eureka/小尾巴不能少\n" +
                "      defaultZone: http://localhost:8761/eureka/\n" +
                "  instance:\n" +
                "    # 是否注册IP到eureka server，如不指定或设为false，那就会注册主机名到eureka server\n" +
                "    prefer-ip-address: true\n" +
                "```\n" +
                "\n" +
                "### Ribbon\n" +
                "\n" +
                "客户端（服务消费者）侧负载均衡组件，不同于Nginx（服务端的负载均衡），默认提供了很多的负载均衡算法，例如轮询、随机、响应时间加权等。当然，为Ribbon自定义负载均衡算法也非常容易，只需实现`IRule` 接口即可\n" +
                "\n" +
                "![图-Ribbon与Eureka配合](http://www.itmuch.com/images/spring-cloud/ribbin-with-eureka.png)\n" +
                "\n" +
                "这里代码演示的是使用RestTemplate实现了基于HTTP的远程调用，但是推荐使用[Web Client](https://github.com/itmuch/thor-test/blob/master/src/main/java/com/itmuch/thor/httpclient/WebClientUtil.java)\n" +
                "\n" +
                "**实现负载均衡步骤**\n" +
                "\n" +
                "- 添加注解`@LoadBalanced`在RestTemplate的bean上\n" +
                "\n" +
                "  ```java\n" +
                "   @Bean\n" +
                "   @LoadBalanced\n" +
                "   public RestTemplate restTemplate() {return new RestTemplate();}\n" +
                "  ```\n" +
                "\n" +
                "- 在消费端的Controller层，调用`restTemplate.getForObject()`方法,url使用`http://{目标服务名称}/{目标服务端点}`的形式，**Ribbon会自动在实际调用时，将目标服务名替换为该服务的IP和端口**。\n" +
                "\n" +
                "```java\n" +
                "@RestController\n" +
                "public class MovieController {\n" +
                "  @Autowired\n" +
                "  private RestTemplate restTemplate;\n" +
                "\n" +
                "  @GetMapping(\"/users/{id}\")\n" +
                "  public User findById(@PathVariable Long id) {\n" +
                "    // 这里用到了RestTemplate的占位符能力\n" +
                "    User user = this.restTemplate.getForObject(\n" +
                "       // http://{目标服务名称}/{目标服务端点}\n" +
                "      \"http://microservice-provider-user/users/{id}\",\n" +
                "      User.class,\n" +
                "      id\n" +
                "    );\n" +
                "    // ...电影微服务的业务...\n" +
                "    return user;\n" +
                "  }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "这里有个小问题，为了测试负载均衡我们需要**开启多个Provider（生产者）实例**，以IDEA为例\n" +
                "\n" +
                "- 点击Edit Configurations\n" +
                "- 右上角的Single instance only取消勾选\n" +
                "- 开启一个Provider实例后，比如占用了8000端口，那么修改yml配置文件，比如修改为9000端口，再次启动，就生成了8000、9000两个端口实例的Provider\n" +
                "\n" +
                "![](https://keeepman.oss-cn-shenzhen.aliyuncs.com/43b82d93f1f2778a8657bedbfc450e9.png)\n" +
                "\n" +
                "\n" +
                "\n" +
                "### 断路器\n" +
                "\n" +
                "实时监测应用，如果发现一定时间失败次数/失败率达到一定的阈值，就直接打开断路器，请求直接返回，而不去调用原本的逻辑。打开后过一段时间后进入半开状态，允许调用一次请求，如果成功，断路器关闭，应用恢复正常，如果还是失败，则继续打开断路器，过段时间后再重复操作。\n" +
                "\n" +
                "### Hystrix\n" +
                "\n" +
                "开源的一个延迟和容错库\n" +
                "\n" +
                "- 包裹请求 **每个命令都在独立的线程中执行**\n" +
                "- 跳闸机制 **当某服务错误率超过一定阈值，可以手动或自动跳闸**\n" +
                "- 资源隔离 **如果线程池满，发往该依赖的请求就被立即拒绝**\n" +
                "- 监控 **可以实时监控运行指标和配置的变化**\n" +
                "- 回退机制 **当请求失败、超时、被拒绝，或当断路器打开时，执行回退逻辑，例如返回一个缺省值**\n" +
                "- 自我修复 **断路器打开一段时间后，会自动进入“半开”状态**\n" +
                "\n" +
                "**使用方法**\n" +
                "\n" +
                "- 启动类中加上`@EnableCircuitBreaker`\n" +
                "\n" +
                "- 添加注解`@HystrixCommand(fallbackMethod = \"findByIdFallback\")`，指定降级方法\n" +
                "\n" +
                "**测试方法**\n" +
                "\n" +
                "将注册中心、Consumer、Provider依次启动，执行Consumer的mapping，成功运行，断开Provider，断路器则自动打开，执行Consumer的mapping时将返回`findByIdFallback()`方法\n" +
                "\n" +
                "可以通过`http://localhost:端口/actuator/health`来检测健康值，包括断路器是否打开呀等等信息。\n" +
                "\n" +
                "```xml\n" +
                "<dependency>\n" +
                "      <groupId>org.springframework.boot</groupId>\n" +
                "      <artifactId>spring-boot-starter-actuator</artifactId>\n" +
                "</dependency>\n" +
                "```\n" +
                "\n" +
                "```YML\n" +
                "management:\n" +
                "  endpoints:\n" +
                "    web:\n" +
                "      exposure:\n" +
                "        # 开放所有监控端点\n" +
                "        include: '*'\n" +
                "  endpoint:\n" +
                "    health:\n" +
                "      # 是否展示健康检查详情\n" +
                "      show-details: always\n" +
                "```\n" +
                "\n" +
                "```json\n" +
                "// 断路器打开后健康显示的状态\n" +
                "\"hystrix\": {\n" +
                "\t\"status\": \"CIRCUIT_OPEN\",\n" +
                "\t\"details\": {\n" +
                "\t\t\"openCircuitBreakers\": [\n" +
                "\t\t\t\"MovieController::findById\"\n" +
                "\t\t]\n" +
                "\t}\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "### Feign\n" +
                "\n" +
                "帮助我们更加便捷、优雅的调用HTTP API，**Feign自带Ribbon和Hystrix **，实现负载均衡\n" +
                "\n" +
                "- 编写Feign Client\n" +
                "\n" +
                "```java\n" +
                "// 对应Provider生产者的name，该name写在了对应生产者的yml配置文件中\n" +
                "@FeignClient(name = \"microservice-provider-user\")\n" +
                "public interface UserFeignClient {\n" +
                "  // 对应Provider生产者的controller的Mapping\n" +
                "  @GetMapping(\"/users/{id}\")\n" +
                "  User findById(@PathVariable(\"id\") Long id);}\n" +
                "```\n" +
                "\n" +
                "- 使用@Autowire注解，注入在Consumer的Controller层中\n" +
                "\n" +
                "```java\n" +
                "@RequestMapping(\"/movies\")\n" +
                "@RestController\n" +
                "public class MovieController {\n" +
                "  @Autowired\n" +
                "  private UserFeignClient userFeignClient;\n" +
                "\n" +
                "  @GetMapping(\"/users/{id}\")\n" +
                "  public User findById(@PathVariable Long id) {\n" +
                "    return this.userFeignClient.findById(id);}}\n" +
                "```\n" +
                "\n" +
                "- Feign整合Hystrix (默认Feign是不启用Hystrix,如需启用，需要在yml中配置，但如此配置的Hystrix 是全局的)\n" +
                "\n" +
                "```YML\n" +
                "## 所有Feign Client都会受到Hystrix保护\n" +
                "feign:\n" +
                "  hystrix:\n" +
                "    enabled: true\n" +
                "```\n" +
                "\n" +
                "```java\n" +
                "// 在注解中添加fallback属性\n" +
                "@FeignClient(name = \"microservice-provider-user\", fallback = UserFeignClientFallback.class)\n" +
                "public interface UserFeignClient {\n" +
                "  @GetMapping(\"/users/{id}\")\n" +
                "  User findById(@PathVariable(\"id\") Long id);\n" +
                "}\n" +
                "\n" +
                "@Component\n" +
                "class UserFeignClientFallback implements UserFeignClient {\n" +
                "  @Override\n" +
                "  public User findById(Long id) {\n" +
                "    return new User(id, \"默认用户\", \"默认用户\", 0, new BigDecimal(1));\n" +
                "  }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "- Hystrix管理图形化界面\n" +
                "\n" +
                "```xml\n" +
                "<dependency>\n" +
                "    <groupId>org.springframework.cloud</groupId>\n" +
                "    <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>\n" +
                "</dependency>\n" +
                "```\n" +
                "\n" +
                "重新立一个子项目，配置给他一个端口即可。可使用重定向功能省去URL的小尾巴，否则需要输入`http://localhost:端口/hystrix`\n" +
                "\n" +
                "- 在Consumer层，在将需要监控的方法上添上注解`@HystrixCommand`这样该方法被调用时，就会产生监控信息\n" +
                "\n" +
                "- 默认不开启该端点的暴露，如需开启需要yml中配置\n" +
                "\n" +
                "  ```YML\n" +
                "  management:\n" +
                "    endpoints:\n" +
                "      web:\n" +
                "        exposure:\n" +
                "          include: 'hystrix.stream'\n" +
                "  ```\n" +
                "\n" +
                "此时访问`http://localhost:端口/actuator/hystrix.stream`即可\n" +
                "\n" +
                "### Zuul路由\n" +
                "\n" +
                "微服务网关，核心是一系列过滤器\n" +
                "\n" +
                "添加注解`@EnableZuulProxy`\n" +
                "\n" +
                "作用简单说，之前访问url需要输入Consumer的端口然后mapping，现在只需要\n" +
                "\n" +
                "`http://127.0.0.1:zuul端口/注册到eureka server上的服务名称/mapping`实现了反向代理功能\n" +
                "\n" +
                "```YML\n" +
                "## 这样访问Zuul的/user/1路径，请求将会被转发到microservice-provider-user的/user/1\n" +
                "zuul:\n" +
                "  routes:\n" +
                "    microservice-provider-user: \n" +
                "      path: /user/**\n" +
                "      strip-prefix: false\n" +
                "```\n" +
                "\n" +
                "[更多配置](http://www.itmuch.com/spring-cloud/finchley-17/)";
        String articleTabloid = buildArticleTabloid(htmlArticleComment);
        System.out.println(articleTabloid);
    }
}

