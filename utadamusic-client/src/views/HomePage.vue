<template>
  <div class="home-page">
    <swiper/>
    <div class="split-w" style="padding: 10px;background-color: white">
      <div class="row" >
        <div class="col-md-1"></div>
        <div class="col-md-8" >
          <div class="section singer">
            <div class="section-head">
              <h4><span class="glyphicon glyphicon-cd" aria-hidden="true"></span>歌手</h4>
            </div>
            <div class="split-b" style="padding: 1px;background-color: #2e6da4"></div>
                <content-list style="margin-left:18.666%" :proplist="singerList.slice(0,10)"></content-list>
          </div>
        </div>
        <div class="col-md-2"></div>
      </div>

      <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-8">
          <div class="section singer">
            <div class="section-head">
              <h4><span class="glyphicon glyphicon-cd" aria-hidden="true"></span>歌单</h4>
            </div>
            <div class="split-b" style="padding: 1px;background-color: #2e6da4"></div>
              <content-song-list style="margin-left:18.666%;" :proplist="songsList.slice(0,10)"/>
           </div>
        </div>
        <div class="col-md-2"></div>
      </div>
    </div>

  </div>

</template>

<script>

    import Swiper from "../components/swiper";
    import ContentList from "../components/ContentList";
    import ContentSongList from "../components/ContentSongList";
     import {mixin} from "../mixins";
    import {mapGetters} from "vuex";


    export default {
      name: "HomePage",
      components: {ContentSongList, ContentList, Swiper},
      mixins:[mixin],
      mounted(){
        this.getSingerList()
        this.getSong_List_List()
      },
      computed: {
        ...mapGetters([
          'songsList',
          'singersList'
        ])
      },
      data() {
        return {
          songsList: [],
          singerList: []
        }
      },
    }
</script>

<style scoped>
 .home-page{
     min-height: 1100px;
 }
  .section{
    background-color:white;
  }
 .section-head
 {
   margin-left: 25%;
 }
 .split-b{
   margin-left: 25%;
   width: 65%;
 }

</style>
