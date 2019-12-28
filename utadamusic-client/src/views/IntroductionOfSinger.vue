<template>
        <el-main>
            <div class="mod_data">
                <div class="data_cover">
                    <img :src="Address(singers.pic)" :alt="singers.name" class="data__photo js_index">
                </div>
                <div class="data_cont">
                    <div class="data__name">
                        <h1 class="data__name_txt js_index"> {{singers.name}}</h1>
                    </div>
                    <div class="data__desc" id="singer_desc">
                        <ul class="info">
                            <li>性别：{{attachSex(singers.sex)}}</li>
                            <li>生日：{{singers.birth}}</li>
                            <li>地址：{{singers.location}}</li>
                        </ul>
                        <p  class="data__desc_txt" id="short-desc">{{singers.introduction}}</p>
                        　                </div>
                </div>
            </div>
            <div class="songbox">
                <div class="part_hd">
                    <h2 class="part_tit">热门歌曲</h2>
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
                            prop="SingerName"
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
import { mapGetters } from 'vuex'
import axios from 'axios'
import qs from 'qs'

export default {
  name: "IntroductionOfSinger",
  mixins:[mixin],

  mounted: function () {
    this.getSingerList(),
      this.getSongList ()
  },
  data(){
    return{
      singers: {},
      textarea: '',
      list:[],
      tableData: []
     }
  },
  computed:{
    ...mapGetters(
      [
        'SingerId',
        ]
    )
  },
  methods:{
    attachSex(value){
      if (value === 0) {
        return '女'
      } else if (value === 1) {
        return '男'
      }
    },
    getSongList () {
      let aa =  this.SingerId
      console.log(this.SingerId)
      this.$axios.get(this.$store.state.Host+'/listSongs',{params: {
          singerId: aa
        }}).then((res) => {

          var JsonObj = JSON.parse(JSON.stringify(res.data))
          for(var i=0;i<JsonObj.length;i++)
          {
            JsonObj[i].SingerName = this.singers.name
          }
        this.tableData = JsonObj
      })
        .catch(function (error) {
          console.log(error)
        })
    },
    goPlay(row){
      //保存歌手名
      this.$store.commit('setArtist',this.singers.name);
      window.sessionStorage.setItem('artist',JSON.stringify(this.singers.name));
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
      //歌曲id
      this.$store.commit('setSongid',row.id);
      window.sessionStorage.setItem('Songid',JSON.stringify(row.id));
      //console.log(this.$store.state.song.url);

     this.$router.push({path: '/play/'+row.id});
    }
  }
}
</script>

<style scoped>

</style>


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
        position: relative;
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


