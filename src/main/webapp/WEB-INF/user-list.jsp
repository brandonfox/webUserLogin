<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
<style>
    table, th, td {
        border: 1px solid black;
    }
    .topBar{
        background-color: grey;
        overflow:hidden;
        height:25px;
    }
    .topBar a{
        padding:14px 16px;
        color: azure;
        font-size: 17px;
        text-decoration: none;
        text-align: center;
    }
    .topbar a:hover{
        background-color:black;
        color:white;
    }
    .right{
        float:right;
    }
    .left{
        float:left;
    }
    body{
        margin:0;
    }
</style>
<body>
<div class="topBar">
    <div class="topBar left" id="homeButton"><a href="/">Home</a></div>
    <div class="topBar left" id="userButton"><a href="/user">Profile</a></div>
    <div class="topBar right" id="logoutButton"><a href="logout">Logout</a></div>
</div>
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
                <div>${user.username}</div>
                <c:if test="${username != user.username}">
                    <div style="float:right;">
                        <button onclick="removeUser('${user.username}')">Remove</button>
                    </div>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
<script>
    function removeUser(user){
        if(confirm("Are you sure?")){
            sendRemoveRequest(user);
        }
    }
    function sendRemoveRequest(user){
        var form = document.createElement('form');
        document.body.appendChild(form);
        form.method = 'post';
        form.action = "/remove";
        var input = document.createElement('input');
        input.type = 'hidden';
        input.name = "username";
        input.value = user;
        form.appendChild(input);
        form.submit();
    }
</script>
</body>
</HTML>