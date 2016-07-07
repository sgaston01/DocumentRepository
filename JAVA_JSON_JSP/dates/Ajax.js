


/*

// This is a list of XMLHttpRequest-creation factory functions to try
HTTP._factories = [
    function() { return new XMLHttpRequest(); },
    function() { return new ActiveXObject("Msxml2.XMLHTTP"); },
    function() { return new ActiveXObject("Microsoft.XMLHTTP"); }
];

// When we find a factory that works, store it here.
HTTP._factory = null;

// Create and return a new XMLHttpRequest object.
//
// The first time we're called, try the list of factory functions until
// we find one that returns a non-null value and does not throw an
// exception. Once we find a working factory, remember it for later use.
//
HTTP.newRequest = function() {
    if (HTTP._factory != null) return HTTP._factory();

    for(var i = 0; i < HTTP._factories.length; i++) {
        try {
            var factory = HTTP._factories[i];
            var request = factory();
            if (request != null) {
                HTTP._factory = factory;

                alert("Found a xmlhttprequest object");
                return request;
            }
        }
        catch(e) {
            continue;
        }
    }
    // If we get here, none of the factory candidates succeeded,
    // so throw an exception now and for all future calls.
    HTTP._factory 
 = function() {
        throw new Error("XMLHttpRequest not supported");
    }
    HTTP._factory(); // Throw an error
}

var request = HTTP.newRequest();


request.onreadystatechange = function()
{
   if ( request.readyState == 4 ) 
   { 
        if ( request.status == 200)
            alert(request.response.Text);
    }

}

*/

var xmlHttp;



function GetXmlHttpObject(handler)
{ 
var objXmlHttp=null

if (navigator.userAgent.indexOf("Opera")>=0)
{
alert("This example doesn't work in Opera") 
return 
}
if (navigator.userAgent.indexOf("MSIE")>=0)
{ 
var strName="Msxml2.XMLHTTP"
if (navigator.appVersion.indexOf("MSIE 5.5")>=0)
{
strName="Microsoft.XMLHTTP"
} 
try
{ 
objXmlHttp=new ActiveXObject(strName)
objXmlHttp.onreadystatechange=handler 
return objXmlHttp
} 
catch(e)
{ 
alert("Error. Scripting for ActiveX might be disabled") 
return 
} 
} 
if (navigator.userAgent.indexOf("Mozilla")>=0)
{
objXmlHttp=new XMLHttpRequest()
objXmlHttp.onload=handler
objXmlHttp.onerror=handler 
return objXmlHttp
}
} 



function onResponse()
{
 
 
 alert("Attempt to get the response of the server " + xmlHttp.readyState);

   if (xmlHttp.readyState==4 ) {
   alert("What is the data that is return " + xmlHttp.responseText);
   
   var returnData = xmlHttp.responseText;


   data =   returnData.split("?");

   alert("parsed data is -> " + data[1]);
  
   var mydata = data[1];

   alert("what is my data b4 eval " + mydata );


  // var json = eval( '(' + mydata + ')' );
  // alert("my json string is " + json);

  // alert("What is this crap " + json.name[0].book[0] );
   
  var jArray = mydata.parseJSON();

 /* alert("What is my json here --> " +  jArray.bindings[0].ircEvent +
  "\n " + jArray.bindings[1].ircEvent + 
  "\n " + jArray.bindings[2].ircEvent +
  "\n " + jArray.bindings[3].ircEvent +
  "\n " + jArray.bindings[4].ircEvent );
  */


var dataZone =  document.getElementById('linkzone');
 

var link_length = jArray.bindings.length;

var link_data = "";

for(var i = 0; i < link_length; i++)
{
                
   link_data += "<a href=" + jArray.bindings[i].category + ">" + jArray.bindings[i].category + "</a> <BR>";

  
}


dataZone.innerHTML = link_data;

  

  
  //this._conZone.id = "linkzone";

//var divZone = docuement.getElementById("linkzone");

//divZone.innerHTML =  "alksdjflkajsd;lkfja;lksjdf";
 //jArray.bindings[0].category;


 var divTag =  document.getElementById("div1");

 divTag.innerHTML = "<B>" + jArray.bindings[0].ircEvent + " " + jArray.bindings[1].ircEvent + "</B>";
 divTag.setAttribute("align" , "center");
 
 //create a table

var tablex = document.createElement("div");  // Create the <table> element
tablex.border = 1; 

 
//tablex.innerHTML = "Div STEVE !!!";

tablex.innerHTML = "<TABLE> <TR> <TD> not working correctly </TD> </TR> </TABLE>";

//alert("what is the table --> " + table );
alert("Going here 2");

//document.appendChild(tablex);

var body  = document.body;
 
body.appendChild(tablex);



 }
}

function getLinks(category)
{
  url = "http://localhost:8084/dates/getData.jsp?cat=" + category;
  xmlHttp = GetXmlHttpObject(onResponse)
  xmlHttp.open("GET", url , false);
  xmlHttp.send(null);

}