// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'


import $ from 'jquery'
import'bootstrap/js/bootstrap.js'
import'bootstrap/css/bootstrap.css'

import ElementUI from 'element-ui';             //引入element-ui组件
import 'element-ui/lib/theme-chalk/index.css';
import Axios from "axios"
import store from './store/index'
import qs from 'qs';

Vue.prototype.$qs = qs;
 Vue.prototype.$axios = Axios
Vue.use(ElementUI)

Vue.config.productionTip = false
window.onresize = setHtmlFontSize;
function setHtmlFontSize(){
  const htmlWidth = document.documentElement.clientWidth || document.body.clientWidth;
  const htmlDom = document.getElementsByTagName('html')[0];
  htmlDom.style.fontSize = htmlWidth / 10 + 'px';
};
setHtmlFontSize();
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
