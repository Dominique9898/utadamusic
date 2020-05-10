# utadamusic
基于vue-cli+springboot+mybaits的音乐网站
1.获得程序当前路径:
System.getProperty("user.dir”)

更新歌手,歌单,歌曲的图片
在各自的controller层中 
```java
String filePath = System.getProperty("user.dir") + System.getProperty("file.separator")+
"data"+System.getProperty("file.separator")+ "img" + System.getProperty("file.separator") + "singerPic” ;
```
记得加入”data"
