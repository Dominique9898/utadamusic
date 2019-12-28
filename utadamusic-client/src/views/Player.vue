<template>
    <div class="mod_palyer">
        <div class="bg_player" v-bind:style="{backgroundImage:'url(&quot;' + Address(this.$store.state.song.picUrl) + '&quot;)'}" id="backImg"></div>

        <div class="play_hd">
            <div class="play_style_normal js_box js_full_box">
                <div class="mod_song_info" id="song_info">
                    <div class="song_info__info">
                        <a class="song_info__cover js_album" data-stat="y_new.player.info_area.albumpic">
                            <img :src="Address(this.$store.state.song.picUrl)" id="song_pic" alt="" class="song_info__pic">
                        </a>
                        <div class="song_info__name" id="song_name">歌曲名：{{this.$store.state.song.title}}</div>
                        <div class="song_info__singer" id="singer_name">歌手名：{{this.$store.state.song.artist}}</div>
                        <div class="song_info__album" id="album_name">专辑名：{{this.$store.state.song.introduction}}</div>
                     </div>
                 </div>

                <div class="song_info_lyric">
                    <div class="song_info_lyric_box js_lyric_box">
                        <!-- 控制song_info__lyric_inner来做滚动 -->
                        <div class="lyctable" key="has-lyr" id="qrc_ctn" style="transition: -webkit-transform 0.1s ease-out 0s; transform: translateY(45px);">
                             <p v-for="(item,index) in this.$store.state.song.lyric">{{item.lyric}}</p>
                          </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="audio-box">
            <div class="audio-container" v-on:click="play" >
                <div class="audio-cover" v-bind:style="{backgroundImage:'url(&quot;' + Address(this.$store.state.song.picUrl) + '&quot;)'}"></div>
                <div class="audio-view">
                    <div class="player_music__info" id="sim_song_info">
                        <a :title="this.$store.state.song.title" class="js_song" data-stat="y_new.player.info_area.songname" data-mid="002UhMxh3kc66x" data-id="228515846" data-songtype="0" data-disstid="" rel="noopener nofollow" target="_blank">{{this.$store.state.song.title}}</a>
                        <a :title="this.$store.state.song.artist" class="js_singer"><router-link :to="Address">{{this.$store.state.song.artist}}</router-link></a>
                    </div>
                    <audio style="margin-left: 20px;height: 20px;width: 900px" id="player"  controls ref="songAudio" @canplay="getDuration" @timeupdate="updateTime"  >
                        <source :src="Address(this.$store.state.song.url)">
                    </audio>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import {mixin} from "../mixins";

export default {
  name: "Player",
  data(){
    return{
      lyric:[],
      currentTime:'00:00',
      totalTime:'',
      playFlag:false,
      lrcTop: 200 + 'px', // 歌词滑动
    }
  },
  mounted(){
    this.getLyric()
  },
  watch:{
    // 监听音乐播放状态：改变播放或暂停按钮
    currentTime: function () {
      // 处理歌词位置及颜色
      var len = this.$store.state.song.lyric.length
       if (len !== 0) {
        for (var i = 0; i < len-1; i++) {
           if (this.currentTime >= this.$store.state.song.lyric[i].time) {
              for (var j = 0; j < len-1; j++) {
              document.querySelectorAll('.lyctable p')[j].classList.remove("lineHeigh")
                 }
                if (i >= 0) {
                  document.querySelectorAll('.lyctable p')[i].classList.add("lineHeigh")
                 }
          }
        }
      }
    }
  },

  methods:{

    getLyric(){
      this.lyc = this.parseLyric(this.$store.state.song.lyric)
    },
    updateTime(e){
      this.currentTime = e.target.currentTime;
    },
    getDuration(){
      this.totalTime = this.transTime(this.$refs.songAudio.duration)
      // console.log(this.totalTime); //此时可以获取到duration
    },
    transTime(time) {
      var duration = parseInt(time);
      var minute = parseInt(duration/60);
      var sec = duration%60+'';
      var isM0 = ':';
      if(minute == 0){
        minute = '00';
      }else if(minute < 10 ){
        minute = '0'+minute;
      }
      if(sec.length == 1){
        sec = '0'+sec;
      }
      return minute+isM0+sec
    },
    play(){
      var player = document.querySelector('#player')
      if(player !==null)
      {
        if (this.playFlag)
        {
          player.pause()
            document.querySelector('.audio-cover').classList.remove("audio-cover-rotation")
          this.playFlag = false;
        }
        else
        {
          player.play()
          document.querySelector('.audio-cover').classList.add("audio-cover-rotation")
          this.playFlag = true;
        }
      }

    },
  },
  mounted(){
    this.lyc = this.parseLyric(this.$store.state.song.lyric)

   },
  mixins:[mixin],
}
</script>

