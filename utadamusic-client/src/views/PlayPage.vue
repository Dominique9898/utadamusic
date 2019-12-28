<template>
      <el-main>
          <div class="mod_data">
                <span class="data_cover">
            <img :src="Address(this.$store.state.song.picUrl)" :alt="this.$store.state.song.title" class="data_photo">
          </span>
              <div class="data_cont">
                  <div class="data_name">
                      <h1 class="data_name_txt" :title="this.$store.state.song.title">{{this.$store.state.song.title}}</h1>
                  </div>
                  <div class="data_singer">
                      <h3 class="data_singer_txt js_singer" >{{this.$store.state.song.artist}}</h3>
                  </div>
                  <ul class="data__info">
                      <li class="data_info__item ">专辑：{{this.$store.state.song.introduction}}</li>
                      <li class="data_info__item js_genre data_info__item--even" style="">流派：Pop</li>
                      <li class="data_info__item js_public_time data_info__item--even" style="">发行时间：2019-11-06</li>
                  </ul>
                  <div class="data__actions">
                      <a  class="mod_btn_green js_all_play" @click="goPlay">播放</a>
                      <a  @click="follow" class="mod_btn"><i id="follow" class="glyphicon glyphicon-heart" aria-hidden="true"></i>收藏</a>
                      <a class="mod_btn js_into_comment" data-stat="y_new.song.gotocomment" href="#comment_box">评论</a>
                  </div>
              </div>
          </div>
              <div class="mod_comment" id="comment_box">
                  <div class="part__hd">
                      <h2 class="part__tit">评论<span class="c_tx_thin part__tit_desc js_all_comment_num">共{{comtList.length}}条评论</span></h2>
                  </div>
                  <div class="js_cmt_input">
                      <div class="comment__input">
                          <div class="comment__textarea js_comment__textarea  c_bg_normal">
                              <div class="comment__textarea_inner">
                                  <!--c_tx_normal-->
                                  <!--focus评论框的时候将.comment__textarea_default模块隐藏，同时显示.comment__textarea_input-->
                                  <div class="comment__textarea_default c_tx_thin js_reply_text_blur" name="" v-show="reply_text_blur" @focus="show"  contenteditable="true">期待你的神评论……</div>
                                  <div class=" comment__textarea_input c_tx_normal" id="text" v-html="UserComment" @input="handleInput"  v-show="reply_text" @blur="show2" contenteditable="true"></div>
                              </div>
                            </div>
                          <a class="comment__face js_cmt_face" href="javascript:;" data-type="reply"><i class="icon_comment icon_comment_face"></i></a>
                          <div class="comment__tool"><a href="javascript:;" class="mod_btn_green comment__tool_btn" @click="comment">发表评论</a></div>
                      </div>
                  </div>
                  <div class="comment_show">
                      <div class="part__hd">
                          <h2 class="part__tit">全部评论<span class="c_tx_thin part__tit_desc js_all_comment_num">共{{comtList.length}}条评论</span></h2>
                      </div>

                      <div class="each-comment" v-for="(item,index) in comtList" :key="index">
                          <div class="each-comment-avatar">
                              <img alt="" :src="Address(userPic[index])" lazy="loaded">
                          </div>
                          <div class="each-comment-msg">
                              <div><span>{{userName[index]}}</span></div>
                              <div class="each-comment-content text-selection">{{item.content}}</div>
                              <div class="each-comment-time">
                                  <div class="time">{{item.createTime}}</div>
                              </div>
                          </div>
                      </div>
                  </div>
              </div>
      </el-main>
</template>

