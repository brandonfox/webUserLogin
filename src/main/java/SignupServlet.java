import Database.DataService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignupServlet extends HttpServlet {

    DataService dataService;

    public SignupServlet(DataService dataService){
        this.dataService = dataService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("errorMsg","");

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/signup.jsp");
        rd.include(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
