package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import javax.swing.text.ChangedCharSetException;

import com.sun.net.httpserver.Filter.Chain;

/*
 * ���봦��Ĺ�����
 * 
 * ʵ�ֹ������Ĳ���:
 * 1.������ͨ���࣬ʵ��javax.servlet.Filter�ӿڵ�init,doFilter,destroy����
 * 2.��web.xml�ļ������ù�����
 * <filter>
		<filter-name>EncodingFilter</filter-name>
		<filter-class>ht.Filter.EncodingFilter</filter-class>
		<init-param>
			<param-name>encoding</param-name>
			<param-value>utf-8</param-value>
		</init-param>
	</filter>
	<filter-mapping>
		<filter-name>EncodingFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
	3.ͨ��init������ȡweb.xml�ļ������õĳ�ʼ������
	4.ʵ��doFilter���������б��봦��
	5.���������ú��Ժ���ϵͳ�Զ����У�����Ҫ����,��������ϵͳ����ִ���κ�����
	ע�����
		1.��doFilter������һ��Ҫִ��chain.doFilter(request,response);���
 * */


public class EncodingFilter implements Filter{

	private FilterConfig config;
	private String encoding;
	public void destroy() {
		 config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if(encoding!=null && !encoding.equals("")){
			//���봦��
			request.setCharacterEncoding(encoding);
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		this.config=config;
		encoding = config.getInitParameter("encoding");
	}

}
