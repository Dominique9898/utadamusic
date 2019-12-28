import Vue from 'vue'
import Router from 'vue-router'
import HomePage from "../views/HomePage";
import SingerPage from "../views/SingerPage";
import me from "../views/me";

import SongListPage from "../views/SongListPage";
import LoginPage from "../views/LoginPage";
import SearchPage from "../views/SearchPage";
import IntroductionOfSinger from "../views/IntroductionOfSinger";
import PlayPage from "../views/PlayPage";
import Player from "../views/Player";
import IntroductionOdSongList from "../views/IntroductionOdSongList";
// path: 程序启动时候的路径,'/'为默认路径
//name 跳转的路由
//component 组件

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home-page',
      component: HomePage
    },
    {
      path: '/singer-page',
      name: 'singer-page',
      component:SingerPage
    },
    {
      path:'/song-list-page',
      name:'song-list-page',
      component:SongListPage
    },
    {
      path:'/me',
      name:'/me',
      component:me
    },
    {
      path:'/login',
      name:'login',
      component:LoginPage
    },
    {
      path:'/search',
      query:{
        keyword:'',
      },
      name:'search',
      component:SearchPage
    },
    {
      path:'/singer/:id',
      name:'singer',
      component:IntroductionOfSinger
    },
    {
      path:'/songlist/:id',
      name:'songlist',
      component:IntroductionOdSongList
    },
    {
      path:'/play/:id',
      name:'play',
      component:PlayPage
    },
    {
      path:'/player',
      name:'player',
      component:Player
    }

  ]
})

