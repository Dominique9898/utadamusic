<head>
  <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
  <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<template>
  <div class="row" style="margin-top: 3%;">
    <div class="col-md-12">
      <div class="carousel slide" id="carousel-example-generic" data-ride="carousel" data-interval="1500">
        <ol class="carousel-indicators">
          <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
          <li data-target="#carousel-example-generic" data-slide-to="1"></li>
          <li data-target="#carousel-example-generic" data-slide-to="2"></li>
          <li data-target="#carousel-example-generic" data-slide-to="3"></li>
        </ol>
        <div class="carousel-inner">
          <div class="item active">
            <img src="http://p1.music.126.net/UK33yFeDmW_GrBsS9zRSMQ==/109951164491296812.jpg?imageView&quality=89">
          </div>
          <div class="item">
            <img src="http://p1.music.126.net/E9AJc6bMs9KhB3JaXJeEJA==/109951164492619315.jpg?imageView&quality=89">
          </div>
          <div class="item">
            <img src="http://p1.music.126.net/waEQvH5J2xj-Aj5qX0O0Yg==/109951164475372105.jpg?imageView&quality=89">
          </div>
          <div class="item">
            <img src="http://p1.music.126.net/NMOJlk9MtOq5AvDUrYOBsQ==/109951164475927644.jpg?imageView&quality=89">
          </div>
        </div>
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev" style="margin-left: 12%">
          <span class="glyphicon glyphicon-chevron-left"></span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next" style="margin-right:12%">
          <span class="glyphicon glyphicon-chevron-right"></span>
        </a>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        name: "swiper"
    }
</script>

<style scoped>
  .carousel-inner img {
    width: 50%;
    margin-left: 25%;
  }
</style>
