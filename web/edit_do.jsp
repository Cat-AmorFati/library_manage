<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="beans.Book, java.math.BigDecimal, java.time.LocalDate" %>

<html>
<head>
  <meta charset="UTF-8">
  <title>正在保存修改...</title>
</head>
<body>
<jsp:useBean id="book" class="beans.Book" scope="page" />
<!-- 不自动绑定全部属性，只绑定字符串类型 -->
<jsp:setProperty name="book" property="isbn" />
<jsp:setProperty name="book" property="title" />
<jsp:setProperty name="book" property="author" />
<jsp:setProperty name="book" property="publisher" />
<jsp:setProperty name="book" property="stock" />

<%
  request.setCharacterEncoding("UTF-8");

  try {
    String publishDate = request.getParameter("publishDate");
    String price = request.getParameter("price");

    book.setPublishDate(LocalDate.parse(publishDate));   // 手动转换为 LocalDate
    book.setPrice(new BigDecimal(price));                // 手动转换为 BigDecimal

    int result = book.updateBook();

    if (result > 0) {
%>
<script>
  alert("图书修改成功！");
  window.location.href = "index.jsp";
</script>
<%
} else {
%>
<script>
  alert("修改失败，请检查输入内容！");
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
