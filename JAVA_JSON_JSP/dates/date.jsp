<html>
<!--
  Copyright 2004 The Apache Software Foundation

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->
<head>

<link rel="STYLESHEET" type="text/css" href="dhtmlXTabbar.css">
	<script  src="dhtmlXCommon.js"></script>
        
	<script  src="dhtmlXTabbar.js"></script>
        
        <script  src="Ajax.js"></script>
      
       <script  src="json.js"></script>
      
       <script  src="jQuery.js"></script>
      	  
</head>

<%@ page session="false"%>

<body bgcolor="white">
<jsp:useBean id="readerx" scope="page" class="segReader.segDirectoryReader" type="segReader.segDirectoryReader"/>
<style>
	body {font-size:12px}
	.{font-family:arial;font-size:12px}
	h1 {cursor:hand;font-size:16px;margin-left:10px;line-height:10px}
	xmp {color:green;font-size:12px;margin:0px;font-family:courier;background-color:#e6e6fa;padding:2px}
	div.hdr{
		background-color:lightgreen;
		margin-bottom:10px;
		padding-left:10px;
	}
</style>

<font size=4>
<table>
		<tr>
			<td>
				<div id="a_tabbar" style="width:890px; height:190px;">
                                    
                                
				</div>
		             
			</td>
          
        </tr>
	</table>
        
      
        
<%    

   readerx.openDirectory("C:\\SEGFilesTest\\"); 
   //readerx.openDirectory("C:\\SEGFiles\\"); 
   readerx.ReadAllFiles();
   String [] arrayTest = readerx.getCategories();
%>

<script language="javascript"> 


  var cArray = '[1,2,3,4,5]';
  var data = eval(cArray);
  
 
 var javaScriptArray = '<% 

    
    String cArray = "[";
    
    for(int i = 0; i < arrayTest.length; i++)
    {
        
      if (arrayTest[i] != null)
      {
      cArray += "\"" + arrayTest[i].toString() + "\"";
              
              
      if( arrayTest.length > 0 && i != arrayTest.length-1 ) 
      cArray += ",";
      }
    }
    
    cArray+= "]";
    
    out.print(cArray.toString());
 %>'
  
  
  var datax = eval(javaScriptArray);
   tabbar=new dhtmlXTabBar("a_tabbar","top");
    tabbar.setImagePath("/imgs/");
 
    
    for( var i = 0; i < datax.length-1; i++)
    {
        tabbar.addTab("a1",datax[i],"100px");
    }
    
   /*tabbar.addTab("a2","Tab 1-2","100px");
    tabbar.addTab("a3","Tab 1-3","100px");
    tabbar.setTabActive("a1");
     */       
  

</script>

  <script language="javascript">
    

    
	</script>
        

</font>

</body>
</html>
