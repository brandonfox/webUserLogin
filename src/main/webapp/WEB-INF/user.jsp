<HTML>
<style>
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
    .user-data-container{
        display: flex;
    }
</style>
<body>
<div class="topBar">
    <div class="topBar left" id="homeButton"><a href="/">Home</a></div>
    <div class="topBar left" id="userButton"><a href="/user">Profile</a></div>
    <div class="topBar right" id="logoutButton"><a href="logout">Logout</a></div>
</div>
<div>
    Profile for ${user.username}
</div>
<form>
<div class="user-data-container">
    <div>
        <label for="email">Email:</label>
        <input type="text" id="email" name="email">
        <div>${user.email}</div>
        <input type="submit" value="Save">
    </div>
</div>
</form>
</body>
</HTML>