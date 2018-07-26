package io.tide.spring.springsecuritytrain;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**

除了“/”,"/home"(首页),"/login"(登录),"/logout"(注销),之外，其他路径都需要认证。
指定“/login”该路径为登录页面，当未认证的用户尝试访问任何受保护的资源时，都会跳转到“/login”。
默认指定“/logout”为注销页面
配置一个内存中的用户认证器，使用admin/admin作为用户名和密码，具有ADMIN角色
防止CSRF攻击
Session Fixation protection(可以参考我之前讲解Spring Session的文章，防止别人篡改sessionId)
Security Header(添加一系列和Header相关的控制)
HTTP Strict Transport Security for secure requests
集成X-Content-Type-Options
缓存控制
集成X-XSS-Protection
X-Frame-Options integration to help prevent Clickjacking(iframe被默认禁止使用)
为Servlet API集成了如下的几个方法
HttpServletRequest#getRemoteUser()
HttpServletRequest.html#getUserPrincipal()
HttpServletRequest.html#isUserInRole(java.lang.String)
HttpServletRequest.html#login(java.lang.String, java.lang.String)
HttpServletRequest.html#logout()
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/", "/home").permitAll()
			.antMatchers("/admin").hasRole("ADMIN")
			.and().formLogin().loginPage("/login").permitAll()
			.and().logout().permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin").password("admin").roles("ADMIN")
			.and()
			.withUser("max").password("max").roles("USER");
	}
	


}
