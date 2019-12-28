<template>
  <div id="app">
    <Header/>
    <router-view class="music-content"/>
     <Foot class="foot"/>
  </div>
</template>

<script>
  import Header from "./components/Header";
  import Foot from "./components/Foot";
export default {
  name: 'App',
    components:{
      Header,
        Foot,
    },
  mounted() {
    window.addEventListener('unload', this.saveState)
  },
  methods: {
    saveState() {
      sessionStorage.setItem('state', JSON.stringify(this.$store.state))
    }
  },
  created () {
    //在页面加载时读取sessionStorage里的状态信息
    if (sessionStorage.getItem("store") ) {
      this.$store.replaceState(Object.assign({}, this.$store.state,JSON.parse(sessionStorage.getItem("store"))))
    }

    //在页面刷新时将vuex里的信息保存到sessionStorage里
    window.addEventListener("beforeunload",()=>{
      sessionStorage.setItem("store",JSON.stringify(this.$store.state))
    })
  }
}
</script>

<style>
  #app{
    background-color: #e6ecf0;
    display: flex;
    flex-direction: column;
  }
  .music-content{
    flex: 1 0 auto;
  }
  .footer{
      position:relative;
   }
  .footer p {
      height: 30px;
  }

</style>
