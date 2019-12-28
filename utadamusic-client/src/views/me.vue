<template>
    <div class="my">
        <div class="mod_profile" >
            <div class="section_inner">
                <div class="profile__cover_link">
                    <img :src= Address(avator)  class="profile__cover" id="profileHead">
                </div>

                <h1 class="profile__tit">
                    <span class="profile__name">{{username}}</span>
                </h1>
            </div>
        </div>

        <div class="main main--profile">
            <div class="mod_tab profile_nav" id="nav">
                <a class="mod_tab__item " href="javascript:;" >全部</a>
            </div>
            <div class="js_box">
                <el-tabs   v-model="activeName"  >
                    <el-tab-pane  label="歌曲" name="first">
                        <el-table   @row-click="goPlay"
                                    :data="this.listbysong"
                                    stripe
                                    style="width: 100%">
                            <el-table-column
                                    prop="name"
                                    label="歌名"
                                    width="180">
                            </el-table-column>
                            <el-table-column
                                    prop="singerName"
                                    label="歌手"
                                    width="180">
                            </el-table-column>
                            <el-table-column
                                    prop="introduction"
                                    label="专辑">
                            </el-table-column>
                        </el-table>
                    </el-tab-pane>
                    <el-tab-pane  label="歌手" name="second"><search-song :prop="listbysinger"></search-song> </el-tab-pane>
                </el-tabs>

            </div>
        </div>
    </div>

</template>

<script>
import SearchSong from "../components/SearchSong";
import {mixin} from "../mixins";
import { mapGetters } from 'vuex'
import axios from 'axios'
export default {
  mixins:[mixin],
  computed: {
    ...mapGetters([
      'avator',
      'username',
      'userId',
    ])
  },
  name: "me",
  components: {SearchSong},
  mounted(){
    this.getSongs()
  },
  methods:{
    getSongs(){
      axios.get(this.$store.state.Host+'/myCollection',{
        params:{
          userId:this.userId
        }
      }).then(res => {
        console.log(res.data)
        this.listbysong = res.data
      }).catch(failResponse => {})
    },
    goPlay(row){
      //保存歌手名
      this.$store.commit('setArtist',row.singerName);
      window.sessionStorage.setItem('artist',JSON.stringify(row.singerName));
      //保存歌词
      this.$store.commit('setLyric',row.lyric);
      window.sessionStorage.setItem('lyric',JSON.stringify(row.lyric));
      //保存歌名
      this.$store.commit('setTitle',row.name);
      window.sessionStorage.setItem('title',JSON.stringify(row.name));
      //保存专辑
      this.$store.commit('setIntroduction',row.introduction);
      window.sessionStorage.setItem('introduction',JSON.stringify(row.introduction));
      //歌曲图片
      this.$store.commit('setPicUrl',row.pic);
      window.sessionStorage.setItem('picUrl',JSON.stringify(row.pic));
      //歌曲链接
      this.$store.commit('setUrl',row.url);
      window.sessionStorage.setItem('url',JSON.stringify(row.url));
      //歌曲发行时间
      this.$store.commit('setCreateTime',row.createTime);
      window.sessionStorage.setItem('createTime',JSON.stringify(row.createTime));
      //console.log(this.$store.state.song.url);
      this.$router.push({path: '/play/'+row.songId});
    }
  },
  data(){
    return{
      activeName:"first",
      avatar:'',
      listbysong:[],

    }
  }
}
</script>

<style scoped>

    .section_inner {
        max-width: 1200px;
        margin: 0 auto;
        position: relative;
    }
    /*头像*/
    .profile__cover_link {
        position: relative;
        width: 102px;
        height: 102px;
        margin: 0 auto;
    }
    .profile__cover {
        display: block;
        width: 100%;
        height: 100%;
        border: 4px solid #fff;
        border-radius: 99px;
        background: #fff;
    }
    .profile__name {
        font-size: 30px;
        font-weight: 400;
        line-height: 64px;
        margin-right: 4px;
    }
    .mod_profile {
        height: 315px;
        padding-top: 65px;
        text-align: center;
        color: #fff;
        background:url('../assets/img/Landscapes.jpg');
    }
    /*选择框*/
    .main {
        min-height: 445px;
    }
    .main {
        z-index: 2;
    }

    .profile_nav {
        position: absolute;
        top: -56px;
        left: 0;
        margin-bottom: 20px;
        width: 80%;
    }

    .mod_tab {
        position: relative;
        zoom: 1;

    }


    .profile_nav .mod_tab__current, .profile_nav .mod_tab__item:hover {
        color: #31c27c;
    }
    .profile_nav .mod_tab__item {
        color: #fff;
        margin-right: 40px;
    }

    .js_box>.mod_tab__item{
        color: black;
    }

    .js_box{
        position: relative;
        bottom:120px;
        width: 80%;
    }

    .mod_tab, .mod_tab__item {
        height: 94px;
    }

    a{
        color: #000;
        text-decoration: none;
        cursor: pointer;
    }
    a:hover {
        color: #31c27c;
        text-decoration: none;
    }

    .main--profile{
        margin-left: 15%;
        margin-top: 15px;
    }
    /*歌曲*/

</style>
