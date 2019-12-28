<template>
  <div class="content-list">

    <ul class="section-content" >
      <li class="content-item" v-for="(item,index) in proplist" :key="index">
        <div class="kuo">
            <img class="item-img" :src="Address(item.pic)"alt="" >
            <div class="mask" @click="goPage(item.id,index)">
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-bofang"></use>
            </svg>
          </div>
        </div>
        <p class="item-name">{{item.title}}</p>
      </li>
    </ul>
  </div>
</template>

<script>

    import {mixin} from "../mixins";

    export default {
        name: "ContentSongList",
        props:['proplist'],
        mixins:[mixin],
        methods:{
        goPage(id,index){
          this.$router.push({path: '/songlist/'+id});
          this.$store.commit('setIndex',index);
          window.sessionStorage.setItem('setIndex', JSON.stringify(index));
          this.$store.commit('setSingerId',id)
          window.sessionStorage.setItem('setSingerId', JSON.stringify(id));
          console.log(this.SingerId)
        }
      }

    }
</script>

<style scoped>
  *{
    box-sizing: border-box;
  }
  .content-list {
    padding: 20px 10px 10px 40px;
   }
  .section-content {
    display: flex;
    flex-wrap: wrap;
    align-content: flex-start;
  }
  .content-item {
    flex-flow: column wrap;
    width: 15%;
    margin-right: 3%;
    margin-bottom: 3%;
    overflow: hidden;
    border-radius: 6px;
    -webkit-box-shadow: 0 0 5px 1px rgba(0, 0, 0, 0.1);
    -moz-box-shadow: 0 0 5px 1px rgba(0, 0, 0, 0.1);
    box-shadow: 0 0 5px 1px rgba(0, 0, 0, 0.1);
    position: relative;
  }
  li:hover{
    -webkit-box-shadow: 0 0 10px 4px rgba(0, 0, 0, 0.4);
    -moz-box-shadow: 0 0 10px 4px rgba(0, 0, 0, 0.4);
    box-shadow: 0 0 10px 4px rgba(0, 0, 0, 0.4);
  }
  li:hover .item-img{
    transform: scale(1.1);
  }
  .item-img {
    width: 100%;
    transition: all 0.4s ease;
  }
  .kuo, .mask {
    width: 100%;
    padding-bottom: 100%;
    height: 0;
    overflow: hidden;
  }
  .mask{
    position: absolute;
    top:0;
    background-color: rgba(52,47,41,.4);
    transition:all .3s ease-in-out;
    opacity: 0;
    display: flex;
    justify-content: center;
  }
  .mask > .icon {
    position: absolute;
    top: 40%
  }
  .mask:hover{
    opacity: 1;
    cursor: pointer;
  }
  p{
    text-align: center;
  }
  ul,li{
    list-style: none;
    display: inline-block;
  }

</style>
