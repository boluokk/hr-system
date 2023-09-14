import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components//views/Home.vue'
import Chat from '../components/views/Chat.vue'

Vue.use(VueRouter)

const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', component: Login },
  {
    path: '/home',
    component: Home,
    children: [{ path: '/chat', component: Chat }]
  }
]

const router = new VueRouter({
  routes
})

export default router
