
function login(){
      var username = document.getElementById("username");
      var password = document.getElementById("password");
      if(username == ""){
           alert("请输入用户名");
        } else if (password.value =="")
        {
        alert("请输入密码");
        }else if(username.value == "admin"&&password == "123456"){
      window.location.href="welcome.html";
        }






}