<template>
  <el-container>
    <el-aside width="20%" ></el-aside>
    <el-main>
      <div class="box">
        <split class="split" tit="歌单"></split>
        <fengexian class="fengexian"/>
        <el-tabs style="margin-left: 3%" v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="全部"   name="first"><content-song-list style="margin-left: -7%;" :proplist="this.List"></content-song-list></el-tab-pane>
            <el-tab-pane label="华语"  name="second"><content-song-list style="margin-left: -7%;" :proplist="this.List"></content-song-list></el-tab-pane>
            <el-tab-pane label="日韩" name="third"><content-song-list style="margin-left: -7%;" :proplist="this.List"></content-song-list></el-tab-pane>
            <el-tab-pane label="欧美" name="fourth"><content-song-list style="margin-left: -7%;" :proplist="this.List"></content-song-list></el-tab-pane>
        </el-tabs>
          <el-pagination
                  background
                  @current-change="handleCurrentChange"
                  layout="prev, pager, next"
                  :total=this.pagesizes>
          </el-pagination>
      </div>
    </el-main>
    <el-aside width="20%" ></el-aside>
  </el-container>
</template>

<script>
    import Split from "../components/split";
    import Fengexian from "../components/fengexian";
    import Loginbox from "../components/loginbox";
    import ContentSongList from "../components/ContentSongList";
    import {mixin} from "../mixins";
    export default {
        name: "SongListPage",
        components: {ContentSongList, Loginbox, Fengexian, Split},
        mixins:[mixin],
         mounted(){
         this.getSong_List_List();
           this.showAll();
        },
      data(){
        return{
          activeName: 'first',
          songsList: [],
          currentPage:1,
          pagesize:10,
          pagesizes:100,
          List:[]
        }
      },
      methods:{
        handleClick(tab, event) {
          var key = event.target.getAttribute('id');
          if (key == 'tab-first')
          {
            this.showAll()
          }
          if(key == 'tab-second')
          {
            var data = this.$store.state.songsList.filter(function (item) {
              return item.style == '华语'
            })
            this.List = data;
          }
          if(key == 'tab-third')
          {
            var data = this.$store.state.songsList.filter(function (item) {
              return item.style == '日韩'
            })
            this.List = data;
          }
          if(key == 'tab-fourth')
          {
            var data = this.$store.state.songsList.filter(function (item) {
              return item.style == '欧美'
            })
            this.List = data;
          }
          var tmp = this.$store.state.songsList.length/10;
          if(tmp<=0)
          {
            this.pagesizes = (tmp+1)*10
          }
          else {
            this.pagesizes = (tmp)*10;
          }
        },
        handleCurrentChange: function (currentPage) {
          this.currentPage = currentPage;
          this.showAll()
        },
        showAll()
        {
          var tmp = this.$store.state.songsList.length/10;
          if(tmp<=0)
          {
            this.pagesizes = (tmp+1)*10
          }
          else {
            this.pagesizes = (tmp)*10;
          }
          this.List = this.$store.state.songsList.slice((this.currentPage-1)*this.pagesize,this.currentPage*this.pagesize)
        },
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
    min-height: 777px;
  }

  .split{
    margin-left: 3%;
  }
  .fengexian{
    width: 90%;
    margin-left: 2.8%;
  }

</style>
