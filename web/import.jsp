<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>图书批量导入</title>
</head>
<body>

<h2>📥 图书批量导入</h2>

<form action="book?action=importDo"
      method="post"
      enctype="multipart/form-data">

    <input type="file" name="excelFile" accept=".xls,.xlsx" required/>
    <br><br>

    <input type="submit" value="开始导入"/>
</form>

<p style="color:gray">
    Excel格式：书名 | 作者 | 分类 | ISBN | 数量
</p>

</body>
</html>
