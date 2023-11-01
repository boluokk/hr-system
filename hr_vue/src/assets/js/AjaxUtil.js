import axios from 'axios'
import { Message } from 'element-ui'
import Router from '../../router/index.js'
import store from '../../store/index'

axios.interceptors.request.use(
  config => {
    return config
  },
  err => {
    Message.error({ message: '请求超时!' })
    return Promise.resolve(err)
  }
)
axios.interceptors.response.use(
  data => {
    if (data.data.status && data.data.status !== 200) {
      Message.error({ message: data.data.msg })
      return
    }
    return data
  },
  err => {
    if (err.response.status === 504) {
      Message.error({ message: '服务器被吃了⊙﹏⊙∥' })
    } else if (err.response.status === 403) {
      if (store.state.msgCount === 0) {
        Message.error({ message: '权限不足,请联系管理员!' })
        store.commit('addMsgCount')
      }
    } else if (err.response.status === 402 || err.response.status === 401) {
      Message.error({ message: '请重新登录' })
      Router.replace('/')
    } else if (err.response.status === 404) {
      Message.error({ message: '未知请求路径' })
    } else {
      Message.error({ message: '未知错误!' })
    }
    return Promise.resolve(err)
  }
)

// 统一前缀
const base = ''

export const postRequest = (url, params) => {
  let ans = {}
  for (let it in params) {
    if (params[it] !== undefined || params[it] !== null) {
      ans[it] = params[it]
    }
  }
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: ans,
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    }
  })
}
export const putRequest = (url, params) => {
  let ans = {}
  for (let it in params) {
    if (params[it] !== undefined || params[it] !== null) {
      ans[it] = params[it]
    }
  }
  return axios({
    method: 'put',
    url: `${base}${url}`,
    data: ans,
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    }
  })
}
export const deleteRequest = (url, parmas) => {
  return axios({
    method: 'delete',
    url: `${base}${url}`,
    data: parmas
  })
}
export const getRequest = url => {
  return axios({
    method: 'get',
    url: `${base}${url}`
  })
}
export const downloadFiles = (url, formData, fullFileName) => {
  axios({
    method: 'post',
    url: `${base}${url}`,
    data: formData,
    responseType: 'blob', // 响应类型设置为blob
    headers: {}
  })
    .then(response => {
      // 创建一个下载链接并模拟点击
      const url = window.URL.createObjectURL(new Blob([response.data]))
      const a = document.createElement('a')
      a.style.display = 'none'
      a.href = url
      a.download = fullFileName || '报表.xlsx' // 可以自定义文件名
      document.body.appendChild(a)
      a.click()
      window.URL.revokeObjectURL(url)
    })
    .catch(error => {
      console.error('下载失败: ' + error)
    })
}

