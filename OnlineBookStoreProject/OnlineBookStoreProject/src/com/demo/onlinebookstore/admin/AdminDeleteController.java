package com.demo.onlinebookstore.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * Controller for deleting a book from book_bt
 *
 */
@WebServlet("/AdminDeleteController")
public class AdminDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
try {
          //Storing the value from jsp file to a string variables
		String button=request.getParameter("buttonCheck"); 
		int  bookID=Integer.parseInt(request.getParameter("bookid"));
	
           // Creating the reference object of the class AdminLoginBean
		AdminLoginBean adminLoginBean=new AdminLoginBean();
                  //Checking the value in the button for doing defferent function
	if(button.equals("SUBMIT"))
		{
                                    //set the value book id from jsp by using object refference of LogBean Class
		adminLoginBean.setBookID(bookID);
		//Calling the method enterBookDetails in the class AdminDAO
		adminLoginBean= AdminDAO.enterBookDetails(adminLoginBean);
		//Check the value of logBean that is there is book  which has the id similar to bookId from the jsp 
		if(adminLoginBean==null)
		{
                                      //Passsing the message to AdminDeletion jsp page and move to the AdminDeletion jsp page
		 request.setAttribute("error", "No such Book...");
		 request.getRequestDispatcher("/adminDeletion.jsp").forward(request, response);
		}
		else
		{               //Setting the Attributes which we want to pass in to AdminDeletion.jsp
			request.setAttribute("success","yes");
			request.setAttribute("bookid",adminLoginBean.getBookIdDB());
			request.setAttribute("bookname",adminLoginBean.getBookNameDB());
			request.setAttribute("price",adminLoginBean.getPriceDB());
			request.setAttribute("author",adminLoginBean.getAuthorDB());
			request.setAttribute("category",adminLoginBean.getCategoryDB());
			request.setAttribute("language",adminLoginBean.getLanguageDB());
			request.setAttribute("noofcopies",adminLoginBean.getNoCopiesDB());
			request.setAttribute("pubdate",adminLoginBean.getPubDateDB()); 
			request.getRequestDispatcher("/adminDeletion.jsp").forward(request, response);
			
		}
	}
                    //check the button value as DELETE for doing delete operation
	else if(button.equals("DELETE"))
                         {
			
			adminLoginBean.setBookID(bookID);
		//Calling deleteBook method in AdminDAO which returns value as "success" or "Deletion Faild....."
			String value= AdminDAO.deleteBook(adminLoginBean);
               if(value.equals("success"))
                     {
                     //Passing the Attributes to AdminDeletion.jsp 
        	   request.setAttribute("succmessage","Book Successfully Deleted......");
   	   request.getRequestDispatcher("/adminDeletion.jsp").forward(request, response);
        	   
                    }else
                         {
                            //passing the Attributes to AdminDeletion.jsp
        	           request.setAttribute("failmessage",value);
      	               request.getRequestDispatcher("/adminDeletion.jsp").forward(request, response);
           	   
        	   
                           }
			
		}
		else
		{
			request.setAttribute("cancel","Deletion is Canceled....");
  			request.getRequestDispatcher("/adminProperties.jsp").forward(request, response);
       	   
		}
     }catch(NumberFormatException e)
{
    	 request.setAttribute("fail","Enter a valid Book Id...");
         request.getRequestDispatcher("/adminDeletion.jsp").forward(request, response);	 
}
		
}

}
