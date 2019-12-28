<template>
    <div class="searchbox">
        <el-table   @row-click="goPlay"
                :data="prop"
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
    </div>
</template>

<script>
export default {
  name: "SearchSong",
  props:['prop'],
  data() {
    return {
    }
  },
  methods:{
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
      this.$router.push({path: '/play/'+row.id});
    }
  }
}
</script>

<style scoped>

</style>
