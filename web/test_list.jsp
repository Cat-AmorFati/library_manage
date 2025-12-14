<%--
  Created by IntelliJ IDEA.
  User: doro
  Date: 2025/11/7
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="https://jakarta.ee/jsp/jstl/core" %>
<html>
<body>
<c:forEach var="i" begin="1" end="3">
  <p>Hello ${i}</p>
</c:forEach>
</body>
</html>
