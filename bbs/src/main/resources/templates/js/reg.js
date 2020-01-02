
    function validate()
    {

        if (document.loginForm.username.value.length<5)
    {
        alert("账号长度不得小于5");

        return false;
    }
        else if ( document.loginForm.password.value.length < 6)
    {
        alert("密码长度不得小于6");

        return false;
    }
        if (document.loginForm.password.value !== document.loginForm.rePassword.value)
    {
        alert("两次密码不同");

        return false;
    }

    }