import Vue from 'vue'
import Vuex from 'vuex'
import { getRequest } from '@/assets/js/AjaxUtil'
import '../assets/js/stomp'
import SockJS from '../assets/js/sockjs'

import { Notification } from 'element-ui'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    user: {
      name:
        window.sessionStorage.getItem('user') === null
          ? '未登入'
          : JSON.parse(window.sessionStorage.getItem('user')).name,
      userFace:
        window.sessionStorage.getItem('user') === null
          ? ''
          : JSON.parse(window.sessionStorage.getItem('user')).userFace,
      username:
        window.sessionStorage.getItem('user') === null
          ? ''
          : JSON.parse(window.sessionStorage.getItem('user')).username
    },
    routes: [],
    msgCount: 0,
    // 聊天室数据↓
    sessions: {},
    // CurrentHr:
    hrs: [],
    currentSession: {},
    filterKey: '',
    stomp: null,
    messageCount: null,
    iSCurrentChat: false,
    newMsgHr: ''
  },
  mutations: {
    login(state, user) {
      state.user = user
      window.sessionStorage.setItem('user', JSON.stringify(user))
    },
    logout(state) {
      window.sessionStorage.removeItem('user')
    },
    initeMenu(state, routes) {
      state.routes = routes
    },
    addMsgCount(state) {
      state.msgCount++
    },
    initMsgCount(state) {
      state.msgCount = 0
    },
    changecurrentSession(state, currentSession) {
      state.currentSession = currentSession
    },
    addMessage(state, msg) {
      let mss = state.sessions[state.user.username + '#' + msg.to]
      if (!mss) {
        // state.sessions[state.user.username + '#' + msg.to] = []
        Vue.set(state.sessions, state.user.username + '#' + msg.to, [])
      }
      state.sessions[state.user.username + '#' + msg.to].push({
        content: msg,
        date: new Date(),
        self: !msg.notSelf
      })
    },
    INIT_DATA(state) {
      const data = localStorage.getItem('vue-chat-session')
      if (data) {
        state.sessions = JSON.parse(data)
      }
    },
    INIT_HRS(state, data) {
      state.hrs = data
    }
  },
  actions: {
    connect(context) {
      context.state.stomp = Stomp.over(new SockJS('/ws/ep'))
      context.state.stomp.connect(
        {},
        success => {
          context.state.stomp.subscribe('/user/queue/channel', msg => {
            let receiveMsg = JSON.parse(msg.body)
            if (
              context.state.currentSession.username !== receiveMsg.from ||
              !context.state.currentSession
            ) {
              Notification.info(
                receiveMsg.content.length <= 10
                  ? receiveMsg.nickName + '：' + receiveMsg.content
                  : receiveMsg.nickName +
                  '：' +
                  receiveMsg.content.substr(0, 10)
              )
              // 是否在chat页面 如果在则不开始计数没有读的消息个数 否则相反
              if (!context.state.iSCurrentChat) {
                if (context.state.messageCount === null) {
                  context.state.messageCount = 1
                } else {
                  context.state.messageCount++
                }
              }
            }
            receiveMsg.notSelf = true
            context.state.hrs.forEach(item => {
              if (
                item.username === receiveMsg.from &&
                context.state.currentSession.username !== receiveMsg.from
              ) {
                item.iSNewMsg = true
              }
            })
            receiveMsg.to = receiveMsg.from
            context.commit('addMessage', receiveMsg)
          })
        },
        error => {
          console.log('连接失败！')
        }
      )
    },
    initData(context) {
      context.commit('INIT_DATA')
      getRequest('/chat/hrs').then(resp => {
        if (resp) {
          context.commit('INIT_HRS', resp.data.obj)
        }
      })
    }
  },
  modules: {}
})

store.watch(
  function(state) {
    return state.sessions
  },
  function(val) {
    localStorage.setItem('vue-chat-session', JSON.stringify(val))
  },
  {
    deep: true
  }
)

export default store
