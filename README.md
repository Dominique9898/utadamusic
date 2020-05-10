# utadamusic
基于vue-cli+springboot+mybaits的音乐网站<br/>

在server的controller下,更新LoginController,SingerController,SongController,SongListController下找到以下代码,更改本地路径
```java
@Configuration
    public class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/avatorImages/**").addResourceLocations("file:/Users/dominiquewei/Documents/Coding/Java-Web/vue/utadamusic/utadamusic-server/data/img/avatorImages/");
        }
    }
```
