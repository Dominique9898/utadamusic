<template>
  <div class="login-box">
    <el-form :model="loginForm" status-icon :rules="rules" ref="loginForm"   class="demo-ruleForm" >
      <el-form-item prop="username" label="用户名">
        <el-input placeholder="用户名" v-model="loginForm.username"></el-input>
      </el-form-item>
      <el-form-item prop="password" label="密码">
        <el-input type="password" placeholder="密码" v-model="loginForm.password" @keyup.enter.native="login"></el-input>
      </el-form-item>
        <el-alert v-show="showSuccess" title="登录成功" type="success" center show-icon></el-alert>
        <el-alert v-show="showError" title="账号或密码错误" type="error" center show-icon></el-alert>
      <div class="login-btn" style="margin-top: 10px">
        <el-button class="btn" type="primary" @click="login">登录</el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
    import axios from 'axios'
     export default {
        name: "loginbox",
        data:function()
        {
            var validateName = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('用户名不能为空'))
                } else {
                    callback()
                }
            }
            var validatePassword = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'))
                } else {
                    callback()
                }
            }
            return{
                username: '',
                password: '',
                showSuccess: false, // 提示成功
                showError: false, // 提示失败
                loginForm: { // 登录用户名密码
                    username: '',
                    password: ''
                },
                rules: {
                    username: [
                        { validator: validateName, message: '请输入用户名', trigger: 'blur' }
                    ],
                    password: [
                        { validator: validatePassword, message: '请输入密码', trigger: 'blur' }
                    ]
                }
            }
        },
        methods:{
          copyMsg (item) {
            this.$store.commit('setUserId', item.id)
            window.sessionStorage.setItem('userId', JSON.stringify(item.id))
            this.$store.commit('setUsername', item.username)
            window.sessionStorage.setItem('username', JSON.stringify(item.username))
            this.$store.commit('setAvator', item.avator)
            window.sessionStorage.setItem('avator', JSON.stringify(item.avator))
            console.log(item.avator)
          },
            login () {
                let postData = this.$qs.stringify({
                username:this.loginForm.username,
                password:this.loginForm.password,
              });
                 axios.post(this.$store.state.Host+'/api/loginVerify',postData)
                    .then(response => {
                        // console.log('-----------获取登录信息---------------')
                        if (response.data.code === 1) {
                           this.$notify({
                            title: '欢迎回来',
                            type: 'success'
                          });
                          this.copyMsg(response.data.userMsg[0])
                          this.$store.commit('setLoginIn', true)
                          window.sessionStorage.setItem('loginIn', JSON.stringify(true))
                          this.$router.push({path: '/'})
                        } else {
                            this.showSuccess = false
                            this.showError = true
                        }
                     })
                    .catch(failResponse => {})
            },

        }
    }
</script>

<style scoped>

  .login-box{
    position: absolute;
    top:100px;
    left: 50px;
    padding: 20px 50px 50px 30px;
    width: 300px;
    background-color: white;
    height: 300px;
    border-radius: 10px;
  }
  .login-btn {
    display: flex;
    justify-content: space-between;
  }
  .login-btn button{
    max-width: 150px;
    width: 100%;
    background-color:darkslateblue;
    color: #f9f9f9;
    border: none;
    padding: 10px;
    text-transform: uppercase;
    border-radius: 2px;
    float:right;
    cursor:pointer;
    outline: none;
    margin-left: 40px;
  }
  .local {
    position: absolute;
    width: 280px;
    margin-left: 20px;
    top: 170px;
  }

</style>
