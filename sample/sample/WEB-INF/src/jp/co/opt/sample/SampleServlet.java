package jp.co.opt.sample;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SampleServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
		System.out.println("get");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("post");
		String param = (String) req.getParameter("param");
		
		List<SampleModel> list = null;
		try {
			list = SampleModel.mainLogic(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		req.setAttribute("resultList", list);
		
		req.getRequestDispatcher("/sample.jsp").forward(req, resp);
	}
}
