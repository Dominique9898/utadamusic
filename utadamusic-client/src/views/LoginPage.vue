<template>
  <el-main class="el-main" :style="defaultHeight">
    <div class="login-reg-panel">
      <div class="login-info-box">
        <h2>Have an account?</h2>
        <label  id="label-register" for="radio-login-surface">LOGIN</label>
        <input class="radio" type="radio" name="active-log-panel" id="radio-login-surface" @click="goLogin">
      </div>

      <div class="register-info-box">
        <h2>Don't have an account ?</h2>
        <label id="label-login" for="radio-register-surface">REGISTER</label>
        <input class="radio" type="radio" name="active-log-panel" id="radio-register-surface" @click="goRegister">
      </div>
      <div class="white-panel">
        <div class="title">
          <h2 style="text-align:left" >{{label}}</h2>
        </div>
        <loginbox v-show="showLog"/>
        <registerbox v-show="showReg"/>

      </div>
    </div>
  </el-main>
</template>

<script>
    import Loginbox from "../components/loginbox";
    import Registerbox from "../components/registerbox";

    export default {
        name: "LoginPage",
        data(){
            return{
                label:"Login",
                showReg:false,
                showLog:true,
                defaultHeight: {
                    height: ""
                }
            }
        },
        components: {Registerbox, Loginbox},
        methods: {
                goRegister:function () {
                    $('.white-panel').addClass('right-moving');
                    this.showReg = true;
                    this.showLog = false;
                    this.label = "Register";
                },
                goLogin:function () {
                    $('.white-panel').removeClass('right-moving');
                    this.showReg = false;
                    this.showLog = true;
                    this.label = "Login";
                },
            getHeight() {
                this.defaultHeight.height = window.innerHeight - 140.8 + "px";
            }
        },
        created() {
            //页面创建时执行一次getHeight进行赋值，顺道绑定resize事件
            window.addEventListener("resize", this.getHeight);
            this.getHeight();
        }
    }
</script>

<style scoped>
   .el-main{
     background: url("../assets/img/bg.jpg");
   }
   .login-reg-panel{
     position: relative;
     text-align:center;
     width:70%;
     margin:auto;
     top: 100px;
     height:400px;
     color: darkslateblue;
     background-color: rgba(255,255,255,.6);
   }
   .radio{
     position:relative;
     display:none;
   }
   .login-info-box{
     width:300px;
     padding:0 50px;
     top:100px;
     left:0;
     position:absolute;
     text-align:left;
   }
   .register-info-box{
     width:300px;
     padding:0 50px;
     top:100px;
     right:0;
     position:absolute;
     text-align:left;
   }
   label{
     border:2px solid;
     padding:0 5px;
     width:150px;
     display:block;
     text-align:center;
     border-radius:4px;
     cursor:pointer;
   }
   .white-panel{
     background-color: rgba(255,255,255,1);
     height:500px;
     position:absolute;
     width:390px;
     transition:.5s ease-in-out;
     top:-50px;
   }
    .title{
      margin-left: 48px;
    }
   .right-moving
   {
     width: 420px;
     right:20px;

   }

</style>
