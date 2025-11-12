<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="beans.Book" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>删除图书</title>
</head>
<body>
<jsp:useBean id="book" class="beans.Book" scope="page" />
<jsp:setProperty name="book" property="isbn" param="isbn" />

<%
    request.setCharacterEncoding("UTF-8");

    try {
        int result = book.delBook();

        if (result > 0) {
%>
<script>
    alert("图书删除成功！");
    window.location.href = "index.jsp";
</script>
<%
} else {
%>
<script>
    alert("删除失败，未找到该图书！");
    window.location.href = "index.jsp";
</script>
<%
    }
} catch (Exception e) {
    e.printStackTrace();
%>
<script>
    alert("系统错误，请稍后重试！");
    window.location.href = "index.jsp";
</script>
<%
    }
%>
</body>
</html>
