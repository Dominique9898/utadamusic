<template>
    <div class="section-head">
      <h2 id="tit"><span class="glyphicon glyphicon-cd" aria-hidden="true"></span>{{tit}}</h2>
    <content-list></content-list>
  </div>
</template>

<script>
    export default {
        name: "split",
        props:['tit']
    }
</script>

<style scoped>
 h2{
   text-align: left;
 }
  .section-head{
    margin-left: 2%;
  }

</style>
