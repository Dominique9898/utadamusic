<head>
  <meta name="referrer" content="never">
  <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<template>
  <div class="footer navbar-fixed-bottom ">
    <p v-for="(item, index) in list" :key="index">
      {{item}}
    </p>
  </div>
</template>

<script>
    export default {
        name: "Foot",
        data () {
            return {
                list: [
                    `关于 | 帮助 | 条款 | 反馈`,
                    `Copyright © 2019`,
                    `Designed by Dominique-Wei`
                ],
                minHeight:0
            }
        }
    }

</script>

<style scoped>

  .footer{
    width: 100%;
    height: 90px;
    display: flex;
      position:fixed;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    background:#333;
    color:#fff;
    bottom: 0;
   }
  .footer p {
    height: 30px;
  }
</style>
