package ex3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

@WebServlet("/AopDemoServlet")
public class AopDemoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private ApplicationContext ctx;
	public void init() throws ServletException{
		ctx = new GenericXmlApplicationContext("ex3/ex1_aop.xml");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DaoInter dao = ctx.getBean("dao", DaoInter.class);
		dao.first();
		dao.second();
		dao.third();
	}
}