<script>
import {mixin} from "../mixins";
import {mapGetters} from 'vuex';
import axios from "axios"
export default {
  name: "PlayPage",
  computed:{
    ...mapGetters(
      [
        'avator',
        'username',
        'userId',
        'loginIn'
      ]
    )
  },
   mixins:[mixin],
  mounted:function(){
       // sessionStorage.setItem("picUrl",this.$store.state.song.picUrl);
        this.getComment()
        this.isFolloed()
    },
   data(){
    return{
        reply_text_blur:true,
        reply_text:false,
        isadd:false,
        comtList:[], // 存放评论内容
        userPic: [], // 保存评论用户头像
        userName: [], // 保存评论用户名字
          flag:false,
          id:''   //收藏id
    }
   },
  methods:{
    handleInput($event){
      this.UserComment = $event.target.innerText;
      },
    show:function () {
       this.reply_text_blur = false;
       this.reply_text = true
    },
    show2:function () {

       if(this.UserComment==='')
      {
        this.reply_text_blur = true;
        this.reply_text = false;
      }
    },
    goPlay:function () {
      if(!this.isadd)
      {
        this.isadd = true;
        this.$store.state.playList.push(
          {
            "Songid":this.$store.state.song.Songid,
            "picUrl":this.$store.state.song.picUrl,
            "url":this.$store.state.song.url,
            "artist":this.$store.state.song.artist,
            "album":this.$store.state.song.introduction,
            "titile":this.$store.state.song.title,
            "lyric":this.$store.state.song.lyric,
            "singerId":this.$store.state.song.singerId
          }
        )
      }
         this.$router.push({path: '/player'})
    },
    getUsers (id) {
       axios.get(this.$store.state.Host + '/commentOfConsumer',{params: {
           id:id
         }})
        .then((res)=>{
          this.userPic.push(res.data[0].avator)
          this.userName.push(res.data[0].username)
        })
        .catch(function (error) {
          console.log(error)
        })
    },
    getComment()
    {
       axios.get(this.$store.state.Host+'/songComments',{params: {
           songId:this.$store.state.song.Songid
        }}).then((res)=>{
        this.comtList= res.data
         for (let item of res.data) {
           this.getUsers(item.userId)
         }
      })
        .catch(function (error) {
          console.log(error)
        })
    },
    comment:function () {
      if (this.loginIn) {
        console.log(this.$store.state.song.Songid,this.userId)
        var params = new URLSearchParams()
        params.append('songId',this.$store.state.song.Songid)
        params.append('userId',this.userId)
         params.append('content', this.UserComment)
        axios.post(this.$store.state.Host + '/api/commentList', params)
          .then(response => {
            console.log(response.data)
            if (response.data.code === 1) {
              this.getComment()
               document.getElementById("text").innerHTML='';
              this.$notify({
                title: '评论成功',
                type: 'success'
              })
            } else {
              this.$notify({
                title: '评论失败',
                type: 'error'
              })
            }
          })
          .catch(failResponse => {
          })
      } else {
        this.$notify({
          title: '请先登录',
          type: 'warning'
        })
      }
    },
    isFolloed(){
      axios.get(this.$store.state.Host+'/myCollection',{
        params:{
          userId: this.userId
        }
      }).then(res =>{
        for (let item of res.data) {
          if (item.songId==this.$store.state.song.Songid)
          {
            document.getElementById("follow").style.color = "red";
            this.flag = true
          }
          else {
            document.getElementById("follow").style.color = "color";
          }
        }
        console.log(this.flag)

      }).catch(failResponse => {})
    },
    follow(){
      if(this.loginIn)
      {
        if (this.flag == false)
        {
          //收藏
          var params = new URLSearchParams();
          params.append('userId',this.userId)
          params.append('songId',this.$store.state.song.Songid)
          axios.post(this.$store.state.Host + '/api/addCollections',params).then(response => {
            //console.log(response.data)
            if (response.data.code === 1) {
              document.getElementById("follow").style.color = "red"
              this.flag = true
              this.$notify({
                title: '收藏成功',
                type: 'success'
              })
            } else {
              this.$notify({
                title: '收藏失败',
                type: 'error'
              })
            }
          })
            .catch(failResponse => {this.$notify({
              title: '收藏失败',
              type: 'error'
            })})
        }
        else {
          //取消收藏
          axios.get(this.$store.state.Host+'/api/deleteCollects',{params: {
              userId:this.userId,
              songId:this.$store.state.song.Songid
            }}).then((res)=>{
            //console.log(response.data)
            if (res.data === true) {
              document.getElementById("follow").style.color = "black"
              this.flag = false;
              this.$notify({
                title: '取消收藏成功',
                type: 'success'
              })
            } else {
              this.$notify({
                title: '取消收藏失败',
                type: 'error'
              })
            }
          }).catch(failResponse => {this.$notify({
            title: '取消收藏失败',
            type: 'error'
          })})
        }
      }
      else
      {
        this.$notify({
          title: '请登录',
          type: 'warning'
        })
      }

    },
  }
}
</script>

