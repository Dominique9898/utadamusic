<head>
  <meta name="referrer" content="never">
  <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<template>

  <nav class="navbar navbar-inverse navbar-fixed-top" style="margin-bottom: 0px">
    <div class="container">
      <div class="navbar-header">
        <div class="navbar-brand" href="#">
          <img :src="logo" alt="Brand"  style="margin-top:-15px;">
        </div>
      </div>
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <div class="navbar-brand">UtadaMusic</div>
      </div>
      <div id="navbar" class="collapse navbar-collapse">
        <ul class="nav navbar-nav">
          <li><router-link to="/">首页</router-link></li>
          <li><router-link to="/singer-page">歌手</router-link></li>
          <li><router-link to="/song-list-page">歌单</router-link></li>
          <li><a @click="gotoMe">我的音乐</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li v-show="!loginIn"><router-link to="/login">登录</router-link></li>
          <li class="dropdown" v-show="loginIn">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">{{username}}<span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a @click="gotoMe">我的音乐</a></li>
              <li><a @click="updateAvator">更改头像</a>
                  <el-upload
                          class="upload-demo change"
                          v-show="false"
                          :action="uploadUrl()"
                          :show-file-list="false"
                          :on-success="handleAvatarSuccess"
                          :before-upload="beforeAvatarUpload">
                      <el-button size="small" type="primary">点击上传</el-button>
                  </el-upload>
              </li>
              <li role="separator" class="divider"></li>
              <li><a @click="Exit">退出</a></li>
            </ul>
          </li>
        </ul>
        <img v-if="loginIn" :src= Address(avator) alt="头像" class="img-circle image-responsive" style="width:3%;margin-top:0.77%;float: right" />
        <form class="navbar-form navbar-right" >
          <div class="form-group"  >
            <input type="text" class="form-control"  v-model="KeyWord" placeholder="Search">
          </div>
          <button class="btn btn-primary" @click="gotoSearch">搜索</button>
        </form>
      </div>

      <!--/.nav-collapse -->
    </div>
  </nav>
</template>

<script>
    import logo from '@/assets/img/kyk.png'
    import {mixin} from "../mixins";
    import { mapGetters } from 'vuex'

    export default {
        name: "Header",
        mixins:[mixin],
        computed: {
        ...mapGetters([
          'userId',
          'activeName',
          'avator',
          'username',
          'loginIn'
        ])
      },
        data(){
            return{
                logo,
                KeyWord:'',
                 loginlabel:true,
                 usernamelabel:true
            }
        },
        methods:{
          gotoMe(){
            if(this.loginIn)
            {
              this.$router.push({path: '/me'})
            }
            else {
              this.$notify({
                title: '请先登录',
                type: 'warning'
              })
            }
          },
          updateAvator(){
            document.querySelector('.upload-demo button').click()
          },
          gotoSearch(){
            if (this.KeyWord ==='')
            {
              this.$notify({
                title: '请输入歌手名或者歌曲名',
                type: 'warning'
              })
            }
            else
            {
              this.$router.push({path: '/search', query: {keyword: this.KeyWord}})
            }
          },
          Exit(){
            this.$store.commit('setLoginIn', false)
            window.sessionStorage.setItem('loginIn', JSON.stringify(false))
            this.$store.commit('setUserId','0')
            window.sessionStorage.setItem('userId', JSON.stringify('0'))
            this.$router.push({path: '/login'})
           },
          uploadUrl () {
            let url = 'http://localhost:8080/api/updateUserImg?id=' + this.userId // 生产环境和开发环境的判断
            return url
          },
          handleAvatarSuccess (res, file) {
            let _this = this
            console.log(res)
            if (res.code === 1) {
              _this.imageUrl = URL.createObjectURL(file.raw)
              _this.$store.commit('setAvator', res.avator)
              window.localStorage.setItem('avator', JSON.stringify(res.avator))
              _this.$notify({
                title: '上传成功',
                type: 'success'
              })
            } else {
              _this.$notify({
                title: '上传失败',
                type: 'error'
              })
            }
          },
          beforeAvatarUpload (file) {
            const isJPG = file.type === 'image/jpeg'
            const isLt2M = file.size / 1024 / 1024 < 2
            if (!isJPG) {
              this.$message.error('上传头像图片只能是 JPG 格式!')
            }
            if (!isLt2M) {
              this.$message.error('上传头像图片大小不能超过 2MB!')
            }
            return isJPG && isLt2M
          }
        }
    }

</script>

<style>
  .navbar{
    position: relative;
  }
 .input-group{
   width: 200px;
 }
    a:hover{
        cursor: pointer;
    }
</style>
