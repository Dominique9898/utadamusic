import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

const song ={
  state:{
    listOfSongs: [], // 当前歌单列表
    Songid: null, // 音乐ID
    url: '', // 歌曲URL
    title: '', //  歌名
    artist: '', //  歌手名
    picUrl: '', // 歌曲图片
    lyric: [], // 未处理的歌词数据
    introduction:'',
    listIndex: null, // 当前歌曲在歌曲列表的位置
    createTime:''//发行时间
  },
  getter:{
    listOfSongs: state => {
      let listOfSongs = state.listOfSongs
      if (!listOfSongs.length) {
        listOfSongs = JSON.parse(window.sessionStorage.getItem('listOfSongs') || null)
      }
      return listOfSongs
    },
    Songid: state => {
      let Songid = state.Songid
      if (!Songid.length) {
        Songid = JSON.parse(window.sessionStorage.getItem('Songid') || null)
      }
      return Songid
    },
    artist:state => {
      let artist = state.artist
      if(!artist){
        artist = JSON.parse(window.sessionStorage.getItem('artist') || null)
      }
      return artist
    },
    lyric:state => {
      let lyric = state.lyric
      if(!lyric)
      {
        lyric = JSON.parse(window.sessionStorage.getItem('lyric') || null)
      }
      return lyric;
    },
    title:state => {
      let title = state.title
      if(!title)
      {
        title = JSON.parse(window.sessionStorage.getItem('title') || null)
      }
      return title;
    },
    picUrl:state => {
      let picUrl = state.picUrl
      if(!picUrl)
      {
        picUrl = JSON.parse(window.sessionStorage.getItem('picUrl') || null)
      }
      return picUrl;
    },
    introduction:state => {
      let introduction = state.introduction
      if(!introduction)
      {
        introduction = JSON.parse(window.sessionStorage.getItem('introduction') || null)
      }
      return introduction;
    },
    url:state => {
      let url = state.url
      if(!url)
      {
        url = JSON.parse(window.sessionStorage.getItem('url') || null)
      }
      return url;
    },
    createTime:state => {
      let createTime = state.createTime
      if(!createTime)
      {
        createTime = JSON.parse(window.sessionStorage.getItem('createTime') || null)
      }
      return createTime;
    },

  },
  mutations:{
    setListOfSongs: (state, listOfSongs) => { state.listOfSongs = listOfSongs },
     setArtist: (state, artist) => { state.artist = artist },
    setLyric:(state,lyric) => {state.lyric = lyric},
    setTitle:(state,title) => {state.title = title},
    setIntroduction:(state,introduction) => {state.introduction = introduction},
    setPicUrl:(state,picUrl) => {state.picUrl = picUrl},
    setUrl:(state,url) => {state.url = url},
    setCreateTime:(state,createTime) => {state.createTime = createTime},
    setSongid:(state,Songid) => {state.Songid = Songid},
  },
}
const user = {
  state: {
    userId: '',
    username: '',
    avator: ''
  },
  getters: {
    userId: state => {
      let userId = state.userId
      if (!userId) {
        userId = JSON.parse(window.localStorage.getItem('userId') || null)
      }
      return userId
    },
    username: state => {
      let username = state.username
      if (!username) {
        username = JSON.parse(window.localStorage.getItem('username') || null)
      }
      return username
    },
    avator: state => {
      let avator = state.avator
      if (!avator) {
        avator = JSON.parse(window.localStorage.getItem('avator') || null)
      }
      return avator
    }
  },
  mutations: {
    setUserId: (state, userId) => { state.userId = userId },
    setUsername: (state, username) => { state.username = username },
    setAvator: (state, avator) => { state.avator = avator }
  }
};

const store = new Vuex.Store({
  modules: {
    user,
    song
  },
  state:sessionStorage.getItem('state') ? JSON.parse(sessionStorage.getItem('state')): {
    Host:"http://localhost:8080",
    loginIn: false, // 是否登录
    singersList: [], // 歌手列表
    songsList: [], // 歌单列表
    Index: 0, // 列表中的序号
    SingerId:0,  //歌手id
    keyWord:'',
    playList:[],
  },
  getters:{
    loginIn: state => {
      let loginIn = state.loginIn
      if (!loginIn) {
        loginIn = JSON.parse(window.sessionStorage.getItem('loginIn') || null)
      }
      return loginIn
    },
    keyWord: state => {
      let keyWord = state.keyWord
      if (!keyWord) {
        keyWord = JSON.parse(window.sessionStorage.getItem('keyWord') || null)
      }
      return keyWord
    },
    singersList: state => {
      let singersList = state.singersList
      if (!singersList.length) {
        singersList = JSON.parse(window.sessionStorage.getItem('singersList') || null)
      }
      return singersList
    },
    songsList: state => {
      let songsList = state.songsList
      if (!songsList.length) {
        songsList = JSON.parse(window.sessionStorage.getItem('songsList') || null)
      }
      return songsList
    },
    Index: state => {
      let Index = state.Index
      if (!Index) {
        Index = JSON.parse(window.sessionStorage.getItem('Index') || null)
      }
      return Index
    },
    SingerId:state => {
      let SingerId = state.SingerId
      if(!SingerId){
        SingerId = JSON.parse(window.sessionStorage.getItem('SingerId') || null)
      }
      return SingerId
    },
    playList:state => {
      let playList = state.playList
      if(!playList){
        playList = JSON.parse(window.sessionStorage.getItem('playList') || null)
      }
      return playList
    },


  },
  mutations:{
    setLoginIn: (state, loginIn) => { state.loginIn = loginIn },
    setIndex: (state, Index) => { state.Index = Index },
    setSingersList: (state, singersList) => { state.singersList = singersList },
    setSongsList: (state, songsList) => { state.songsList = songsList },
    setSingerId:(state , SingerId) => {state.SingerId = SingerId},
    setPlayList:(state , playList) => {state.playList = playList},
    setKeyWord:(state , keyWord) => {state.keyWord = keyWord},
   }

});
export default store
