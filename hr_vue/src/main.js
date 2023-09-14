import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import { initMenu } from './assets/js/MenuUtil'
import './assets/font/iconfont.js'
// 全局css
import './assets/css/global.css'
// 导入element
import './plugins/element.js'
import 'element-ui/lib/theme-chalk/index.css'

// 导入login页面需要的css文件
import './assets/css/loginRequire01.css'
import './assets/css/font-awesome.min.css'

import {
  getRequest,
  postRequest,
  deleteRequest,
  putRequest
} from './assets/js/AjaxUtil.js'
import axios from 'axios'

Vue.config.productionTip = false

// 挂载请求工具
Vue.prototype.getRequest = getRequest
Vue.prototype.postRequest = postRequest
Vue.prototype.deleteRequest = deleteRequest
Vue.prototype.putRequest = putRequest

Vue.prototype.$http = axios

// 路由导航守卫
router.beforeEach((to, from, next) => {
  if (to.path === '/login') return next()
  if (!window.sessionStorage.getItem('user')) {
    next('/')
  } else {
    initMenu(router, store)
    store.commit('initMsgCount')
    // websocket在什么情况下可以连接或者重新连接
    if (
      (from.path === '/login' && to.path === '/home') ||
      (to.path === '/home' && from.path === '/') ||
      (to.path === '/chat' && from.path === '/') ||
      (from.path === '/' && to.path)
    ) {
      store.dispatch('connect')
      store.dispatch('initData')
    }
    if (to.path !== '/chat' && from.path === '/chat') {
      store.state.iSCurrentChat = false
      store.state.currentSession = {}
    }
    next()
  }
})

// 时间格式过滤
Vue.filter('dateFormat', function(originData) {
  const time = new Date(originData)
  // 年
  const y = (time.getFullYear() + '').padStart(2, '0')
  // 月
  const m = (time.getMonth() + 1 + '').padStart(2, '0')
  // 日
  const d = (time.getDate() + '').padStart(2, '0')
  // 时
  const hh = (time.getHours() + '').padStart(2, '0')
  // 分
  const mm = (time.getMinutes() + '').padStart(2, '0')
  // 秒
  const ss = (time.getSeconds() + '').padStart(2, '0')

  return `${y}-${m}-${d} ${hh}:${mm}:${ss}`
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
