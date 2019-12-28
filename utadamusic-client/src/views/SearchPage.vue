<template>
    <el-container>
        <el-aside width="20%" ></el-aside>
        <el-main>
            <el-tabs style="margin-left: 3%" v-model="activeName"  >
                <el-tab-pane label="歌曲" name="first"><search-song :prop="listbysong"></search-song></el-tab-pane>
                <el-tab-pane label="歌手" name="second"><search-song :prop="listbysinger"></search-song> </el-tab-pane>
            </el-tabs>
        </el-main>
        <el-aside width="20%" ></el-aside>
    </el-container>
</template>

<script>
import SearchSong from "../components/SearchSong";
import axios from "axios"
export default {
  name: "SearchPage",
  components: {SearchSong},
  data(){
    return{
      key:'',
      activeName:'first',
      listbysong:[],
      listbysinger:[],
     }
  },
  watch:{
    $route(){
      this.getResultBySongName()
      this.getResultBySinger()
    },
  },
  mounted() {
    this.getResultBySongName()
    this.getResultBySinger()
  },
  methods:{
        getResultBySongName(){
            axios.get(this.$store.state.Host+'/listSongsOfSearch',{params: {
            name:this.$route.query.keyword
                 }}).then((res)=>{
            this.listbysong= res.data
           }
        )
    },
    getResultBySinger(){
      axios.get(this.$store.state.Host+'/SongsOfSearch',{params: {
          name:this.$route.query.keyword
        }}).then((res)=>{
          this.listbysinger= res.data
        }
      )
    },

  },
}
</script>

<style scoped>
    .el-main{
        min-height: 800px;
    }
</style>
