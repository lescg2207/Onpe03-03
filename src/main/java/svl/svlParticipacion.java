package svl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class svlParticipacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public svlParticipacion() {
        super();
    }

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		dao.OnpeDAO daoOnpe = new dao.OnpeDAO();
		
		//"?id=nacional,AMAZONAS,BAGUA,BAGUA"
		
		//"extranjero" //,ASIA,TOKIO
		
		
		
		
		
		String id = request.getParameter("id");
		String sDPD=null;
		Object data = null;
		
		if ( id != null ) {
			String aID []=id.split(",");
			
			if(aID.length==1)sDPD=aID[0].equals("nacional")?"DEPARTAMENTO":"CONTINENTE";
			else if(aID.length==2)sDPD= aID[0].equals("nacional")?"PROVINCIA":"PAÍS";
			else if(aID.length==3)sDPD= aID[0].equals("nacional")?"DISTRITO":"CIUDAD";
			
			
			
			
			
			
			
			
			
			

			if(aID.length==1)data = daoOnpe.getVotos( id.equals("nacional") ? 1 : 26, id.equals("nacional") ? 25 : 30 );
			if(aID.length==2)data = daoOnpe.getVotosDepartamento( aID[1] );
			if(aID.length==3)data = daoOnpe.getVotosProvincia(aID[2]);
			
			
			
			session.setAttribute("DPD",sDPD );
		}
		
		session.setAttribute("id", id);
		session.setAttribute("data", data);
		
		response.sendRedirect("participacion.jsp");
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
