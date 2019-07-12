<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
<style>
    table, th, td {
        border: 1px solid black;
    }
</style>
<body>
<div>
    <h1 style="text-align: center">User list</h1>
</div>
<table style="width:100%;">
    <tr>
        <td style="text-align: center">Username</td>
    </tr>
    <c:forEach items="${userlist}" var="user">
        <tr>
            <td>
                ${user.username}
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</HTML>