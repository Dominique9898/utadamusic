<template>
  <el-main>
    <div class="register">
      <el-form label-width="100px" :model="registerForm" status-icon :rules="rules" ref="registerForm" class="demo-ruleForm">
        <el-form-item prop="username" label="用户名">
          <el-input v-model="registerForm.username" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="密码">
          <el-input type="password" placeholder="密码" v-model="registerForm.password"></el-input>
        </el-form-item>
          <el-form-item prop="repassword" label="确认密码">
              <el-input type="password" placeholder="确认密码" v-model="registerForm.repassword"></el-input>
          </el-form-item>
        <el-form-item prop="email" label="邮箱">
          <el-input v-model="registerForm.email" placeholder="邮箱"></el-input>
        </el-form-item>
        <el-form-item prop="birth" label="生日">
          <el-date-picker type="date" placeholder="选择日期" v-model="registerForm.birth" style="width: 100%;"></el-date-picker>
        </el-form-item>

        <el-form-item label="性别">
          <el-radio-group v-model="registerForm.sex">
            <el-radio :label="0">女</el-radio>
            <el-radio :label="1">男</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-alert v-show="showSuccess" class="local" title="注册成功" type="success" center show-icon></el-alert>
        <el-alert v-show="showError" class="local" title="注册失败" type="error" center show-icon></el-alert>
        <div class="login-btn">
           <el-button type="primary" @click="register">确定</el-button>
        </div>
      </el-form>
    </div>
  </el-main>
</template>

<script>
import {mapState} from 'vuex'
import {mapMutations} from 'vuex'
import axios from 'axios'
    export default {
        name: "registerbox",
        data: function () {
          var validatePass = (rule, value, callback) => {
            if (value === '') {
              callback(new Error('请再次输入密码'));
            } else if (value !== this.registerForm.password) {
              callback(new Error('两次输入密码不一致!'));
            } else {
              callback();
            }
          };
            return {
                showSuccess: false, // 提示成功
                showError: false, // 提示失败
                registerForm: { // 注册
                    username: '',
                    password: '',
                  repassword:'',
                    sex: '',
                    birth: '',
                 },
                rules: {
                    username: [
                        { required: true, trigger: 'blur' }
                    ],
                    password: [
                        { required: true, trigger: 'blur' }
                    ],
                    repassword: [
                    { required: true, validator:validatePass,trigger: 'blur' }
                     ],
                    sex: [
                        { required: true, message: '请选择性别', trigger: 'change' }
                    ],
                    email: [
                        { message: '请输入邮箱地址', trigger: 'blur' },
                        { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
                    ],
                    birth: [
                        { required: true, type: 'date', message: '请选择日期', trigger: 'change' }
                    ],
                },

            }
        },
        methods: {

          register () {
            let _this = this
            let d = _this.registerForm.birth
            var datetime = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate()
            var params = new URLSearchParams()
            params.append('username', _this.registerForm.username)
            params.append('password', _this.registerForm.password)
            params.append('sex', _this.registerForm.sex)
            params.append('phone_num', _this.registerForm.phoneNum)
            params.append('email', _this.registerForm.email)
            params.append('birth', datetime)
            params.append('introduction', _this.registerForm.introduction)
            params.append('location', _this.registerForm.location)
            params.append('avator', '/img/user.jpg')
            axios.post(_this.$store.state.Host + '/api/signup', params)
              .then(response => {
                console.log(response)
                if (response.data.code === 1) {
                  _this.showError = false
                  _this.showSuccess = true
                } else {
                  _this.showSuccess = false
                  _this.showError = true
                }
              })
              .catch(failResponse => {})
          }
        }
    }
</script>

<style scoped>
  .register{
    top:55px;
    position: absolute;
    background-color: rgba(255,255,255,1);
    width: 420px;
    height: 400px;
    padding: 10px 50px 50px 20px;
    left: 0px;
  }
  .login-btn {
    display: flex;
    justify-content: space-between;
    margin-left: 120px;
  }
  .login-btn button{
    width: 100%;
    height:36px;
    margin-top: 40px;
    max-width: 150px;
    background-color:darkslateblue;
    color: #f9f9f9;
    border: none;
    text-transform: uppercase;
    border-radius: 2px;
    float:right;
    cursor:pointer;
    outline: none;
   }
  .local {
    position: absolute;
    width: 270px;
    top: 550px;
    margin-left: 80px;
  }

  .el-main/deep/.el-form-item{
    margin-bottom: 4px;
  }

</style>
