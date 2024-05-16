<script setup>
     import {ref,reactive} from 'vue'
    //导入发送请求的axios对象
    import request from '../utils/request'

    import {useRouter} from 'vue-router'

    const router = useRouter()

     let registUser = reactive({
      username:"",
      userPwd:""
     })

     let usernameMsg=ref('')
     let userPwdMsg=ref('')
     let reUserPwdMsg=ref('')
     let reUserPwd=ref('')

      async function checkUsername(){
            // 定义正则
            var usernameReg=/^[a-zA-Z0-9]{5,10}$/
            // 校验用户名
            if(!usernameReg.test(registUser.username)){
                // 格式不合法
                usernameMsg.value="请输入5-10位字母或数字"
                return false
            }

            //继续校验用户名是否已经存在
            let {data} =await request.post(`user/checkUsernameUsed?username=${registUser.username}`)
            if(data.code!=200){
                usernameMsg.value="用户名已经被占用"
                return false
            }else {
                usernameMsg.value="用户名可用"
            }
            
      

            usernameMsg.value="ok"
            return true
        }


         function checkUserPwd(){
            // 定义正则
            var passwordReg=/^[0-9]{5,10}$/
             // 校验密码
             if(!passwordReg.test(registUser.userPwd)){
                // 格式不合法
                userPwdMsg.value="请输入5-10位数字"
                return false
            }
            userPwdMsg.value="ok"
            return true
        }

          function checkReUserPwd(){
            let userPwdReg=/^[0-9]{5,10}$/

            if(!userPwdReg.test(reUserPwd.value)){
              reUserPwdMsg.value="请输入5-10位数字"
              return false
            }
            if(reUserPwd.value!=registUser.userPwd){
              reUserPwdMsg.value="两次输入密码不一致"
              return false
            }
            reUserPwdMsg.value="ok"
            return true
          }

        async function regist(){
            //校验所有的输入框是否合法
            let flag1 = await checkUsername()
            let flag2 = checkUserPwd()
            let flag3 = checkReUserPwd()
            if(flag1&&flag2&&flag3){
                //发送请求
                let {data} = await request.post("user/regist",registUser)
                console.log(data)
                if(data.code==200){
                    //注册成功,跳转页面
                    alert("注册成功，去登录吧")
                    router.push("/login")
                }else{
                    //注册失败
                    alert("抱歉，用户名已经被注册了")
                }
            }else{
                //不通过，不能注册
                return false
            }
        }


</script>

<template>
  <div>
    <h3 class="ht">请注册</h3>

    <table class="tab" cellspacing="0px">
        <tr class="ltr">
            <td>请输入账号</td>
            <td>
                <input class="ipt" 
                  id="usernameInput" 
                  type="text" 
                  name="username" 
                  v-model="registUser.username"
                  @blur="checkUsername()">

                <span id="usernameMsg" class="msg" v-text="usernameMsg"></span>
            </td>
        </tr>
        <tr class="ltr">
            <td>请输入密码</td>
            <td>
                <input class="ipt" 
                id="userPwdInput" 
                type="password" 
                name="userPwd" 
                v-model="registUser.userPwd"
                @blur="checkUserPwd()">
                <span id="userPwdMsg" class="msg" v-text="userPwdMsg"></span>
            </td>
        </tr>
        <tr class="ltr">
            <td>确认密码</td>
            <td>
                <input class="ipt" 
                id="reUserPwdInput" 
                type="password" 
                v-model="reUserPwd"
                @blur="checkReUserPwd()">
                <span id="reUserPwdMsg" class="msg" v-text="reUserPwdMsg"></span>
            </td>
        </tr>
        <tr class="ltr">
            <td colspan="2" class="buttonContainer">
                <input class="btn1" type="button" @click="regist()" value="注册">
                <input class="btn1" type="button" value="重置">
                <router-link to="/login">
                  <button class="btn1">去登录</button>
                </router-link>
            </td>
        </tr>
    </table>


  </div>
</template>

<style scoped>
       .ht{
            text-align: center;
            color: cadetblue;
            font-family: 幼圆;
        }
        .tab{
            width: 500px;
            border: 5px solid cadetblue;
            margin: 0px auto;
            border-radius: 5px;
            font-family: 幼圆;
        }
        .ltr td{
            border: 1px solid  powderblue;

        }
        .ipt{
            border: 0px;
            width: 50%;

        }
        .btn1{
            border: 2px solid powderblue;
            border-radius: 4px;
            width:60px;
            background-color: antiquewhite;

        }
        .msg {
            color: gold;
        }
        .buttonContainer{
            text-align: center;
        }
</style>