<style scoped>
    .el-main{
        background-color: white;
        min-height: 1200px;
     }

   .each-comment{
       display: flex;
       margin: 20px 0 20px;
       border-bottom: 1px solid #adadad;
   }
   .each-comment img{
       width: 50px;
       height: 50px;
       border-radius: 50%;
       margin-top: 3px;
   }
   .each-comment-avatar{
       margin-right: 14px;
   }

   .each-comment-msg{
       padding-bottom: 20px;
   }

   span{
       font-weight: 600;
   }
   .each-comment-content, .text-selection{
       line-height: 26px;
   }
   .each-comment-time{
       margin-top: 5px;
   }
   .time{
       opacity: .5;
   }

   .comment_show{
       width: 990px;
       margin-top: 20px;
       margin-bottom: 100px;
       border-radius: 2px;
   }
   .audio-left img {
       width: 40px;
       position: relative;
       top: 15px;
       margin: 0;
       display: initial;   /* 解除与app的样式冲突 */
       cursor: pointer;
   }

   .mod_comment{
       margin-left: 150px;
    }
   .part__hd{
       overflow: hidden;
       height: 65px;
   }
   .part__tit {
       font-size: 24px;
       line-height: 58px;

   }
   .part__tit_desc {
       font-size: 14px;
       margin-left: 12px;
   }

   .c_tx_thin {
       color: #999;
   }
   .mod_data {
       margin-left: 150px;
       position: relative;
       height: 300px;
       padding-left: 305px;
       margin-top: 40px;
       margin-bottom: 35px;
   }
   img {
       border: 0 none;
       max-height: 300px;
       -ms-interpolation-mode: bicubic;
       image-rendering: optimizeQuality;
   }
   .data_cover {
       position: absolute;
       left: 0;
       top: 0;
       width: 300px;
       height: 300px;
   }
   .data_cont {
       padding-top: 13px;
       margin-left: 50px;
   }
   .data_name{
       height:50px;
   }
   .data_name_txt{
       color: #000;
       margin: 0;
       float: left;
       line-height: 50px;
       font-weight: 400;
   }
   .data_singer{
       color: #000;
       overflow: hidden;
       height: 40px;
   }
   .data__actions{
       position: absolute;
       bottom: 23px;
   }
   .mod_btn_green{
       min-width: 122px;
       text-align: center;
       padding: 0 18px;
   }
   .mod_btn{
       border: 1px solid #c9c9c9;
       min-width: 122px;
       text-align: center;
       padding: 0 18px;
   }
   .lyric_cont limit{
       font-family: poppin,Tahoma,Arial,\5FAE\8F6F\96C5\9ED1,sans-serif;
       font-size: 14px;
       color: #000;
       line-height: 26px;
       margin-bottom: 40px;
   }
   .comment__input {
       margin-top: 16px;
       width: 1000px;
       position: relative;
       padding-bottom: 60px;
   }
   .comment__textarea {
       height: 102px;
       border: solid 1px #ececec;
       background-color: #e2e2e2;
   }
   .comment__textarea_inner {
       margin: 10px 14px 0 14px;
   }
   .c_tx_thin {
       color: #999;
   }
   .comment__textarea_input {
       width: 100%;
       border: none;
       height: 66px;
       resize: none;
       overflow: auto;
       outline: 0;
       background: 0 0;
   }
   .comment__tool {
       position: absolute;
       bottom: 10px;
       right: -5px;
   }

   .mod_btn_green {
       border: 1px solid #31c27c;
       background-color: #31c27c;
       color: #fff;
   }
   .mod_btn, .mod_btn_green {
       font-size: 14px;
       margin-right: 6px;
       padding: 0 23px;
       height: 38px;
       line-height: 38px;
       display: inline-block;
   }
   a {
       color: #000;
       text-decoration: none;
   }
   h1{
       margin-top: 10px;
       margin-bottom: 10px;
   }
   h3{
       font-size:25px;
       margin-top: 10px;
       margin-bottom: 10px;
   }
   ul{
       margin-top: 10px;
       margin-bottom: 10px;
   }



</style>
