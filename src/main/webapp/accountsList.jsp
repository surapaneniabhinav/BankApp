<%@ page import="com.main.servlet.Account" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: surap
  Date: 8/26/2017
  Time: 8:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accounts List</title>
</head>
<body>
<form action="accountsList">
    Account List :
</form>


<%
    ArrayList<Account> list = (ArrayList<Account>) request.getAttribute("accList");
    for (Account account  : list){
        out.println("  First Name : " +account.getFirstName() + " LastName : "+ account.getLastName() + " Balance : " + account.getBalance() +"\r \n");
%>
<br/>
<%
    };
%>

<a href="newAccount.html" >New Account</a>
<a href="deleteAccount.html">Delete Account</a>
<a href="updateAccount.html">Update Account</a>

</body>
</html>
