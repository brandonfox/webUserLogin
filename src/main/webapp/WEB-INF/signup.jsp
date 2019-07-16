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
    .loginTable{
        width: 60%;
        max-width: 580px;
        position:relative;
        top:40%;
        left:50%;
        transform:translateX(-50%) translateY(-50%);
    }
    .loginTable form{
        background-color: #f0f0f0;
        border:1px solid #ccc;
        box-shadow: 2px 2px #e1e1e1;
    }
    .loginGrid{
        display:grid;
        grid-template-columns: 20% 80%;
        grid-row-gap: 15px;
        padding-top: 20px;
        margin:15px 30px;
    }
    .gridspan{
        grid-column: 1/3;
        text-align: center;
    }
    .loginGrid input[type=text], .loginGrid input[type=password]{
        width:100%;
    }
    .loginRedirect{
        float:right;
        position:relative;
        bottom:17px;
        font-size: 12px;
        margin-right: 5px;
    }
    .errorMsg{
        color: red;
    }
</style>
<body>
<div class="topBar">
    <div class="topBar left" id="homeButton"><a href="/">Home</a></div>
</div>
<div class="loginTable">
    <form action="/signup" method="post">
        <div class="loginGrid">
            <div>Username:</div>
            <div><input type="text" id="usernameTxt" name="username"></div>
            <div>Password:</div>
            <div><input type="password" name="password"></div>
            <div><label>Email:</label></div>
            <div><input type="text" id="emailTxt" name="email"></div>
            <div class="gridspan"><input type="submit" value="Sign up"></div>
            <div class="gridspan errorMsg">${errorMsg}</div>
        </div>
        <div class="loginRedirect">Already have an account? <a href="/login">Log in</a> </div>
    </form>
</div>
</body>
</HTML>