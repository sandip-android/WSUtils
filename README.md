# WSUtils

<H3>For HTTP Request</H3>

<H3>There are 2 step for Requst</H3>

<H2>Step-1</H2>

<H4>Raw Data Request (Http Request in which parameter is needed to pass in JSON Form).</H4>

<H4>From Activity</H4>

```JAVA
//For Extra Header add
ArrayList<RequestHeader> requestHeaders = new ArrayList<>();
//requestHeaders.add(new RequestHeader(<Header Name>,<Header Value>));

JSONObject reqBody = new JSONObject();
//Dont put any if request is GET
//reqBody.put(<Key>, <Value>);

HttpRequestJson httpRequestJson = new HttpRequestJson(<ActivityName.this>, <RequestType>, <RequsetHeaders>, <RequsetBody>, <RequestName>);
httpRequestJson.execute(<URL>);
```
<H4>Parameter Description</H4>

1). ActivityName.this -> Name of activity from which Http Request need to execute.  
2). RequestType ->   
	    HttpRequestJson.Type_GET (For GET Request)
	    HttpRequestJson.Type_POST (For POST Request)  
	    HttpRequestJson.Type_PUT (For PUT Request)  
	    HttpRequestJson.Type_DELETE (For DELETE Request).  
3). RequsetHeaders -> List of Request Headers (Empty ArrayList if there is no Header).  
4). RequsetBody -> JSON Parameter in String format (Eg. reqBody.toString()).  
5). RequestName -> Simply identifier for HttpRequest which is used for identifying for Response of Request.  


<H4>From Fragment</H4>
```JAVA
HttpRequestJson httpRequestJson = new HttpRequestJson(getActivity(),<FragmentName.this>, <RequestType>, <RequsetHeaders>, <RequsetBody>, <RequestName>);
httpRequestJson.execute(<URL>);
```
<H4>Parameter Description</H4>

1). getActivity().  
2). FragmentName.this  
3). RequestType -> -> Name of Fragment from which Http Request need to execute.  
	HttpRequestJson.Type_GET (For GET Request)  
	HttpRequestJson.Type_POST (For POST Request)  
	HttpRequestJson.Type_PUT (For PUT Request)  
	HttpRequestJson.Type_DELETE (For DELETE Request).   
4). RequsetHeaders -> List of Request Headers (Empty ArrayList if there is no Header).  
5). RequsetBody -> JSON Parameter in String format (Eg. reqBody.toString()).  
6). RequestName -> Simply identifier for HttpRequest which is used for identifying for Response of Request.  

<H4>Form Data Request (Http Request in which parameter is needed to pass in NamePairValue Form).</H4>

<H4>From Activity</H4>

```JAVA
//For Extra Header add
ArrayList<RequestHeader> requestHeaders = new ArrayList<>();
//requestHeaders.add(new RequestHeader(<Header Name>,<Header Value>));

ContentValues cv = new ContentValues();
//cv.put(<KeyName>,<Value>);

HttpRequestForm httpRequestForm = new HttpRequestForm(<ActivityName.this>, <RequestType>, <RequsetHeaders>, <RequsetBody>, <RequestName>);
httpRequestForm.execute(<URL>);
```
<H4>Parameter Description</H4>

1). ActivityName.this -> Name of activity from which Http Request need to execute.  
2). RequestType ->   
	    HttpRequestForm.Type_GET (For GET Request)
	    HttpRequestForm.Type_POST (For POST Request)  
	    HttpRequestForm.Type_PUT (For PUT Request)  
	    HttpRequestForm.Type_DELETE (For DELETE Request).  
3). RequsetHeaders -> List of Request Headers (Empty ArrayList if there is no Header).  
4). RequsetBody -> Content Values with required parameters.  
5). RequestName -> Simply identifier for HttpRequest which is used for identifying for Response of Request.  


<H4>From Fragment</H4>
```JAVA
HttpRequestForm httpRequestForm = new HttpRequestForm(getActivity(),<FragmentName.this>, <RequestType>, <RequsetHeaders>, <RequsetBody>, <RequestName>);
httpRequestForm.execute(<URL>);
```
<H4>Parameter Description</H4>

1). getActivity().  
2). FragmentName.this  
3). RequestType -> -> Name of Fragment from which Http Request need to execute.  
	HttpRequestForm.Type_GET (For GET Request)  
	HttpRequestForm.Type_POST (For POST Request)  
	HttpRequestForm.Type_PUT (For PUT Request)  
	HttpRequestForm.Type_DELETE (For DELETE Request).   
4). RequsetHeaders -> List of Request Headers (Empty ArrayList if there is no Header).  
5). RequsetBody -> Content Values with required parameters.  
6). RequestName -> Simply identifier for HttpRequest which is used for identifying for Response of Request.  

<H2>Step-2</H2>
<H3>Implement Interface for get Response of Requset</H3>
<H4>For Both Activity and Fragment</H4>
```Java
public class MainActivity extends Activity implements AsyncInterface {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
	@Override
    public void onWSResponse(String json, String WSName) {
        //json is JSON Response
		//WSName is identifier of HttpRequest
    }
}
```
