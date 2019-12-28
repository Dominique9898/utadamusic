<head>
    <link type="text/css" rel="stylesheet" href="/music-client/static/layui/layui.js"/>
    <script type="text/javascript" src="/music-client/static/layui/layui.js"></script>
</head>
<template>
  <el-container>
    <el-aside width="200px" >
      <div v-for="item in singerLabelList" class="blk" >
        <h4 style="margin-top: 20px">{{item.tit}}</h4>
        <ul class="sa" style="margin-top: 20px">
          <li @click="changeTit(item.men)"><a href="#">{{item.men}}</a></li>
          <li @click="changeTit(item.woman)"><a href="#">{{item.woman}}</a></li>
         </ul>
        <el-divider></el-divider>
      </div>
    </el-aside>
    <el-main>
      <split :tit="tit"/>
      <fengexian class="fengexian"/>
      <div class="pagination">
          <content-list :proplist="this.List"></content-list>
          <el-pagination
                  background
                  @current-change="handleCurrentChange"
                  layout="prev, pager, next"
                  :total=this.pagesizes>
          </el-pagination>
       </div>

    </el-main>
  </el-container>
</template>

<script>
    import Fenye from "../components/fenye";
    import Split from "../components/split";
    import Fengexian from "../components/fengexian";
    import {mixin} from "../mixins";
    import ContentList from "../components/ContentList";

    export default {
        name: "SingerPage",
        components: {ContentList, Fengexian, Split, Fenye},
        mixins:[mixin],
      mounted(){
        this.getSingerList()
        this.showAll();
       },
        data() {
            return {
                tit:"全部歌手",
                  currentPage:1,
                  pagesize:10,
                  singerList: [],
                  List:[],
                  pagesizes:100,
                singerLabelList:[
                    {
                        tit:"华语",
                        men:"华语男歌手",
                        woman:"华语女歌手",
                     },{
                        tit:"欧美",
                        men:"欧美男歌手",
                        woman:"欧美女歌手",
                     },{
                        tit:"日韩",
                        men:"日韩男歌手",
                        woman:"日韩女歌手",
                     }
                ],
            }
        },
      methods: {
        changeTit(str) {
          this.tit = str
          this.showPart(str)
        },
        handleCurrentChange: function (currentPage) {
          this.currentPage = currentPage;
          this.showAll()
        },
        showAll()
        {
           var tmp = this.$store.state.singersList.length/this.pagesize;
          if(tmp<=0)
          {
            this.pagesizes = (tmp+1)*this.pagesize
          }
          else {
            this.pagesizes = (tmp)*this.pagesize;
          }
          this.List = this.$store.state.singersList.slice((this.currentPage-1)*this.pagesize,this.currentPage*this.pagesize)
        },
        showPart(str)
        {
          var key = this.check(str)
          var data = this.$store.state.singersList.filter(function (item) {
            return item.sex == key.sex && item.location == key.location
          })
          this.List = data;
          var tmp = this.List.length/this.pagesize;
          if(tmp<=0)
          {
            this.pagesizes = (tmp+1)*this.pagesize
          }
          else {
            this.pagesizes = (tmp)*this.pagesize;
          }
        },
        check(str){
          if (str == '华语男歌手')
          {
            return  {'sex':1,'location':'中国'}
          }
          if (str == '华语女歌手')
          {
            return  {'sex':0,'location':'中国'}
          }
          if (str == '欧美男歌手')
          {
            return  {'sex':1,'location':'欧美'}
          }
          if (str == '欧美女歌手')
          {
            return  {'sex':0,'location':'欧美'}
          }
          if (str == '日韩女歌手')
          {
            return  {'sex':0,'location':'日韩'}
          }
          else
          {
            return  {'sex':1,'location':'日韩'}
          }
        }
      }
    }
</script>

<style scoped>
  .el-aside {
    background-color: #d3d3d3;
    text-align: center;
    margin-top: -1.788%;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }
   li a:hover {
    background-position: 0 0;
    text-decoration: underline;
    color: #c20c0c;
  }
   li a {
    color: #333;
  }
  li{
    list-style-type:square;
    padding-bottom: 10px;
  }
  .sa{
    text-align:left;
  }
  .blk {
    margin: 23px 0 0;
    padding-top: 16px;
    border-top: 1px solid #d3d3d3;
    text-align: left;
  }
  h4{
    margin-left: 20px;
  }
  .fengexian{
    margin-left: 2%;
    width: 80%;
  }

</style>
