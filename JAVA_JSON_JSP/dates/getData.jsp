<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>

    <body>
    
    
<jsp:useBean id="readerx" scope="page" class="segReader.segDirectoryReader" type="segReader.segDirectoryReader"/>

    
  <% 
     
   response.setHeader("Pragma", "No-cache");
   response.setDateHeader("Expires", 0);
   response.setHeader("Cache-Control", "no-cache");
   response.setContentType("text/html; charset=utf-8");
   
   String query = request.getQueryString();
   
   out.println(query);
   
   readerx.openDirectory("C:\\SEGFilesTEST\\");  
  // readerx.openDirectory("C:\\SEGFiles\\"); 
   
   readerx.ReadAllFiles();
   
      String [] split2 = query.split("\\=");
             
       int split_count = split2.length;
             
     String value = split2[0];
     String value1 = split2[1];
         
       
   // out.println("What is the query value " + value1);
       
   //String [] arrayTest = readerx.getCategories();
   
  Object [] links = readerx.getLinks(value1);
   
   out.println("What is first value of my links " + links[0]);
   
   //out.println("Call made by steve");
  
    String link = "";// (String)links[0];
    
    String myResponse = "";
    int link_count = links.length;
    
    for(int counter = 0; counter < link_count; counter++)
    { 
       link = (String)links[counter];
       
       if(counter == 0 && link_count > 1)
       myResponse = "?{"+ "\"bindings\":[{\"category\":\"" + link + "\"},";
       else if (counter == 0 && link_count == 1)
        myResponse = "?{"+ "\"bindings\":[{\"category\":\"" + link + "\"}";
       else if (counter > 0 && counter != link_count-1)
        myResponse += "{\"category\":\"" + link + "\"},";
       else if( counter == link_count-1)
       myResponse += "{\"category\":\"" + link + "\"}";
           
      
    }
    
     myResponse += "]}?";
     
     out.println(myResponse);
     
    //out.println("?{"+ "\"bindings\":[{\"category\":\"" + link + "\"},");
    //out.println("{\"category\":\"PRI1\"}, ");
    
    
    /*out.println("{\"category\":\"PRI2\"}, ");
    out.println("{\"category\":\"PRI3\"}, ");
    out.println("{\"cat\":\"PRI4\"}, ");
    out.println("{\"ircEvent\":\"PRI5\"}]}?");
    */
    /*out.println("?{"+ "\"bindings\":[{\"ircEvent\":\"PRIVMSG\"},");
    out.println("{\"ircEvent\":\"PRI1\"}, ");
    out.println("{\"ircEvent\":\"PRI2\"}, ");
    out.println("{\"ircEvent\":\"PRI3\"}, ");
    out.println("{\"ircEvent\":\"PRI4\"}, ");
    out.println("{\"ircEvent\":\"PRI5\"}]}?");
    */
  %>
        
    </body>
</html>