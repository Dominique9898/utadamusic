import axios from 'axios'

export const mixin = {
  methods: {

    //search
    getResultBySongName(){
      axios.get(this.$store.state.Host+'/listSongsOfSearch',{params: {
          name:this.$route.query.keyword
        }}).then((res)=>{
          this.listbysong= res.data
        }
      )
    },
    getResultBySinger(){
      axios.get(this.$store.state.Host+'/listSongsOfSearch',{params: {
          name:this.$route.query.keyword
        }}).then((res)=>{
          this.listbysinger= res.data
        }
      )
    },

    //获取歌手
    getSingerList(){
      this.$axios.get(this.$store.state.Host+'/listSingers').then((res) => {
        this.singerList = res.data
        this.singers = this.singerList[this.$store.state.Index]
        this.$store.commit('setSingersList', res.data)
        this.$store.commit('SingerId', this.singers.id)
        window.sessionStorage.setItem('setSingersList', JSON.stringify(res.data))
        window.sessionStorage.setItem('SingerId', JSON.stringify(this.singers.id))

      });
    },
    //获取歌单
     getSong_List_List(){
      this.$axios.get(this.$store.state.Host+'/listSongLists').then((res) => {
        this.songsList = res.data
        this.list_song = this.songsList[this.$store.state.Index]
        this.$store.commit('setSongsList', res.data)
        window.sessionStorage.setItem('songsList', JSON.stringify(res.data))
      });
    },
    /**
     * @return {string}
     */
    // 获取图片信息
    Address(srcUrl) {
      return this.$store.state.Host + srcUrl || '../assets/img/user.jpg'
    },
   /**
    * @return {string}
    */

    // 得到名字后部分
    replaceFName (str) {
      let arr = str.split('-')
      return arr[1]
    },
    // 得到名字前部分
    replaceLName (str) {
      let arr = str.split('-')
      return arr[0]
    },

    // 解析歌词
    parseLyric (text) {
      // console.log(typeof text)
      var lyric = [];
       var lines = text.split('\n');

      for(var i=0;i<lines.length;i++){
        var t = lines[i].substring(lines[i].indexOf("[") + 1, lines[i].indexOf("]"));
        lyric.push({

          time: (t.split(":")[0] * 60 + parseFloat(t.split(":")[1])).toFixed(3),
          lyric: lines[i].substring(lines[i].indexOf("]") + 1, lines[i].length)
        });
      }
      console.log(lyric)
      //保存歌词
      this.$store.commit('setLyric',lyric);
      window.sessionStorage.setItem('lyric',JSON.stringify(lyric));
      return lyric
    },
    // 搜索音乐
    getSong () {
      if (!this.$route.query.keywords) {
        this.$store.commit('setListOfSongs', [])
        this.$notify({
          title: '您输入内容为空',
          type: 'warning'
        })
      } else {
        let _this = this
        axios.get(_this.$store.state.Host + '/listSongsOfSearch?name=' + _this.$route.query.keywords)
          .then(function (response) {
            // console.log(response.data)
            if (!response.data.length) {
              _this.$store.commit('setListOfSongs', [])
              _this.$notify({
                title: '系统暂无该歌曲',
                type: 'warning'
              })
            } else {
              _this.$store.commit('setListOfSongs', response.data)
              // window.sessionStorage.setItem('listOfSongs', JSON.stringify(response.data))
            }
          })
          .catch(function (error) {
            console.log(error)
          })
      }
    }
  }
}
