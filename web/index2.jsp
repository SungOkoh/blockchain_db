<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2019-05-02
  Time: 오후 3:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>데이터 저장 완료입니다.</h1>
    <form action="Test04" method="post">
        <input type="text" placeholder="데이터값 일단입력" name = "data">
        <input type="submit" value="데이터값 보내기">
        <%out.print((String)request.getAttribute("data"));
          out.print((String)request.getAttribute("hash"));
        %>
</body>
</html>