<style scoped>
    .lineHeigh{
        color: #31c27c;
        font-size: 17px;
     }
    .bg_player{
        color: #000;
        font-size: 14px;
        line-height: 1.5;
        font-family: poppin,Tahoma,Arial,\5FAE\8F6F\96C5\9ED1,sans-serif;
        width: 100%;
        height: 100%;
        overflow: hidden;
        position: absolute;
        top: 0;
        left: 0;
        background-repeat: no-repeat;
        background-size: cover;
        background-position: 50%;
        filter: blur(65px);
        opacity: .6;
        transform: translateZ(0);
        display: block;
     }

    .mod_palyer{
        color: #000;
        font-size: 14px;
        line-height: 1.5;
        font-family: poppin,Tahoma,Arial,\5FAE\8F6F\96C5\9ED1,sans-serif;
        max-width: 1000px;
        margin-top: 10px;
        margin-left: 20%;
        margin-right: 20%;
        position: relative;
        z-index: 2;
    }
    .play_hd{
        color: #000;
        font-size: 14px;
        line-height: 1.5;
        font-family: poppin,Tahoma,Arial,\5FAE\8F6F\96C5\9ED1,sans-serif;
        position: relative;
        height: 500px;
        text-align: center;
        margin-top: 40px;
        margin-bottom: 50px;
    }
    .play_style_normal{
        font-size: 14px;
        line-height: 1.5;
        font-family: poppin,Tahoma,Arial,\5FAE\8F6F\96C5\9ED1,sans-serif;
        color: rgba(225,225,225,.8);
        cursor: default;
        margin-right: 300px;
    }
    .mod_song_info{
        font-family: poppin,Tahoma,Arial,\5FAE\8F6F\96C5\9ED1,sans-serif;
        color: rgba(225,225,225,.8);
        cursor: default;
        position: absolute;
        top: 0;
        left: 0;
        text-align: center;
        font-size: 14px;
        line-height: 24px;
        width: 400px;
        height: 500px;
    }
    .song_info__info{
        margin-top: 30px;
        font-family: poppin,Tahoma,Arial,\5FAE\8F6F\96C5\9ED1,sans-serif;
        cursor: default;
        text-align: center;
        font-size: 14px;
        line-height: 50px;
    }
    .mod_btn{
        text-decoration: none;
        border-radius: 2px;
        font-size: 14px;
        margin-right: 6px;
        height: 38px;
        line-height: 38px;
        display: inline-block;
        white-space: nowrap;
        box-sizing: border-box;
        overflow: hidden;
        border: 1px solid #fff;
        background-color: #fff;
        color: #000;
        min-width: 105px;
        text-align: center;
        padding: 0 18px;
    }
    .song_info__name{
        font-family: poppin,Tahoma,Arial,\5FAE\8F6F\96C5\9ED1,sans-serif;
        color: #000;
        cursor: default;
        text-align: center;
        font-size: 14px;
        line-height: 24px;
        margin-top: 40px;
        white-space: nowrap;
        width: 100%;
        overflow: hidden;
        text-overflow: ellipsis;
        height: 28px;
    }
    .song_info__singer{
        font-family: poppin,Tahoma,Arial,\5FAE\8F6F\96C5\9ED1,sans-serif;
        color: #000;
        cursor: default;
        text-align: center;
        font-size: 14px;
        line-height: 24px;
        white-space: nowrap;
        width: 100%;
        overflow: hidden;
        text-overflow: ellipsis;
        height: 28px;
    }
    .song_info__album{
        font-family: poppin,Tahoma,Arial,\5FAE\8F6F\96C5\9ED1,sans-serif;
        color: #000;
        cursor: default;
        text-align: center;
        font-size: 14px;
        line-height: 24px;
        margin-bottom: 10px;
        white-space: nowrap;
        width: 100%;
        overflow: hidden;
        text-overflow: ellipsis;
        height: 28px;
    }
    .song_info__cover{
        font-family: poppin,Tahoma,Arial,\5FAE\8F6F\96C5\9ED1,sans-serif;
        text-align: center;
        font-size: 14px;
        line-height: 24px;
        text-decoration: none;
        position: relative;
        display: block;
        width: 315px;
        height: 315px;
        margin: auto;
        color: rgba(225,225,225,.8);
    }
    .song_info__pic{
        font-family: poppin,Tahoma,Arial,\5FAE\8F6F\96C5\9ED1,sans-serif;
        text-align: center;
        font-size: 14px;
        line-height: 24px;
        color: rgba(225,225,225,.8);
        border: 0 none;
        vertical-align: middle;
        width: 315px;
        height: 315px;
    }
    .mod_songlist_toolbar{
        line-height: 1.5;
        font-family: poppin,Tahoma,Arial,\5FAE\8F6F\96C5\9ED1,sans-serif;
        color: rgba(225,225,225,.8);
        cursor: default;
        position: relative;
        margin-top: 30px;
        font-size: 0;
        text-align: center;
        right: 0;
    }
    .song_info_lyric{
        font-family: poppin,Tahoma,Arial,\5FAE\8F6F\96C5\9ED1,sans-serif;
        color: #000;
        cursor: default;
        position: absolute;
        top: 0;
        right: 0;
        height: 100%;
        text-align: center;
        font-size: 14px;
        line-height: 24px;
        width: 400px;
    }
    .song_info_lyric_box{
        font-family: poppin,Tahoma,Arial,\5FAE\8F6F\96C5\9ED1,sans-serif;
        color:#000;
        text-align: center;
        font-size: 14px;
        line-height: 24px;
        position: absolute;
        left: 0;
        right: 0;
        bottom: 0;
        overflow: hidden;
        margin-right: -30px;
        overflow-y: auto;
        top: 50%;
        height: 500px;
        margin-top: -250px;
        cursor: default;
        width: 100%;
    }
    ul li{
        list-style-type: none;
    }
    .audio-box{
        color: #000;
        font-size: 14px;
        line-height: 1.5;
        font-family: poppin,Tahoma,Arial,\5FAE\8F6F\96C5\9ED1,sans-serif;
        position: relative;
        margin-bottom: 30px;
    }
    .audio-container{
        width: 100%;
        margin: auto;
    }
    .player_music__info{
        color: #fff;
        margin: 0;
        padding: 0;
        width: 100%;
        padding-left: 20px;
        font-weight: 400;
        font-size: 15px;
        line-height: 38px;
        overflow: hidden;
    }
    .audio-view{
        float: right;
        width: calc(100% - 66px);
        height: 66px;
    }
    .audio-cover{
        float: left;
        margin: 5px;
        width: 56px;
        height: 56px;
        background-size: auto 100%;
        background-repeat: no-repeat;
        background-position: center center;
        background-color: #000;
        border-radius: 50%;
    }
    .audio-cover-rotation{
        -webkit-animation: rotating 9s linear infinite;
        animation: rotating 9s linear infinite;
    }
    .audio-cover:hover{
        cursor: pointer;
    }
    .audio-cover img{
        display: block;
        height: 100%;
        margin: auto;
    }

    .audio-select > div + div{
        margin-left: 10px;
    }
    .audio-list-head p{
        padding-left: 8px;
        font-size: 16px;
        color: #47a3da;
    }
    .audio-list-head span{
        position: absolute;
        right: 6px;
        top: 0;
        font-size: 12px;
        display: block;
        width: 35px;
        line-height: 20px;
        border: 1px solid #eee;
        border-radius: 3px;
        text-align: center;
        cursor: pointer;
        color: #f10b0b;
    }
    .audio-inline li{
        font-size: 12px;
        line-height: 2.6;
        padding-left: 8px;
        padding-right: 8px;
        border-top: 1px solid #ccc;
    }
    .audio-inline li:first-child{
        border-top: 0 solid #ccc;
    }
    .audio-inline a{
        font-size: inherit;
        text-decoration: none;
        height: 30px;
        overflow: hidden;
        display: block;
        text-overflow: ellipsis;
        white-space: nowrap;
        color: #888;
    }

    .volume-box span{
        display: block;
        width: 8px;
        height: 8px;
        position: absolute;
        background-color: #fff;
        border-radius: 50%;
        margin-left: -4px;
        margin-top: -4px;
        left: 50%;
        cursor: pointer;
    }
    .volume-box i{
        position: absolute;
        width: 100%;
        border-radius: 2.5px;
        background-color: #47a3da;
        height: 0;
        display: block;
        bottom: 0;
    }



    @-webkit-keyframes rotating {
        from{
            -webkit-transform: rotate(0deg);
            -moz-transform: rotate(0deg);
            -ms-transform: rotate(0deg);
            -o-transform: rotate(0deg);
            transform: rotate(0deg);
        }
        to{
            -webkit-transform: rotate(360deg);
            -moz-transform: rotate(360deg);
            -ms-transform: rotate(360deg);
            -o-transform: rotate(360deg);
            transform: rotate(360deg);
        }
    }
    @keyframes rotating {
        from{
            -webkit-transform: rotate(0deg);
            -moz-transform: rotate(0deg);
            -ms-transform: rotate(0deg);
            -o-transform: rotate(0deg);
            transform: rotate(0deg);
        }
        to{
            -webkit-transform: rotate(360deg);
            -moz-transform: rotate(360deg);
            -ms-transform: rotate(360deg);
            -o-transform: rotate(360deg);
            transform: rotate(360deg);
        }
    }
</style>
