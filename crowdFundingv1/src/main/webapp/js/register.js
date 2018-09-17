function verifyCode() {
    var cellphone = $("#cellphone").val()
    var user = $("#user").val()
    var password = $("#password").val()
    var passwordtwo = $("#passwordtwo").val()
    if (user.length==0){
        alert("名字不能为空")
        return
    }
    if (password.length<6){
        alert("密码长度不能小于6位")
        return
    }
    if (password != passwordtwo) {
        alert("前后密码不一致")
    }

    $.ajax({
        type:"post",
        contentType:'application/x-www-form-urlencoded; charset=UTF-8',
        url:"login/verifyCode.do",
        method:"post",
        data:{
            cellphone:cellphone,
            usName:user,
            password:md5(password)
        },
        dataType:"json",
        success:function(resp){
            /*console.log(resp)*/
            if (resp['isSuccess']){
                if (cellphone.indexOf("@")!=-1) {
                    alert("邮箱已发送请注意查收验证")
                    window.location.href="admin.html"
                }
                alert("验证码已发送")
            }else{

                alert(resp['error'])
            }
        },
        error:function (resp) {
            console.log(resp)
            alert(resp['error'])
        }
    })
}
function register() {
    var cellphone = $("#cellphone").val()
    var user = $("#user").val()
    var password = $("#password").val()
    var passwordtwo = $("#passwordtwo").val()
    var code = $("#code").val()
    if (user.length==0){
        alert("名字不能为空")
        return
    }
    if (password.length<6){
        alert("密码长度不能小于6位")
        return
    }
    if (password != passwordtwo) {
        alert("前后密码不一致")
    }
    if(code==null){
        alert("请输入验证码")
    }
    $.ajax({
        type:"post",
        contentType:'application/x-www-form-urlencoded; charset=UTF-8',
        url:"login/register.do",
        method:"post",
        data:{
            cellphone:cellphone,
            user:user,
            password:md5(password),
            code:code
        },
        dataType:"json",
        success:function(resp){
            console.log(resp)
            if (resp['isSuccess']){
                alert("注册成功")
                /* window.location.href="admin.html"*/
            }else if (!resp['isSuccess']) {
                alert(resp['content'])
            }
        },
        error:function (resp) {
            console.log(resp)
            tip.text(resp['error'])
        }
    })

}
function md5(password) {
    return hex_md5(password);
}