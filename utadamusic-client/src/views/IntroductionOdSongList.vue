<template>
    <el-main>
        <div class="mod_data">
            <div class="data_cover">
                <img :src="Address(list_song.pic)" :alt="list_song.title" class="data__photo js_index">
            </div>
            <div class="data_cont">
                <div class="data__name">
                    <h1 class="data__name_txt js_index"> {{list_song.title}}</h1>
                </div>
                <div class="data__desc" id="singer_desc">
                   <h2>简介:</h2>
                    <p  class="data__desc_txt" id="short-desc">{{list_song.introduction}}</p>
                    　                </div>
            </div>
        </div>
        <div class="songbox">
            <div class="part_hd">
                <h2 class="part_tit">歌单</h2>
            </div>
            <el-table @row-click="goPlay"
                        :data="tableData"
                      stripe
                      style="width: 100%">
                <el-table-column
                        prop="name"
                        label="歌曲"
                        width="500">
                </el-table-column>
                <el-table-column
                        label="歌手"
                        prop="singerName"
                        width="500">
                </el-table-column>
                <el-table-column
                        prop="introduction"
                        label="专辑">
                </el-table-column>
                　
            </el-table>
        </div>
    </el-main>
</template>

<script>
import {mixin} from "../mixins";
import {mapGetters} from 'vuex'
import axios from 'axios'

export default {
  name: "IntroductionOdSongList",
  mixins:[mixin],
  computed:{
    ...mapGetters(
      [
        'SingerId',
      ]
    )
  },
  data(){
    return{
      tableData: [],
      listSongOfSinger: [],
      list_song:{},
      }
  },
  created(){

  },
  mounted:function () {
    this.getSong_List_List();
     this.getSongId (this.SingerId);

  },
  methods:{
    getSongId (par) {
        console.log(par)
      this.$axios.get(this.$store.state.Host+'/listSongOfSingers',{params: {
          songListId: par
        }}).then((res) => {
        this.listSongOfSinger = res.data
        console.log(res.data)

        // 获取歌单里的歌曲信息
          for (let item of this.listSongOfSinger) {
           this.getSongList(item.songId)

          }
       })
        .catch(function (error) {
          console.log(error)
        });

    },
    getSongList (id) {

      axios.get(this.$store.state.Host + '/listSongsOfSongs?id=' + id).then((res) => {
            this.tableData.push(res.data[0])
         })
        .catch(function (error) {
          console.log(error)
        })
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
      console.log(this.$store.state.song.songId);
      this.$store.commit('setSongid',row.id);
      window.sessionStorage.setItem('Songid',JSON.stringify(row.id));
      this.$router.push({path: '/play/'+row.id});
    }
  }
}
</script>

<style scoped>
    .el-main{
        min-height:1000px;
    }
    .data__photo {
        border-radius: 999px;
        position:absolute;
        width:100%;
        margin:auto;
    }
    .data__name_txt {
        float: left;
        font-size: 40px;
        font-weight: 400;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        max-width: 90%;
        margin-right: 10px;
    }
    .data__name {
        overflow: hidden;
        line-height: 50px;
        height: 70px;
    }
    .mod_data {
        left: 150px;
        position: relative;
        height: 300px;
        padding-left: 305px;
        margin-top: 40px;
        margin-bottom: 35px;
    }
    img {
        border: 0 none;
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
        padding-top: 5px;
        margin-left: 50px;
    }
    .data__desc{
        font-size: 14px;
        height: 150px;
        line-height: 20px;
        width: 900px;
    }
    .data__desc_txt{
        margin-left: 18px;
    }
    h1{
        margin-top: 10px;
        margin-bottom: 10px;
    }
    .part_hd{
        overflow: hidden;
        height: 80px;
    }
    .part_tit{
        float: left;
        font-size: 24px;
        font-weight: 400;
        line-height: 58px;
        font-family: \5FAE\8F6F\96C5\9ED1;
    }
    .songbox{
        left: 150px;
        height: 300px;
        padding-left: 150px;
        margin-top: 40px;
        padding-right: 10px;
    }
    .el-table .cell .el-table th div{
        padding-left: 40px;
    }
    .el-table {
        position: relative;
        overflow: hidden;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        -webkit-box-flex: 1;
        -ms-flex: 1;
        flex: 1;
        width: 100%;
        max-width: 95%;
        font-size: 14px;
        color: #606266;
    }
    .info {
        color: black;
        font-size: 15px ;
        margin-left: -10px;
    }
    .info li {
        width: 100%;
        height: 20px;
        list-style-type:circle;

    }
</style>
